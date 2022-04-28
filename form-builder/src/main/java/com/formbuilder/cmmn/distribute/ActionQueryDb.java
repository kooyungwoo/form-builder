package com.formbuilder.cmmn.distribute;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//DB 타입 정보-생성 쿼리문 만드는 class경로를 가지고 있는 enum
public enum ActionQueryDb {
	MYSQL("MYSQL", "com.formbuilder.cmmn.distribute.MysqlActionQuery");
	
	private String code;
    private String value;

    ActionQueryDb(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ActionQueryDb getEnumByCode(String code) {
    	List<ActionQueryDb> actionQueryDbList = Stream.of(values()).filter(t->t.getCode().equals(code)).collect(Collectors.toList());
		if(actionQueryDbList!=null && actionQueryDbList.size()>0) {
			return actionQueryDbList.get(0);
		}else {
			return null;
		}
	}
}
