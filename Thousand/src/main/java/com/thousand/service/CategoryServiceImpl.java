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

	//카테고리 세부내용 받아오기
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
	//기존 카테고리 내용 수정하기.
	@Override
	public void updateCategory(int categorycode, String recipe, String local, String item) {
		CategoryDTO cDTO = new CategoryDTO();
		cDTO.setCategorycode(categorycode);
		cDTO.setRecipe(recipe);
		cDTO.setLocal(local);
		cDTO.setItem(item);
		//받아온 정보로 내용 수정하기.
		categoryRepository.updateCategory(cDTO);
	}

	@Override
	public void deleteCategory(CategoryDTO cDTO) {
		// TODO Auto-generated method stub
	}

	//방금 등록했던 카테고리 받아오기
	@Override
	public int choiceCategoryCode() {
		int categorycode = categoryRepository.choiceCategoryCode();
		return categorycode;
	}

}
