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
import com.formbuilder.dto.FormFieldDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "FORM_FIELD")
public class FormField implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "FORM_FIELD_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="폼 항목 필드 번호")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer formFieldSeq;
	
	@Column(name = "FORM_FIELD_TEXT", length=200, nullable=true, insertable=true, updatable=true, columnDefinition="필드 명(text,textrare->동작 없음, radio,checkbox,selectbox->필드명)")
	private String formFieldText;
	
	@Column(name = "FORM_FIELD_VALUE", length=20, nullable=true, insertable=true, updatable=true, columnDefinition="필드 값(text,textrare,radio,checkbox,selectbox->value)")
	private String formFieldValue;
	
	@Column(name = "FORM_FILED_DEFAULT", length=20, nullable=true, insertable=true, updatable=true, columnDefinition="필드 기본 값(text,textarea->value우선 / radio,checkbox,selectbox->기본선택)")
	private String formFiledDefault;
	
	@Column(name = "FORM_FIELD_PHD", length=200, nullable=true, insertable=true, updatable=true, columnDefinition="필드 힌트(placeholder)")
	private String formFieldPhd;
	
	@Column(name = "FORM_FIELD_DES", length=500, nullable=true, insertable=true, updatable=true, columnDefinition="필드 설명(description)")
	private String formFieldDes;
	
	@Column(name = "REG_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="등록일")
	private Date regDate;
	
	@Column(name = "MOD_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="수정일")
	private Date modDate;
	
    @Column(name = "FORM_ITEM_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="폼 구성 항목 번호")
	private Integer formItemSeq;
    
    @JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_ITEM_SEQ", insertable=false, updatable=false)
    private FormItem formItem;
    
    @Builder(builderClassName = "ByFormFieldBuilder", builderMethodName = "ByAllBuilder")
	public FormField(String formFieldText, String formFieldValue, String formFiledDefault, String formFieldPhd, 
			String formFieldDes, Date regDate, Date modDate, Integer formItemSeq) {
		this.formFieldText = formFieldText;
		this.formFieldValue = formFieldValue;
		this.formFiledDefault = formFiledDefault;
		this.formFieldPhd = formFieldPhd;
		this.formFieldDes = formFieldDes;
		this.regDate = regDate;
		this.modDate = modDate;
		this.formItemSeq = formItemSeq;
	}
    
    public FormFieldDTO entityToDTO() {
    	FormFieldDTO formFieldDTO = new FormFieldDTO();
		ModelMapper mMapper = new ModelMapper();
		mMapper.map(this, formFieldDTO); 
		
		return formFieldDTO;
    }
}
