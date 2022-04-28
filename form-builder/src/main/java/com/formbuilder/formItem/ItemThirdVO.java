package com.formbuilder.formItem;

import java.util.Date;

import com.formbuilder.domain.FormField;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ItemThirdVO {
	private String fieldText;
	private String fieldValue;
	private String fieldDes;
	private String fieldDefault;
	
	public FormField jsonToDomain(Integer formItemSeq, ItemSecondVO itemScondVO, Date toDay) {
		FormField formField = FormField.ByAllBuilder().formFieldText(this.fieldText)
				.formFieldValue(this.fieldValue)
				.formFiledDefault(this.fieldDefault)
				.formFieldPhd(itemScondVO.getFieldPhd())
				.formFieldDes(itemScondVO.getItemDes())
				.regDate(toDay)
				.modDate(toDay)
				.formItemSeq(formItemSeq).build();
		return formField;
    }
	
	public FormField entityToDTO() {
		return new FormField();
	}
}
