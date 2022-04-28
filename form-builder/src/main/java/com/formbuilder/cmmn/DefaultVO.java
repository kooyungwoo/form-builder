package com.formbuilder.cmmn;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultVO {
	/** 검색조건 */
	protected String searchColumn = "";

	/** 검색Keyword */
	protected String searchWord = "";

	 /** 정렬 컬럼 */
	protected String sortColumn = "";
    
    /** 정렬순서(DESC,ASC) */
	protected String sortOrdr = "DESC";
	
	protected String callDataType = "";
	
	protected String callResultType = "";
	
	private String workType = "";

	protected Integer page = 1;
	protected Integer row = 10;
	protected Integer count;
	protected Integer pages;
	protected Integer blocks;
	protected Integer block;
	protected Integer fPage;
	protected Integer lPage;
	
	protected int startIndex = 1;
	protected int endIndex = 1;
	

	public String getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	@JsonIgnore
	public int getStartIndex() {
		startIndex = (page-1)*(row+1);
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	@JsonIgnore
	public int getEndIndex() {
		endIndex = page * row;
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		if(page == null || page < 0){
			page = 1;
		}
		if(row == null || row < 0){
			row = 10;
		}
		//페이지 값 셋팅시 페이지 번호가 최대 데이터 개수를 넘길경우 -1 처리
		if(page>1 && count-(page*row) <= 0){
			page = page-1;
		}
		
		this.count = count;
		pages = (count == 0) ? 1 : (int) ((count - 1) / row) + 1;
		blocks = (int) Math.ceil(1.0 * pages / 10.0);
		block = (int) Math.ceil(1.0 * page / 10.0);
		fPage = (block - 1) * 10 + 1;
		lPage = block * 10;
		if (lPage > pages)
			lPage = pages;
		
		if(page > pages){
			page = pages;
		}
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getBlocks() {
		return blocks;
	}

	public void setBlocks(Integer blocks) {
		this.blocks = blocks;
	}

	public Integer getBlock() {
		return block;
	}

	public void setBlock(Integer block) {
		this.block = block;
	}

	public Integer getfPage() {
		return fPage;
	}

	public void setfPage(Integer fPage) {
		this.fPage = fPage;
	}

	public Integer getlPage() {
		return lPage;
	}

	public void setlPage(Integer lPage) {
		this.lPage = lPage;
	}
	
	public String getCallDataType() {
		return callDataType;
	}

	public void setCallDataType(String callDataType) {
		this.callDataType = callDataType;
	}

	public String getCallResultType() {
		return callResultType;
	}

	public void setCallResultType(String callResultType) {
		this.callResultType = callResultType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
