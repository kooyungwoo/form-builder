package com.formbuilder.formItem;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ItemSecondVO {
	private String itemName;
	private Integer itemLiIndex;
	private String itemType;
	private String itemView;
	private String listView;
	private String filedDefault;
	private String fieldPhd;
	private String itemDes;
	@JsonProperty("fieldDatas")
	private List<ItemThirdVO> fieldDatas;
}
