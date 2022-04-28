package com.formbuilder.cmmn.distribute;

import java.util.List;

import com.formbuilder.domain.FormItem;
import com.formbuilder.domain.FormSet;

public class MysqlActionQuery implements ActionQuery {
	
	private StringBuffer actionQuery = new StringBuffer();
	
	public MysqlActionQuery() {
		super();
	}

	@Override
	public String getQueryStr(FormSet formSetInfo) {
		String targetTableName 			= formSetInfo.getTargetTableName();
		List<FormItem> formItemList 	= formSetInfo.getFormItemList();
		if(formItemList!=null && formItemList.size()>0) {
			actionQuery.isEmpty();
			actionQuery.append(" CREATE TABLE "+targetTableName+" ( \n");
			actionQuery.append(" "+targetTableName+"_SEQ int NOT NULL COMMENT '데이터 번호', \n");
			for(FormItem formItemInfo:formItemList) {
				actionQuery.append(formItemInfo.getTbColumnName()+" varchar(200) COMMENT '"+formItemInfo.getFormItemDes()+"',  \n");
			}
			actionQuery.append(" REG_DATE DATETIME DEFAULT NULL COMMENT '등록일', \n");
			actionQuery.append(" MOD_DATE DATETIME DEFAULT NULL COMMENT '수정일' \n");
			actionQuery.append(" ) \n");
		}
		return actionQuery.toString();
	}

}
