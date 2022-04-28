package com.formbuilder.dto;

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
public class FormFieldDTO{
	private Integer formFieldSeq;
	private String formFieldText;
	private String formFieldValue;
	private String formFiledDefault;
	private String formFieldPhd;
	private String formFieldDes;
	private Date regDate;
	private Date modDate;
	private Integer formItemSeq;
	
	public FormField dtoToEntity() {
		FormField formField = FormField.ByAllBuilder().formFieldText(this.formFieldText).formFieldValue(this.formFieldValue).formFiledDefault(this.formFiledDefault)
				.formFieldPhd(this.formFieldPhd).formFieldDes(this.formFieldDes).regDate(this.regDate).modDate(this.modDate).formItemSeq(this.formItemSeq).build();
		return formField;
    }
}
