package com.formbuilder.formSet.web;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.formbuilder.cmmn.ModelAndViewUtil;
import com.formbuilder.dto.FormSetDTO;
import com.formbuilder.formSet.SearchParamVO;
import com.formbuilder.formSet.service.FormSetService;

@Controller("formSetController")
@RequestMapping("/formSet")
public class FormSetController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("formSetService")
	private FormSetService formSetService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView formSetList(@ModelAttribute SearchParamVO searchParam) throws Exception{
		logger.debug("start list");
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<FormSetDTO> formSetList = formSetService.formSetList();
		modelAndView.addObject("formSetList", formSetList);
		
		ModelAndViewUtil.setReturnLayout(modelAndView, "/layout/layout_view_1", "../formSet/list.ftl");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public HashMap<String, Object> formSetAdd(@ModelAttribute SearchParamVO searchParam, @ModelAttribute FormSetDTO formSetInfo) throws Exception{
		HashMap<String, Object> returnMap = formSetService.insertFormSetInfo(formSetInfo);
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/distribute", method = RequestMethod.GET)
	public HashMap<String, Object> formSetDistribute(@ModelAttribute SearchParamVO searchParam) throws Exception{
		
		HashMap<String, Object> returnMap = formSetService.distributeFormSet(searchParam.getFormSetSeq());
		
		return returnMap;
	}
}
