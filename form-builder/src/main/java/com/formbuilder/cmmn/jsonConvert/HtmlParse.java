package com.formbuilder.cmmn.jsonConvert;

import org.jsoup.nodes.Document;

import com.formbuilder.formItem.ItemSecondVO;

public interface HtmlParse {
	public void htmlParse(ItemSecondVO itemJson, Document itemDoc, String tbColumnName);
}
