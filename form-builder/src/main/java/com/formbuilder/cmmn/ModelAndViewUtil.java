package com.formbuilder.cmmn;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil {
	
	/**
	 * 전달받은 layout, page 정보를 ModelAndView에 설정
	 * @param ModelAndView 저장하려는ModelAndView객체
	 * @param String layout 정보
	 * @param String 실제 호출되는 페이지 정보
	 * */
	public static void setReturnLayout(ModelAndView modelAndView, String layOutPage, String viewPage) {
		modelAndView.addObject("TPL", viewPage);
		modelAndView.setViewName(layOutPage);
	}
}
