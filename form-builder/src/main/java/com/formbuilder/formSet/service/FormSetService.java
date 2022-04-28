package com.formbuilder.formSet.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import com.formbuilder.dto.FormSetDTO;

public interface FormSetService {
	public List<FormSetDTO> formSetList() throws Exception;
	
	public FormSetDTO formSetInfo(Integer formSetSeq) throws Exception;

	public HashMap<String, Object> insertFormSetInfo(FormSetDTO formSetInfo) throws Exception;

	public void updateSiteInfo(FormSetDTO formSetInfo) throws Exception;
	
	public HashMap<String, Object> distributeFormSet(Integer formSetSeq) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
