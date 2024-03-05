package com.thousand.service;

import com.thousand.dto.CategoryDTO;
import com.thousand.repository.CategoryRepository;
import com.thousand.repository.CategoryRepositoryImpl;

public class CategoryServiceImpl implements CategoryService {
	//singleton pattern
	private CategoryServiceImpl() {}
	private static CategoryServiceImpl instance = new CategoryServiceImpl();
	public static CategoryServiceImpl getInstance() {
		return instance;
	}	
	//Repo instance 생성
	CategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();

	@Override
	public CategoryDTO selectCategory(int categorycode) {
		CategoryDTO cDto;	//결과값 보낼 DTO생성
		cDto = categoryRepository.selectCategory(categorycode);
		return cDto;
	}
	//글내용 받아서 입력해주고 categorycode 리턴(postDto에 저장하기위해)
	@Override
	public int insertCategory(String recipe,String local, String item) {
		//글등록
		categoryRepository.insertCategory(recipe, local, item);
		//등록한 글 카테고리코드 받기
		int categorycode = choiceCategoryCode();
		return categorycode;
	}

	@Override
	public void updateCategory(CategoryDTO cDTO) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteCategory(CategoryDTO cDTO) {
		// TODO Auto-generated method stub
	}

	@Override
	public int choiceCategoryCode() {
		//방금 등록했던 카테고리 받아오기
		int categorycode = categoryRepository.choiceCategoryCode();
		return categorycode;
	}

}
