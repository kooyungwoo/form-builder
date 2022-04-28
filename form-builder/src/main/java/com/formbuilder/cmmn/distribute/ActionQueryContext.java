package com.formbuilder.cmmn.distribute;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.formbuilder.domain.FormSet;

//db 타입에 맞춰서 동적인 class생성이 필요해 스트래티지 패턴 사용
public class ActionQueryContext {
	private ActionQuery actionQuery = null;
	private FormSet formSetInfo = null;
	
	public String getQueryStr() {
		return actionQuery.getQueryStr(formSetInfo);
	}
	
	/**
	 * 전달 받은 dbType에 맞는 ActionQuery 구현체를 찾아 설정 하는 메소드
	 * @param String dbType db 타입
	 * @param FormSet formSetInfo 쿼리문 작성 시 사용할 정보가 담긴 객체
	 * */
	public void setActionQuery(String dbType, FormSet formSetInfo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//db 타입에 맞는 구현체 경로를 enum 에서 호출
		ActionQueryDb actionQueryDb 		= ActionQueryDb.getEnumByCode(dbType);
		String classPath 							= actionQueryDb.getValue();
		//관련 class를 reflection으로 호출 및 생성
		Class<?> tempClass					= Class.forName(classPath);//class 호출
		Constructor<?> constructor 			= tempClass.getConstructor();//생성자 호출
		this.actionQuery 						= (ActionQuery)constructor.newInstance();//생성자 실행
		this.formSetInfo = formSetInfo;
	}
}
