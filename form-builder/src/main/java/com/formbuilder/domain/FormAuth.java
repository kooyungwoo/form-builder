package com.formbuilder.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formbuilder.dto.FormAuthDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "FORM_AUTH")
public class FormAuth implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "AUTH_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="권한 번호")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer authSeq;
	
	@Column(name = "AUTH_NAME", length=200, nullable=true, insertable=true, updatable=true, columnDefinition="권한 이름")
	private String authName;
	
	@Column(name = "AUTH_LIST", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="목록 출력 여부")
	private String authList;
	
	@Column(name = "AUTH_WRITE", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="글쓰기 여부")
	private String authWrite;
	
	@Column(name = "AUTH_VIEW", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="상세화면 접근 여부")
	private String authView;
	
	@Column(name = "AUTH_FILE_DOWN", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="파일 다운로드 여부")
	private String authFileDown;
	
	@Column(name = "REG_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="등록일")
	private Date regDate;
	
	@Column(name = "MOD_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="수정일")
	private Date modDate;
	
    @Column(name = "FORM_SET_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="폼게시판 번호")
	private Integer formSetSeq;
    
    @JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_SET_SEQ", insertable=false, updatable=false)
    private FormSet formSet;
    
    @Builder(builderClassName = "ByFormAuthBuilder", builderMethodName = "ByAllBuilder")
	public FormAuth(String authName, String authList, String authWrite, String authView, 
								String authFileDown, Date regDate, Date modDate, Integer formSetSeq) {
		this.authName = authName;
		this.authList = authList;
		this.authWrite = authWrite;
		this.authView = authView;
		this.authFileDown = authFileDown;
		this.regDate = regDate;
		this.modDate = modDate;
		this.formSetSeq = formSetSeq;
	}
    
    public void modFormAuth(String authName, String authList, String authWrite, String authView, 
			String authFileDown, Date modDate) {
    	this.authName = authName;
		this.authList = authList;
		this.authWrite = authWrite;
		this.authView = authView;
		this.authFileDown = authFileDown;
		this.modDate = modDate;
    }
    
    public FormAuthDTO entityToDTO() {
    	FormAuthDTO formAuthDTO = new FormAuthDTO();
		ModelMapper mMapper = new ModelMapper();
		mMapper.map(this, formAuthDTO); 
		
		return formAuthDTO;
    }
}
