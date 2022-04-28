package com.formbuilder.domain.addEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FormItemType {
	TEXT("text", "텍스트박스"), FILE("file", "파일"), TEXTAREA("textArea", "텍스트영역"),
	CHECKBOX("checkBox", "체크박스"), SELECTBOX("selectBox", "선택박스"), RADIO("radio", "라디오");
    
    private String code;
    private String value;

    FormItemType(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getValue() {
        return value;
    }
    
    //code 값이 일치 하는 FormItemType 객체를 넘겨준다
    public static FormItemType getEnumByCode(String code) {
    	List<FormItemType> formItemTypeList = Stream.of(values()).filter(t->t.getCode().equals(code)).collect(Collectors.toList());
		if(formItemTypeList!=null && formItemTypeList.size()>0) {
			return formItemTypeList.get(0);
		}else {
			return null;
		}
	}
    
    //value 값이 일치 하는 FormSetType 객체를 넘겨준다
    public static FormItemType getEnumByValue(String value) {
		List<FormItemType> formItemTypeList = Stream.of(values()).filter(t->t.getValue().equals(value)).collect(Collectors.toList());
		if(formItemTypeList!=null && formItemTypeList.size()>0) {
			return formItemTypeList.get(0);
		}else {
			return null;
		}
	}
}
