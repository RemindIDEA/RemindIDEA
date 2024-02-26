package com.thousand.dto;

public class CategoryDTO {
	private int categorycode;		//카테고리 번호 pk
	private String recipe;				//카테고리 분류 - 레시피별
	private String local;				//카테고리 분류 - 지역별
	private String item;				//카테고리 분류 - 재료별
	
	//getter and setter
	public int getCategorycode() {
		return categorycode;
	}
	public void setCategorycode(int categorycode) {
		this.categorycode = categorycode;
	}
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
}
