package com.formbuilder.formItem;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ItemFirstVO {
	private Integer itemIndex;
	
	private Integer itemSeq;
	
	@JsonProperty("itemJson")
	private ItemSecondVO itemJson;
}
