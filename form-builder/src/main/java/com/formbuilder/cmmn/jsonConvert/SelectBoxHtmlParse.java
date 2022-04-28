package com.formbuilder.cmmn.jsonConvert;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.formbuilder.formItem.ItemSecondVO;
import com.formbuilder.formItem.ItemThirdVO;

public class SelectBoxHtmlParse implements HtmlParse {

	@Override
	public void htmlParse(ItemSecondVO itemJson, Document itemDoc, String tbColumnName) {
		//항목 명 변경
		Element inputTextlabel 	= itemDoc.select("label.col-form-label").first();
		inputTextlabel.text(itemJson.getItemName());
		
		Element inputElement				= itemDoc.select("select").first();
		List<ItemThirdVO> fieldDatas 	= itemJson.getFieldDatas();
		for(ItemThirdVO fieldInfo:fieldDatas) {
			Element selectOption = new Element("option");
			selectOption.attr("value", fieldInfo.getFieldValue());
			selectOption.text(fieldInfo.getFieldText());
			if(StringUtils.equals(fieldInfo.getFieldDefault(), "Y")) {
				selectOption.attr("selected", true);
			}
			inputElement.appendChild(selectOption);
		}

	}

}
