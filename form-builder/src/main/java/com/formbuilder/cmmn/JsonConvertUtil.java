package com.formbuilder.cmmn;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.formbuilder.cmmn.jsonConvert.CheckBoxHtmlParse;
import com.formbuilder.cmmn.jsonConvert.FileHtmlParse;
import com.formbuilder.cmmn.jsonConvert.HtmlParse;
import com.formbuilder.cmmn.jsonConvert.RadioHtmlParse;
import com.formbuilder.cmmn.jsonConvert.SelectBoxHtmlParse;
import com.formbuilder.cmmn.jsonConvert.TextAreaHtmlParse;
import com.formbuilder.cmmn.jsonConvert.TextHtmlParse;
import com.formbuilder.formItem.ItemSecondVO;

public class JsonConvertUtil {
	private HtmlParse htmlParse = null;
	
	public String jsonToHtml(ItemSecondVO itemJson, String tbColumnName) throws Exception {
		String itemType 		= itemJson.getItemType();
		//itemType에 맞는 html정보를 호출해서 셋팅
		String itemHtml 		= callAccidentAreaInfo(itemType);
		
		//itemType에 맞는 htmlParsing 객체 생성
		setHtmlParse(itemType);
		
		Document itemDoc	= Jsoup.parse(itemHtml);
		//html 파싱
		htmlParse.htmlParse(itemJson, itemDoc, tbColumnName);
		
		return itemDoc.body().html();
	}
	
	private void setHtmlParse(String itemType) {
		if("text".equals(itemType)) {htmlParse = new TextHtmlParse();}
		else if("file".equals(itemType)) {htmlParse = new FileHtmlParse();}
		else if("textArea".equals(itemType)) {htmlParse = new TextAreaHtmlParse();}
		else if("selectBox".equals(itemType)) {htmlParse = new SelectBoxHtmlParse();}
		else if("checkBox".equals(itemType)) {htmlParse = new CheckBoxHtmlParse();}
		else if("radio".equals(itemType)) {htmlParse = new RadioHtmlParse();}
	}
	
	private String callAccidentAreaInfo(String itemType) throws ClientProtocolException, IOException {
		HttpClient client 				= HttpClientBuilder.create().build();
        HttpGet request 				= new HttpGet("http://localhost:8081/temp_html/"+itemType+".html");
        HttpResponse response 	= client.execute(request);
        HttpEntity entity 				= response.getEntity();
        
        String returnHtml				= EntityUtils.toString(entity, "UTF-8");
		return returnHtml;
	}
}
