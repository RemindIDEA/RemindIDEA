package com.thousand.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thousand.consts.QueryCollect;
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.SELECT_CATEGORY);
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
	//글 등록시 카테고리 정보 등록하기
	@Override
	public void insertCategory(String recipe,String local, String item) {
		// 분류 3가지 집어넣기
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.INSERT_CATEGORY);
			pstmt.setString(1, recipe);
			pstmt.setString(2, local);
			pstmt.setString(3, item);
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	@Override
	public void updateCategory(CategoryDTO cDTO) {
		// 분류 3가지 수정 정보 넣어주기
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.UPDATE_CATEGORY);
			pstmt.setString(1, cDTO.getRecipe());
			pstmt.setString(2, cDTO.getLocal());
			pstmt.setString(3, cDTO.getItem());
			pstmt.setInt(4, cDTO.getCategorycode());
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	@Override
	public void deleteCategory(CategoryDTO cDTO) {
		String sql = "delete category where categorycode=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cDTO.getCategorycode());
			pstmt.executeUpdate();
			// 입력한 후에 바로 입력한 카테고리 코드 확인해서 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//최근 등록한 카테고리번호 받아오기(카테고리 등록후 바로실행)
	@Override
	public int choiceCategoryCode() {
		int categoryCode = -1; // 반환할 카테고리코드 확인
		String sql = "select categorycode from category order by categorycode desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 받아온 자료가 있을 시
				categoryCode = rs.getInt(1); // 첫번째 값만 받아오면 됨. 내림차순이기에 최신글은 가장 높은 숫자.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return categoryCode;
	}

}
