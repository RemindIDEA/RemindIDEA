package com.thousand.repository;

import com.thousand.dto.CategoryDTO;

public interface CategoryRepository {
	// 카테고리 가져오기
	public CategoryDTO selectCategory(int categorycode);
	// 카테고리 입력
	public void insertCategory(String recipe,String local, String item);
	// 카테고리 수정
	public void updateCategory(CategoryDTO cDTO);
	//카테고리 삭제
	public void deleteCategory(CategoryDTO cDTO);
	// 카테고리 번호 가져오기(게시글 입력시 마지막 번호 리턴해주기)
	public int choiceCategoryCode();
}
