package com.formbuilder.dto;

import java.util.Date;

import com.formbuilder.domain.FormItem;
import com.formbuilder.domain.addEnum.FormItemType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FormItemDTO{
	private Integer formItemSeq;
	private String formItemName;
	private String formItemType;
	private String formItemList;
	private Integer formItemListOrder;
	private String formItemView;
	private Integer formItemViewOrder;
	private String formItemViewType;
	private String formItemDes;
	private Date regDate;
	private Date modDate;
	private Integer formSetSeq;
	private String tbColumnName;
	private String itemHtml;
	private String itemJson;
	@Getter(AccessLevel.NONE)
	private String formItemTypeName;
	
	public FormItem dtoToEntity() {
		FormItem formItem = FormItem.ByAllBuilder().formItemName(this.formItemName)
																				.formItemType(this.formItemType)
																				.formItemList(this.formItemList)
																				.formItemListOrder(this.formItemListOrder)
																				.formItemView(this.formItemView)
																				.formItemViewOrder(this.formItemViewOrder)
																				.formItemViewType(this.formItemViewType)
																				.formItemDes(this.formItemDes)
																				.regDate(this.regDate)
																				.modDate(this.modDate)
																				.formSetSeq(this.formSetSeq)
																				.tbColumnName(this.tbColumnName)
																				.itemHtml(this.itemHtml)
																				.itemJson(this.itemJson).build();
		return formItem;
    }
	
	//DTO에서 type관련 데이터 추출시 해당 값의 name에 해당하는 정보를 얻기 위해 enum에서 데이터를 가지고 오게 xxxxName 변수를 추가
	public String getFormItemTypeName() {
		FormItemType formItemType = FormItemType.getEnumByCode(this.formItemType);
		if(formItemType!=null) {
			return formItemType.getValue();
		}else {
			return "-";
		}
	}
}
