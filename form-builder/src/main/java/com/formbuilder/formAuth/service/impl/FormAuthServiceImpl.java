package com.formbuilder.formAuth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.formbuilder.domain.FormAuth;
import com.formbuilder.dto.FormAuthDTO;
import com.formbuilder.formAuth.service.FormAuthService;
import com.formbuilder.repository.FormAuthRepository;

@Service("formAuthService")
public class FormAuthServiceImpl implements FormAuthService{
	@Resource(name="formAuthRepository")
	private FormAuthRepository formAuthRepository;
	
	@Override
	public List<FormAuthDTO> formAuthList(Integer formSetSeq) throws Exception {
		List<FormAuth> dataList 					= formAuthRepository.findByFormSetSeq(formSetSeq, Sort.by(Sort.Direction.ASC, "authSeq"));
		List<FormAuthDTO> formAuthList 	= dataList.stream().map(FormAuth::entityToDTO).collect(Collectors.toList());
		return formAuthList;
	} 
	
	@Override
	public FormAuthDTO formAuthInfo(Integer authSeq) throws Exception  {
		Optional<FormAuth> optionalInfo 	= formAuthRepository.findById(authSeq);
		FormAuth dataInfo 							= optionalInfo.orElse(null);
		return dataInfo.entityToDTO();
	}
	
	@Override
	public HashMap<String, Object> formAuthWork(FormAuthDTO formAuthInfo) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if(formAuthInfo.getAuthSeq()==null || formAuthInfo.getAuthSeq()==0) {
			returnMap = insertFormAuth(formAuthInfo);
		}else {
			updateFormAuth(formAuthInfo);
			returnMap.put("result", true);
		}
		
		return returnMap;
	}
	
	public HashMap<String, Object> insertFormAuth(FormAuthDTO formAuthInfo) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		Date toDay 					= new Date();
		formAuthInfo.setRegDate(toDay);
		formAuthInfo.setModDate(toDay);
		
		FormAuth formAuth = formAuthInfo.dtoToEntity();
		
		formAuthRepository.save(formAuth);
		if(formAuth.getAuthSeq()==null) {
			returnMap.put("result", false);
			returnMap.put("message", "정상적으로 저장 되지 않았습니다.");
		}else {
			returnMap.put("result", true);
		}
		return returnMap;
	}
	
	public void updateFormAuth(FormAuthDTO formAuthInfo) throws Exception {
		Date toDay 					= new Date();
		
		FormAuth dbFormAuth = formAuthRepository.findById(formAuthInfo.getAuthSeq()).get();
		dbFormAuth.modFormAuth(formAuthInfo.getAuthName(), formAuthInfo.getAuthList(), formAuthInfo.getAuthWrite(), formAuthInfo.getAuthView(), 
				formAuthInfo.getAuthFileDown(), toDay);
		
		formAuthRepository.saveAndFlush(dbFormAuth);
	}
	
	@Override
	public void deleteFormAuth(Integer authSeq) throws Exception {
		formAuthRepository.deleteByAuthSeq(authSeq);
	}
}
