package com.thousand.dto;

public class SearchDTO {
	private String searchField;
	private String searchWord;
	private String pageTemp;
	public SearchDTO(){}
	public SearchDTO(String searchField, String searchWord, String pageTemp) {
		super();
		this.searchField = searchField;
		this.searchWord = searchWord;
		this.pageTemp = pageTemp;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getPageTemp() {
		return pageTemp;
	}
	public void setPageTemp(String pageTemp) {
		this.pageTemp = pageTemp;
	}
}
