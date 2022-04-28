package com.formbuilder.formSet.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.formbuilder.cmmn.distribute.DistributeUtil;
import com.formbuilder.domain.FormSet;
import com.formbuilder.dto.FormSetDTO;
import com.formbuilder.formSet.service.FormSetService;
import com.formbuilder.repository.FormSetRepository;

@Service("formSetService")
public class FormSetServiceImpl implements FormSetService {
	
	@Resource(name="formSetRepository")
	private FormSetRepository formSetRepository;
	
	@Resource(name="distributeUtil")
	private DistributeUtil  distributeUtil;
	
	@Override
	public List<FormSetDTO> formSetList() throws Exception {
		List<FormSet> dataList 				= formSetRepository.findAll();
		List<FormSetDTO> formSetList 	= dataList.stream().map(FormSet::entityToDTO).collect(Collectors.toList());
		return formSetList;
	}
	
	@Override
	public FormSetDTO formSetInfo(Integer formSetSeq) throws Exception  {
		Optional<FormSet> optionalInfo 	= formSetRepository.findById(formSetSeq);
		FormSet dataInfo 							= optionalInfo.orElse(null);
		/*
		아래와 같이 FormSet 객체가 존재 하는지 확인 후 넘길 수 있습니다.
		if(optionalInfo.isPresent()) {
			optionalInfo.get();
		}
		확인 하지 않고 바로 get하는 경우 null pointer exception 발생
		*/
		/*
		 Optional 에 FormSet객체가 null인경우 NullPointerException을 throw
		 FormSet dataInfo = optionalInfo.orElseThrow(NullPointerException::new);
		 Optional 에 FormSet 객체가 null인 경우 전달받은 값으로 대체 해서 넘겨줌(new FormSet()에 해당하는 정보는 null이 아니여서 우선 save 되기 때문에 확인 필요)
		 FormSet dataInfo = optionalInfo.orElse(new FormSet());
		 null로 리턴
		 FormSet dataInfo = optionalInfo.orElse(null);
		 Optional 에 FormSet 객체가 null인 경우 신규 정보를 DB에 저장하고 해당 정보를 반환
		 FormSet dataInfo = formSetRepository.findById(formSetSeq).orElseGet(() -> formSetRepository.save(new FormSet(formSetSeq));
		 비어 있는 객체로 생성해서 리턴
		 FormSet dataInfo = formSetRepository.findById(formSetSeq).orElseGet(FormSet::new);
		 * */
		return dataInfo.entityToDTO();
	}
	
	@Override
	public HashMap<String, Object> insertFormSetInfo(FormSetDTO formSetInfo) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		Date toDay 					= new Date();
		formSetInfo.setRegDate(toDay);
		formSetInfo.setModDate(toDay);
		
		FormSet resultFormSet = formSetRepository.save(formSetInfo.dtoToEntity());
		if(resultFormSet.getFormSetSeq()==null) {
			returnMap.put("result", false);
			returnMap.put("message", "정상적으로 저장 되지 않았습니다.");
		}else {
			returnMap.put("result", true);
		}
		return returnMap;
	}
	
	@Override
	public void updateSiteInfo(FormSetDTO formSetInfo) throws Exception {
		Date toDay 					= new Date();
		
		FormSet dbFormSet = formSetRepository.findById(formSetInfo.getFormSetSeq()).get();
		dbFormSet.modFormSet(formSetInfo.getFormSetName(), formSetInfo.getFormSetType(), formSetInfo.getFormSetSkin(), formSetInfo.getTargetTableName(), 
				toDay);
		
		formSetRepository.saveAndFlush(dbFormSet);
	}
	
	@Transactional(rollbackFor={Exception.class}, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	@Override
	public HashMap<String, Object> distributeFormSet(Integer formSetSeq) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		boolean isSuccess = false;
		Optional<FormSet> optionalInfo 	= formSetRepository.findById(formSetSeq);
		FormSet dbFormSet						= optionalInfo.orElse(null);
		if("N".equals(dbFormSet.getDistComp())) {
			isSuccess = distributeUtil.createTable(dbFormSet);
			if(isSuccess) {
				dbFormSet.distributeFormSet("Y", new Date());
				formSetRepository.saveAndFlush(dbFormSet);
				
				returnMap.put("result", true);
			}else {
				returnMap.put("result", false);
				returnMap.put("message", "테이블 생성에 실패 했습니다.");
			}
		}else {
			returnMap.put("result", false);
			returnMap.put("message", "이미 배포 완료된 정보 입니다.");
		}
		
		return returnMap;
	}
}
