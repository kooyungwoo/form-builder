package com.formbuilder.formItem.web;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formbuilder.cmmn.ModelAndViewUtil;
import com.formbuilder.dto.FormItemDTO;
import com.formbuilder.formItem.ItemFirstVO;
import com.formbuilder.formItem.ItemListOrderVO;
import com.formbuilder.formItem.service.FormItemService;
import com.formbuilder.formSet.SearchParamVO;

@Controller("formItemController")
@RequestMapping("/formItem")
public class FormItemController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("formItemService")
	private FormItemService formItemService;
	
	@RequestMapping(value="/item", method = RequestMethod.GET)
	public ModelAndView formItemView(@ModelAttribute SearchParamVO searchParam) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		List<FormItemDTO> formItemList = formItemService.formItemList(searchParam.getFormSetSeq());
		modelAndView.addObject("formItemList", formItemList);
		
		modelAndView.addObject("searchParam", searchParam);
		ModelAndViewUtil.setReturnLayout(modelAndView, "/layout/layout_view_1", "../formItem/item.ftl");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public HashMap<String, Object> formItemAdd(@ModelAttribute SearchParamVO searchParam) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		//파라미터 받을때 @ModelAttribute으로 받으려고 했는데 넘겨줄때 "-> \" 으로 변경하고 {-> "{, }->}" 으로 변경 하는 문제로 치환 후 변경 하는 작업 진행
		//추후 원인 파악해서 ModelAttribute 으로 변경 필요
		String itemInfoData = searchParam.getItemInfoData();
		itemInfoData = itemInfoData.replaceAll("\\\\\"", "\"").replaceAll(":\"\\{", ":\\{").replaceAll("\"\\}\"", "\"\\}").replaceAll("}\"}", "}}");
		
		ObjectMapper objectMapper 	= new ObjectMapper();
        ItemFirstVO[] itemFirstVOs 		= objectMapper.readValue(itemInfoData, ItemFirstVO[].class);
        boolean isSuccess 					= formItemService.addFormItem(searchParam, itemFirstVOs);
        
        returnMap.put("result", isSuccess);
        
		return returnMap;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView formItemList(@ModelAttribute SearchParamVO searchParam) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		
		List<FormItemDTO> formItemList = formItemService.formItemList(searchParam.getFormSetSeq());
		modelAndView.addObject("formItemList", formItemList);
		
		List<FormItemDTO> formItemViewList = formItemService.formItemList(searchParam.getFormSetSeq(), "Y");
		modelAndView.addObject("formItemViewList", formItemViewList);
		
		modelAndView.addObject("searchParam", searchParam);
		ModelAndViewUtil.setReturnLayout(modelAndView, "/layout/layout_view_1", "../formItem/list.ftl");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/list/order/add", method = RequestMethod.POST)
	public HashMap<String, Object> formItemListOrderAdd(@ModelAttribute SearchParamVO searchParam) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		//파라미터 받을때 @ModelAttribute으로 받으려고 했는데 넘겨줄때 "-> \" 으로 변경하고 {-> "{, }->}" 으로 변경 하는 문제로 치환 후 변경 하는 작업 진행
		String itemInfoData = searchParam.getItemInfoData();
		itemInfoData = itemInfoData.replaceAll("\\\\\"", "\"").replaceAll(":\"\\{", ":\\{").replaceAll("\"\\}\"", "\"\\}");
		
		ObjectMapper objectMapper 					= new ObjectMapper();
		ItemListOrderVO[] itemListOrderVOs 	= objectMapper.readValue(itemInfoData, ItemListOrderVO[].class);
        boolean isSuccess 								= formItemService.formItemListOrderSave(searchParam, itemListOrderVOs);
        
        returnMap.put("result", isSuccess);
        
		return returnMap;
	}
}
