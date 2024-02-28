package com.thousand.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thousand.dto.CategoryDTO;

import util.DBManager;

public class CategoryRepositoryImpl implements CategoryRepository {
	//singleton pattern
	private CategoryRepositoryImpl(){}
	private static CategoryRepositoryImpl instance = new CategoryRepositoryImpl();
	public static CategoryRepositoryImpl getInstance() {
		return instance;
	}	
	//카테고리코드로 해당 카테고리 내용 전달
	@Override
	public CategoryDTO selectCategory(int categorycode) {
		CategoryDTO cDto =new CategoryDTO();
		String sql = "select * from category where categorycode=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categorycode);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 받아온 자료가 있을 시
				cDto.setCategorycode(categorycode);
				cDto.setRecipe(rs.getString("recipe"));
				cDto.setLocal(rs.getString("local"));
				cDto.setItem(rs.getString("item"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
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
