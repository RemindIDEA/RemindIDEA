package com.thousand.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thousand.consts.QueryCollect;
import com.thousand.dto.MemberDTO;
import com.thousand.enums.LoginResult;

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
		Integer result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.CREATE_MEMBER);
			pstmt.setString(1, mDTO.getId());
			pstmt.setString(2, mDTO.getPw());
			pstmt.setString(3, mDTO.getEmail());
			pstmt.setString(4, mDTO.getNickname());
			// 영향을 받은 행의 수 리턴.insert하면 1행이 추가되므로 1을 리턴.
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	@Override
<<<<<<< Updated upstream
	public int selectMember(String id, String pw) {
		Integer result = null; // result 기본값 -1
=======
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
	public LoginResult validateMember(String id, String pw) {
		LoginResult result = null; // result 기본값 -1
>>>>>>> Stashed changes
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// db연결
			conn = DBManager.getConnection();
			// 동적 쿼리 생성
			pstmt = conn.prepareStatement(QueryCollect.SELECT_MEMBER);
			// 동적쿼리에 id값 세팅
			pstmt.setString(1, id);
			// 쿼리문 실행
			rs = pstmt.executeQuery();
			// id 값 존재하면
			if (rs.next()) {
				// 비밀번호 값이 null이 아니다 그리고 입력한 pw워드가 맞다면
				if (rs.getString("pw") != null && rs.getString("pw").equals(pw)) {
					result = LoginResult.SUCCESS;
					// 비밀번호가 틀리면
				} else { result = LoginResult.PASSWORD_INCORRECT; }
				// id가 존재하지 않으면
			} else { result = LoginResult.USER_NOT_FOUND;}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { DBManager.close(conn, pstmt, rs); }
		// 1:pw일치, 0:pw불일치, -1:id없음
		return result;
	}

	@Override
	public int updateMember(MemberDTO mDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteMember(MemberDTO mDTO) {
		// TODO Auto-generated method stub
		
	}
	//ID 중복 체크
	@Override
	public int confirmId(String id) {
		Integer result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.CONFIRM_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // id가 있는 경우
				result = 1;
			} else { // id가 없는 경우
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	//닉네임 중복체크 여부
	@Override
	public int confirmNickname(String nickname) {
		Integer result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.CONFIRM_NICKNAME);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) { // nickname이 있는 경우
				result = 1;
			} else { // nickname이 없는 경우
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
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
