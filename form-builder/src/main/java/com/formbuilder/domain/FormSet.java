package com.formbuilder.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formbuilder.dto.FormSetDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "FORM_SET")
public class FormSet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "FORM_SET_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="폼게시판 번호")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer formSetSeq;
	
	@Column(name = "FORM_SET_NAME", length=200, nullable=true, insertable=true, updatable=true, columnDefinition="폼게시판 명")
	private String formSetName;
	
	@Column(name = "FORM_SET_TYPE", length=3, nullable=true, insertable=true, updatable=true, columnDefinition="폼게시판 타입(100,200,300)")
	private String formSetType;
	
	@Column(name = "FORM_SET_SKIN", length=10, nullable=true, insertable=true, updatable=true, columnDefinition="폼 디자인(001,002...)")
	private String formSetSkin;
	
	@Column(name = "TARGET_TABLE_NAME", length=30, nullable=true, insertable=true, updatable=true, columnDefinition="데이터 저장 테이블명")
	private String targetTableName;
	
	@Column(name = "REG_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="등록일")
	private Date regDate;
	
	@Column(name = "MOD_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="수정일")
	private Date modDate;
	
	@Column(name = "DIST_COMP", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="배포완료여부")
	private String distComp;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_SET_SEQ")
	private List<FormItem> formItemList = new ArrayList<FormItem>();
	
	@Builder(builderClassName = "ByFormSetBuilder", builderMethodName = "ByAllBuilder")
	public FormSet(String formSetName, String formSetType, String formSetSkin, String targetTableName,
			Date regDate, Date modDate, String distComp) {
		this.formSetName = formSetName;
		this.formSetType = formSetType;
		this.formSetSkin = formSetSkin;
		this.targetTableName = targetTableName;
		this.regDate = regDate;
		this.modDate = modDate;
		this.distComp = distComp;
	}
	
	public void modFormSet(String formSetName, String formSetType, String formSetSkin, String targetTableName,
			Date modDate) {
		this.formSetName = formSetName;
		this.formSetType = formSetType;
		this.formSetSkin = formSetSkin;
		this.targetTableName = targetTableName;
		this.modDate = modDate;
	}
	
	public void distributeFormSet(String distComp, Date modDate) {
		this.distComp = distComp;
		this.modDate = modDate;
	}
	
	 public FormSetDTO entityToDTO() {
		 FormSetDTO formSetDTO = new FormSetDTO();
		ModelMapper mMapper = new ModelMapper();
		mMapper.map(this, formSetDTO); 
		
		return formSetDTO;
    }
}
