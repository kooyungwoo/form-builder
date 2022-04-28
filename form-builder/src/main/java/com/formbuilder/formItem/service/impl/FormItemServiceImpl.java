package com.formbuilder.formItem.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formbuilder.cmmn.JsonConvertUtil;
import com.formbuilder.domain.FormField;
import com.formbuilder.domain.FormItem;
import com.formbuilder.domain.FormSet;
import com.formbuilder.dto.FormItemDTO;
import com.formbuilder.dto.FormSetDTO;
import com.formbuilder.formItem.ItemFirstVO;
import com.formbuilder.formItem.ItemListOrderVO;
import com.formbuilder.formItem.ItemSecondVO;
import com.formbuilder.formItem.ItemThirdVO;
import com.formbuilder.formItem.JsonToFormField;
import com.formbuilder.formItem.service.FormItemService;
import com.formbuilder.formSet.SearchParamVO;
import com.formbuilder.repository.FormFieldRepository;
import com.formbuilder.repository.FormItemRepository;

@Service("formItemService")
public class FormItemServiceImpl implements FormItemService {
	
	@Resource(name="formItemRepository")
	private FormItemRepository formItemRepository;
	
	@Resource(name="formFieldRepository")
	private FormFieldRepository formFieldRepository;
	
	//@Transactional 으로 트랜잭션 처리 aop관련 설정 추가 하지 않음
	/*
	 Transactional 속성
	 isolation = 트랜잭션에서 일관성없는 데이터 허용 수준을 설정한다
	 (isolation은 DB운영 관련 문제로 생각해 DEFAULT를 사용 하고 있습니다. 자세한 내용은 oracle,mysql isolation 관련 내용 검색 필요-oracle, mysql이 기본 isolation이 다름)
	 (
	 DEFAULT : 기본이며, DB의 lsolation Level을 따른다.
	 READ_UNCOMMITED (level 0) : 커밋되지 않는 데이터에 대한 읽기를 허용
	 READ_COMMITED (level 1) : 커밋된 데이터에 대해 읽기 허용
	 REPEATEABLE_READ (level 2) : 동일 필드에 대해 다중 접근 시 모두 동일한 결과를 보장
	 SERIALIZABLE (level 3) : 가장 높은 격리, 성능 저하의 우려가 있음
	 )
	 propagation = 트랜잭션 동작 도중 다른 트랜잭션을 호출할 때, 어떻게 할 것인지 지정하는 옵션이다
	 (
	 REQUIRED = 이미 진행중인 트랜잭션이 있다면 해당 트랜잭션 속성을 따르고, 진행중이 아니라면 새로운 트랜잭션을 생성한다.
	 REQUIRES_NEW = 항생 새로운 트랜잭션을 생성한다. 이미 진행중인 트랜잭션이 있다면 잠깐 보류하고 해당 트랜잭션 작업을 먼저 진행한다
	 SUPPORT = 이미 진행 중인 트랜잭션이 있다면 해당 트랜잭션 속성을 따르고, 없다면 트랜잭션을 설정하지 않는다.
	 NOT_SUPPORT = 이미 진행중인 트랜잭션이 있다면 보류하고, 트랜잭션 없이 작업을 수행한다.
	 MANDATORY = 이미 진행중인 트랜잭션이 있어야만, 작업을 수행한다. 없다면 Exception을 발생시킨다.
	 NEVER = 트랜잭션이 진행중이지 않을 때 작업을 수행한다. 트랜잭션이 있다면 Exception을 발생시킨다.
	 NESTED = 진행중인 트랜잭션이 있다면 중첩된 트랜잭션이 실행되며, 존재하지 않으면 REQUIRED와 동일하게 실행된다.
	 )
	 noRollbackFor = 특정 예외 발생 시 rollback하지 않는다.(noRollbackFor=Exception.class)
	 rollbackFor = 특정 예외 발생 시 rollback한다.(rollbackFor=Exception.class)
	 timeout = 지정한 시간 내에 메소드 수행이 완료되지 않으면 rollback 한다. (-1일 경우 timeout을 사용하지 않는다 기본은 -1)(timeout=10) 초단위
	 readOnly = 트랜잭션을 읽기 전용으로 설정한다.(true 시 insert, update, delete 실행 시 예외 발생 기본은 false)
	 
	 * */
	@Transactional(rollbackFor={Exception.class}, isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	@Override
	public boolean addFormItem(SearchParamVO searchParam, ItemFirstVO[] itemFirstVO) throws Exception {
		boolean isSuccess = true;
		//if>for>if 로 넘어가는 구조 문제 있음 디자인 패턴 적용 필요
		if(itemFirstVO!=null && itemFirstVO.length>0) {
			Date toDay = new Date();
			JsonConvertUtil jsonConvertUtil = new JsonConvertUtil();
			for(ItemFirstVO itemFirst:itemFirstVO) {
				itemFirst.getItemJson().setItemLiIndex(itemFirst.getItemIndex());
				String tbColumnName 	= "column_"+searchParam.getFormSetSeq()+"_"+itemFirst.getItemIndex();
				String itemJson 			= new ObjectMapper().writeValueAsString(itemFirst.getItemJson());
				String itemHtml			= jsonConvertUtil.jsonToHtml(itemFirst.getItemJson(), tbColumnName);
				
				FormItem formItem = null;
				if(itemFirst.getItemSeq()!=null && itemFirst.getItemSeq()!=0) {
					formItem = formItemRepository.getById(itemFirst.getItemSeq());
					formItem.modFormItem(itemFirst.getItemJson().getItemName(), itemFirst.getItemJson().getListView(), 
							itemFirst.getItemJson().getItemView(), itemFirst.getItemIndex(), "hor", 
							itemFirst.getItemJson().getItemDes(), toDay, itemHtml, itemJson);
				}else {
					formItem = FormItem.ByAllBuilder().formItemName(itemFirst.getItemJson().getItemName())
							.formItemType(itemFirst.getItemJson().getItemType())
							.formItemList(itemFirst.getItemJson().getListView())
							.formItemListOrder(0)
							.formItemView(itemFirst.getItemJson().getItemView())
							.formItemViewOrder(itemFirst.getItemIndex())
							.formItemViewType("hor")//추후에 ver(수직)관련 작업 추가 현재 계획은 디자인은 css에서 처리하게
							.formItemDes(itemFirst.getItemJson().getItemDes())
							.regDate(toDay)
							.modDate(toDay)
							.formSetSeq(searchParam.getFormSetSeq())
							.tbColumnName(tbColumnName)
							.itemHtml(itemHtml)
							.itemJson(itemJson).build();
				}
				formItemRepository.save(formItem);
				if(formItem.getFormItemSeq()==null || formItem.getFormItemSeq()==0) {
					isSuccess = false;
					break;
				}else {
					isSuccess = addFormField(itemFirst.getItemJson(), itemFirst.getItemJson().getFieldDatas(), formItem.getFormItemSeq(), toDay);
				}
			}
		}
		//삭제된 항목 작업
		deleteItem(searchParam.getDeleteItemSeq());
		
		return isSuccess;
	}
	
	public void deleteItem(Integer[] deleteItemSeq) {
		if(deleteItemSeq!=null && deleteItemSeq.length>0) {
			for(Integer formItemSeq:deleteItemSeq) {
				if(formItemSeq!=null && formItemSeq!=0) {
					//관련필드 삭제
					formFieldRepository.deleteByFormItemSeq(formItemSeq);
					//항목 삭제
					Optional<FormItem> optionalInfo 	= formItemRepository.findById(formItemSeq);
					FormItem itemInfo 							= optionalInfo.orElse(null);
					formItemRepository.delete(itemInfo);
				}
			}
		}
	}
	
	public boolean addFormField(ItemSecondVO itemScondVO, List<ItemThirdVO> fieldDatas, Integer formItemSeq, Date toDay) {
		boolean isSuccess = true;
		
		//기존에 등록된 필드 정보 삭제 후 재 입력
		formFieldRepository.deleteByFormItemSeq(formItemSeq);
		
		if(fieldDatas!=null && fieldDatas.size()>0) {
			/*
			 	람다 표현식 함수 축약의 경우 아래의내용을 참고 해서 작업 가능
			 	(Apple apple) -> apple.getWeight() 는 Apple::getWeight
				() -> Thread.currentThread().dumpStack() 는 Thread.currentThread()::dumpStack
				(str, i) -> str.subString(i) 는 String::subString
				(String s) -> System.out.println(s) 는 System.out::pringln
				(String s) -> this.isValidName(s) 는 this::isValidName
			 * */
			List<FormField> formFieldList 	= fieldDatas.stream().map((ItemThirdVO fieldInfo) -> fieldInfo.jsonToDomain(formItemSeq, itemScondVO, toDay)).collect(Collectors.toList());
			formFieldRepository.saveAll(formFieldList);
			
			int fieldSeqChkCnt = 0;
			formFieldList.stream().forEach(FormField -> chkFormFieldSeq(FormField.getFormFieldSeq(), fieldSeqChkCnt));
			//fieldSeqChkCnt 값이 0보다 큰경우 fieldSeq값이 갱신 안된 경루로 작업에 실패 했다고 보고 실패 플래그 작업
			if(fieldSeqChkCnt>0) {
				isSuccess = false;
			}
			
//			for(ItemThirdVO fieldInfo:fieldDatas) {
//				FormField formField = FormField.ByAllBuilder().formFieldText(fieldInfo.getFieldText())
//													.formFieldValue(fieldInfo.getFieldValue())
//													.formFiledDefault(fieldInfo.getFieldDefault())
//													.formFieldPhd(itemScondVO.getFieldPhd())
//													.formFieldDes(itemScondVO.getItemDes())
//													.regDate(toDay)
//													.modDate(toDay)
//													.formItemSeq(formItemSeq).build();
//				formFieldRepository.save(formField);//saveAll 사용가능한지 검토 후 상요자 화면부터 변경 필요
//				if(formField.getFormFieldSeq()==null || formField.getFormFieldSeq()==0) {isSuccess = false; break;}
//			}
		}else {
			FormField formField = FormField.ByAllBuilder().formFieldText(itemScondVO.getItemName())
					.formFieldValue("")
					.formFiledDefault(itemScondVO.getFiledDefault())
					.formFieldPhd(itemScondVO.getFieldPhd())
					.formFieldDes(itemScondVO.getItemDes())
					.regDate(toDay)
					.modDate(toDay)
					.formItemSeq(formItemSeq).build();
			
					formFieldRepository.save(formField);
					if(formField.getFormFieldSeq()==null || formField.getFormFieldSeq()==0) {isSuccess = false;}
		}
		return isSuccess;
	}
	
	@Override
	public boolean formItemListOrderSave(SearchParamVO searchParam, ItemListOrderVO[] itemListOrderVO) throws Exception {
		boolean isSuccess = true;
		
		//정렬 기준 변경 전 정보 초기화
		resetFormItemListInfo(searchParam.getFormSetSeq());
		
		if(itemListOrderVO!=null && itemListOrderVO.length>0) {
			Date toDay = new Date();
			for(ItemListOrderVO itemListOrder:itemListOrderVO) {
				Optional<FormItem> optionalInfo 	= formItemRepository.findById(itemListOrder.getItemSeq());
				FormItem dataInfo 							= optionalInfo.orElse(null);
				if(dataInfo!=null) {
					dataInfo.modFormItem("Y", itemListOrder.getItemIndex(), toDay);
					
					formItemRepository.save(dataInfo);
				}
			}
		}
		return isSuccess;
	}
	
	@Override
	public List<FormItemDTO> formItemList(Integer formSetSeq) throws Exception {
		List<FormItem> dataList 					= formItemRepository.findByFormSetSeq(formSetSeq, Sort.by(Sort.Direction.ASC, "formItemViewOrder"));
		List<FormItemDTO> formItemList 	= dataList.stream().map(FormItem::entityToDTO).collect(Collectors.toList());
		return formItemList;
	}
	
	@Override
	public List<FormItemDTO> formItemList(Integer formSetSeq, String formItemListYn) throws Exception {
		List<FormItem> dataList 					= formItemRepository.findByFormSetSeqAndFormItemList(formSetSeq, formItemListYn, Sort.by(Sort.Direction.ASC, "formItemListOrder"));
		List<FormItemDTO> formItemList 	= dataList.stream().map(FormItem::entityToDTO).collect(Collectors.toList());
		return formItemList;
	}
	
	public int resetFormItemListInfo(Integer formSetSeq) {
		return formItemRepository.updateByFormSetSeq(formSetSeq);
	}
	
	public void chkFormFieldSeq(Integer formFieldSeq, int fieldSeqChkCnt) {
		if(formFieldSeq==null || formFieldSeq==0) {++fieldSeqChkCnt;}
	}

}
