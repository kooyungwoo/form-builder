package com.formbuilder.domain.addEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormSetType {
	TYPE100("100", "100타입"), TYPE200("200", "200타입"), TYPE300("300", "300타입"),
	TYPE400("400", "400타입"), TYPE500("500", "500타입"), TYPE600("600", "600타입"),
	TYPE700("700", "700타입");
	
    private String code;
    private String value;

    FormSetType(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getValue() {
        return value;
    }
    
    //code 값이 일치 하는 FormSetType 객체를 넘겨준다
    public static FormSetType getEnumByCode(String code) {
    	List<FormSetType> formSetTypeList = Stream.of(values()).filter(t->t.getCode().equals(code)).collect(Collectors.toList());
		if(formSetTypeList!=null && formSetTypeList.size()>0) {
			return formSetTypeList.get(0);
		}else {
			return null;
		}
	}
    
    //value 값이 일치 하는 FormSetType 객체를 넘겨준다
    public static FormSetType getEnumByValue(String value) {
		List<FormSetType> formSetTypeList = Stream.of(values()).filter(t->t.getValue().equals(value)).collect(Collectors.toList());
		if(formSetTypeList!=null && formSetTypeList.size()>0) {
			return formSetTypeList.get(0);
		}else {
			return null;
		}
	}
}
