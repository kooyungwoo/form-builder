package com.formbuilder.formItem.service;

import java.util.List;

import com.formbuilder.dto.FormItemDTO;
import com.formbuilder.formItem.ItemFirstVO;
import com.formbuilder.formItem.ItemListOrderVO;
import com.formbuilder.formSet.SearchParamVO;

public interface FormItemService {
	
	public boolean addFormItem(SearchParamVO searchParam, ItemFirstVO[] itemFirstVO) throws Exception;
	
	public List<FormItemDTO> formItemList(Integer formSetSeq) throws Exception;
	
	public List<FormItemDTO> formItemList(Integer formSetSeq, String formItemListYn) throws Exception;
	
	public boolean formItemListOrderSave(SearchParamVO searchParam, ItemListOrderVO[] itemListOrderVO) throws Exception;
	
}
