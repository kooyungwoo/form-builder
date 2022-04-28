package com.formbuilder.cmmn.jsonConvert;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.formbuilder.formItem.ItemSecondVO;

public class TextHtmlParse implements HtmlParse {

	@Override
	public void htmlParse(ItemSecondVO itemJson, Document itemDoc, String tbColumnName) {
		//항목 명 변경
		Element inputTextlabel 	= itemDoc.select("label.col-form-label").first();
		inputTextlabel.text(itemJson.getItemName());
		
		//input text의 속성값 변경(이름, 기본값, 힌트, 제목)
		Element inputText			= itemDoc.select("input[type=text]").first();
		inputText.attr("name", tbColumnName);
		inputText.attr("value", StringUtils.defaultString(itemJson.getFiledDefault()));
		inputText.attr("placeholder", StringUtils.defaultString(itemJson.getFieldPhd()));
		inputText.attr("title", StringUtils.defaultString(itemJson.getItemDes()));
	}

}
