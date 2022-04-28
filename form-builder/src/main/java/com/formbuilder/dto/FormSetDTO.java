package com.formbuilder.dto;

import java.util.Date;

import com.formbuilder.domain.FormSet;
import com.formbuilder.domain.addEnum.FormSetType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FormSetDTO{
	private Integer formSetSeq;
	private String formSetName;
	private String formSetType;
	private String formSetSkin;
	private String targetTableName;
	private Date regDate;
	private Date modDate;
	@Getter(AccessLevel.NONE)
	private String formSetTypeName;
	private String distComp;
	
	public FormSet dtoToEntity() {
		FormSet formSet = FormSet.ByAllBuilder().formSetName(this.formSetName).formSetType(this.formSetType).formSetSkin(this.formSetSkin)
				.targetTableName(this.targetTableName).regDate(this.regDate).modDate(this.modDate).distComp(distComp).build();
		return formSet;
    }

	//DTO에서 type관련 데이터 추출시 해당 값의 name에 해당하는 정보를 얻기 위해 enum에서 데이터를 가지고 오게 xxxxName 변수를 추가
	public String getFormSetTypeName() {
		FormSetType formSetType = FormSetType.getEnumByCode(this.formSetType);
		if(formSetType!=null) {
			return formSetType.getValue();
		}else {
			return "-";
		}
	}
}
