package com.formbuilder.formItem;

import java.lang.FunctionalInterface;
import com.formbuilder.domain.FormField;

@FunctionalInterface
public interface JsonToFormField {
	
	public FormField jsonObjToFormField(ItemSecondVO itemScondVO, ItemThirdVO fieldInfo);
}
