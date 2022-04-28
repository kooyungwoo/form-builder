package com.formbuilder.formSet;

import com.formbuilder.cmmn.DefaultVO;

public class SearchParamVO extends DefaultVO{
	private Integer formSetSeq;
	private String itemInfoData;
	private Integer authSeq;
	private Integer[] deleteItemSeq;

	public Integer getFormSetSeq() {
		return formSetSeq;
	}

	public void setFormSetSeq(Integer formSetSeq) {
		this.formSetSeq = formSetSeq;
	}

	public String getItemInfoData() {
		return itemInfoData;
	}

	public void setItemInfoData(String itemInfoData) {
		this.itemInfoData = itemInfoData;
	}

	public Integer getAuthSeq() {
		return authSeq;
	}

	public void setAuthSeq(Integer authSeq) {
		this.authSeq = authSeq;
	}

	public Integer[] getDeleteItemSeq() {
		return deleteItemSeq;
	}

	public void setDeleteItemSeq(Integer[] deleteItemSeq) {
		this.deleteItemSeq = deleteItemSeq;
	}
	
}
