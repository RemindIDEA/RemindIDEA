package com.thousand.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thousand.consts.QueryCollect;
import com.thousand.dto.MemberDTO;

import util.DBManager;

public class LoginRepositoryImpl implements LoginRepository{

	private LoginRepositoryImpl() {}
	private static LoginRepositoryImpl instance = new LoginRepositoryImpl();
	public static LoginRepositoryImpl getInstance(){
		return instance;
	}

	//회원가입
	@Override
	public int createMember(MemberDTO mDTO) {
		// TODO Auto-generated method stub
		return 0;
	}
	// 회원 정보가져오기
	public MemberDTO getMember(String id) {
		MemberDTO mDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.GET_MEMBER);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mDto = new MemberDTO();
				mDto.setId(rs.getString("id"));
				mDto.setPw(rs.getString("pw"));
				mDto.setEmail(rs.getString("email"));
				mDto.setNickname(rs.getString("nickname"));
				mDto.setJoindate(rs.getTimestamp("joindate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mDto;
	}
	// 회원 정보 수정
	@Override
	public int updateMember(MemberDTO mDTO) {
		Integer result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.UPDATE_MEMBER);
			pstmt.setString(1, mDTO.getPw());
			pstmt.setString(2, mDTO.getEmail());
			pstmt.setString(3, mDTO.getNickname());
			pstmt.setString(4, mDTO.getId());
			result = pstmt.executeUpdate();// 영향을 받은 행의 수 리턴.update하면 1행이 변경되므로 1을 리턴.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	@Override
	public void deleteMember(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.DELETE_MEMBER);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	//로그인 회원 확인
	@Override
	public int selectMember(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	//ID 중복 체크
	@Override
	public int confirmId(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int confirmNickname(String nickname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkPw(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchId(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchPw(String id, String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

}
