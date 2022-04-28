package com.formbuilder.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formbuilder.dto.FormItemDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "FORM_ITEM")
public class FormItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "FORM_ITEM_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="폼 구성 항목 번호")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer formItemSeq;
	
	@Column(name = "FORM_ITEM_NAME", length=200, nullable=true, insertable=true, updatable=true, columnDefinition="항목 명")
	private String formItemName;
	
	@Column(name = "FORM_ITEM_TYPE", length=2, nullable=true, insertable=true, updatable=true, columnDefinition="항목 타입(10,20,30)")
	private String formItemType;
	
	@Column(name = "FORM_ITEM_LIST", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="목록 출력 여부")
	private String formItemList;
	
	@Column(name = "FORM_ITEM_LIST_ORDER", nullable=true, insertable=true, updatable=true, columnDefinition="목록 출력 순서")
	private Integer formItemListOrder;
	
	@Column(name = "FORM_ITEM_VIEW", length=1, nullable=true, insertable=true, updatable=true, columnDefinition="입력(보기) 출력 여부")
	private String formItemView;
	
	@Column(name = "FORM_ITEM_VIEW_ORDER", nullable=true, insertable=true, updatable=true, columnDefinition="입력(보기) 출력 순서")
	private Integer formItemViewOrder;
	
	@Column(name = "FORM_ITEM_VIEW_TYPE", length=10, nullable=true, insertable=true, updatable=true, columnDefinition="출력 형식(수평-hor,수직-ver)")
	private String formItemViewType;
	
	@Column(name = "FORM_ITEM_DES", length=500, nullable=true, insertable=true, updatable=true, columnDefinition="항목 설명(description)")
	private String formItemDes;
	
	@Column(name = "REG_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="등록일")
	private Date regDate;
	
	@Column(name = "MOD_DATE", nullable=true, insertable=true, updatable=true, columnDefinition="수정일")
	private Date modDate;
	
    @Column(name = "FORM_SET_SEQ", nullable=false, insertable=true, updatable=false, columnDefinition="폼게시판 번호")
	private Integer formSetSeq;
    
    @Column(name = "TB_COLUMN_NAME", length=100, nullable=true, insertable=true, updatable=true, columnDefinition="테이블 컬럼 이름(자동생성)")
	private String tbColumnName;
    
    @Column(name = "ITEM_HTML", length=800, nullable=true, insertable=true, updatable=true, columnDefinition="항목 구성 HTML")
	private String itemHtml;
    
    @Column(name = "ITEM_JSON", length=500, nullable=true, insertable=true, updatable=true, columnDefinition="항목 구성 JSON")
	private String itemJson;
    
    @JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_SET_SEQ", insertable=false, updatable=false)
    private FormSet formSet;
    
    @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ITEM_SEQ")
	private List<FormField> formFieldList = new ArrayList<FormField>();
    
    @Builder(builderClassName = "ByFormItemBuilder", builderMethodName = "ByAllBuilder")
	public FormItem(String formItemName, String formItemType, String formItemList, 
								Integer formItemListOrder, String formItemView, Integer formItemViewOrder, String formItemViewType, 
								String formItemDes, Date regDate, Date modDate, Integer formSetSeq, String tbColumnName,
								String itemHtml, String itemJson) {
		this.formItemName = formItemName;
		this.formItemType = formItemType;
		this.formItemList = formItemList;
		this.formItemListOrder = formItemListOrder;
		this.formItemView = formItemView;
		this.formItemViewOrder = formItemViewOrder;
		this.formItemViewType = formItemViewType;
		this.formItemDes = formItemDes;
		this.regDate = regDate;
		this.modDate = modDate;
		this.formSetSeq = formSetSeq;
		this.tbColumnName = tbColumnName;
		this.itemHtml = itemHtml;
		this.itemJson = itemJson;
	}
    
    public void modFormItem(String formItemName, String formItemList, 
			String formItemView, Integer formItemViewOrder, String formItemViewType, 
			String formItemDes, Date modDate, String itemHtml, String itemJson) {
    	this.formItemName = formItemName;
		this.formItemList = formItemList;
		this.formItemView = formItemView;
		this.formItemViewOrder = formItemViewOrder;
		this.formItemViewType = formItemViewType;
		this.formItemDes = formItemDes;
		this.modDate = modDate;
		this.itemHtml = itemHtml;
		this.itemJson = itemJson;
    }
    
    public void modFormItem(String formItemList, Integer formItemListOrder, Date modDate) {
    	this.formItemList = formItemList;
		this.formItemListOrder = formItemListOrder;
		this.modDate = modDate;
    }
    
    public FormItemDTO entityToDTO() {
    	FormItemDTO formItemDTO = new FormItemDTO();
		ModelMapper mMapper = new ModelMapper();
		mMapper.map(this, formItemDTO); 
		
		return formItemDTO;
    }
}
