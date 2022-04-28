package com.formbuilder.cmmn.jsonConvert;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.formbuilder.formItem.ItemSecondVO;
import com.formbuilder.formItem.ItemThirdVO;

public class RadioHtmlParse implements HtmlParse {

	@Override
	public void htmlParse(ItemSecondVO itemJson, Document itemDoc, String tbColumnName) {
		//항목 명 변경
		Element inputTextlabel 	= itemDoc.select("label.col-form-label").first();
		inputTextlabel.text(itemJson.getItemName());
		
		Element divElement				= itemDoc.select("div.col-form-input").first();
		List<ItemThirdVO> fieldDatas 	= itemJson.getFieldDatas();
		for(ItemThirdVO fieldInfo:fieldDatas) {
			Element inputElement = new Element("input");
			inputElement.attr("type", "radio");
			inputElement.attr("name", tbColumnName);
			inputElement.attr("class", "form-check-input");
			inputElement.attr("value", fieldInfo.getFieldValue());
			if(StringUtils.equals(fieldInfo.getFieldDefault(), "Y")) {
				inputElement.attr("checked", true);
			}
			
			divElement.appendChild(inputElement);
			
			Element labelElement = new Element("label");
			inputElement.attr("class", "form-check-label");
			inputElement.text(fieldInfo.getFieldText());
			
			divElement.appendChild(labelElement);
		}

	}

}
