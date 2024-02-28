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

	@Override
	public void insertCategory(CategoryDTO cDTO) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
	}

}
