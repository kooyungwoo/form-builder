package com.formbuilder.formAuth.web;

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
import com.formbuilder.dto.FormAuthDTO;
import com.formbuilder.formAuth.service.FormAuthService;
import com.formbuilder.formSet.SearchParamVO;

@Controller("formAuthController")
@RequestMapping("/formAuth")
public class FormAuthController {
	@Autowired
	@Qualifier("formAuthService")
	private FormAuthService formAuthService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView formAuthList(@ModelAttribute SearchParamVO searchParam) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		List<FormAuthDTO> formAuthList = formAuthService.formAuthList(searchParam.getFormSetSeq());
		modelAndView.addObject("formAuthList", formAuthList);
		
		modelAndView.addObject("searchParam", searchParam);
		ModelAndViewUtil.setReturnLayout(modelAndView, "/layout/layout_view_1", "../formAuth/list.ftl");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public HashMap<String, Object> formAuthAdd(@ModelAttribute SearchParamVO searchParam, @ModelAttribute FormAuthDTO formAuthInfo) throws Exception{
		
		HashMap<String, Object> returnMap = formAuthService.formAuthWork(formAuthInfo);
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/info/{authSeq}", method = RequestMethod.GET)
	public HashMap<String, Object> formAuthInfo(@ModelAttribute SearchParamVO searchParam) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		FormAuthDTO authInfo = formAuthService.formAuthInfo(searchParam.getAuthSeq());
		returnMap.put("authInfo", authInfo);
		returnMap.put("result", true);
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/del/{authSeq}", method = RequestMethod.DELETE)
	public HashMap<String, Object> formAuthDel(@ModelAttribute SearchParamVO searchParam) throws Exception{
		
		formAuthService.deleteFormAuth(searchParam.getAuthSeq());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", true);
		
		return returnMap;
	}
}
