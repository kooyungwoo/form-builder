package com.formbuilder.formAuth.service;

import java.util.HashMap;
import java.util.List;

import com.formbuilder.dto.FormAuthDTO;

public interface FormAuthService {
	/**
	 * 폼빌더에 저장된 권한 목록을 가지고 온다
	 * @param Integer formSetSeq 폼빌더 번호
	 * @return List<FormAuthDTO> 권한 목록
	 * */
	public List<FormAuthDTO> formAuthList(Integer formSetSeq) throws Exception;

	/**
	 * 권한 상세 설정 정보를 가지고 온다
	 * @param Integer authSeq 권한 번호
	 * @return FormAuthDTO 권한 정보
	 * */
	public FormAuthDTO formAuthInfo(Integer authSeq) throws Exception;

	/**
	 * 권한 정보를 저장/수정 한다
	 * @param FormAuthDTO formAuthInfo 권한 정보
	 * @return void
	 * */
	public HashMap<String, Object> formAuthWork(FormAuthDTO formAuthInfo) throws Exception;

	/**
	 * 권한 정보를 삭제 한다
	 * @param FormAuthDTO formAuthInfo 권한 정보
	 * @return void
	 * */
	public void deleteFormAuth(Integer authSeq) throws Exception;
}
