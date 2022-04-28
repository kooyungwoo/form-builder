package com.formbuilder.dto;

import java.util.Date;

import com.formbuilder.domain.FormAuth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FormAuthDTO{
	
	private Integer authSeq;
	private String authName;
	private String authList;
	private String authWrite;
	private String authView;
	private String authFileDown;
	private Date regDate;
	private Date modDate;
	private Integer formSetSeq;
    
	public FormAuth dtoToEntity() {
		FormAuth formAuth = FormAuth.ByAllBuilder().authName(this.authName)
																				.authList(this.authList)
																				.authWrite(this.authWrite)
																				.authView(this.authView)
																				.authFileDown(this.authFileDown)
																				.regDate(this.regDate)
																				.modDate(this.modDate)
																				.formSetSeq(this.formSetSeq).build();
		return formAuth;
    }
}
