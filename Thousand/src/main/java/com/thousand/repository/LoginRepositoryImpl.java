package com.thousand.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.thousand.consts.QueryCollect;
import com.thousand.dto.MemberDTO;

import com.thousand.enums.LoginResult;
import com.thousand.enums.SearchCheckResult;

import util.DBManager;

public class LoginRepositoryImpl implements LoginRepository {
	private LoginRepositoryImpl() {
	}

	private static LoginRepositoryImpl instance = new LoginRepositoryImpl();

	public static LoginRepositoryImpl getInstance() {
		return instance;
	}

	// 회원가입
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
		Connection conn = null;
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

	// 로그인 회원 확인
	@Override
	public LoginResult validateMember(String id, String pw) {
		LoginResult result = null; // result 기본값 -1
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
				} else {
					result = LoginResult.PASSWORD_INCORRECT;
				}
				// id가 존재하지 않으면
			} else {
				result = LoginResult.USER_NOT_FOUND;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		// 1:pw일치, 0:pw불일치, -1:id없음
		return result;
	}

	// ID 중복 체크
	@Override
	public SearchCheckResult confirmId(String id) {
		SearchCheckResult result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.CONFIRM_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // id가 있는 경우
				result = SearchCheckResult.SUCCESS;
			} else { // id가 없는 경우
				result = SearchCheckResult.FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	// 닉네임 중복체크 여부
	@Override
	public SearchCheckResult confirmNickname(String nickname) {
		SearchCheckResult result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.CONFIRM_NICKNAME);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) { // nickname이 있는 경우
				result = SearchCheckResult.SUCCESS;
			} else { // nickname이 없는 경우
				result = SearchCheckResult.FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	@Override
	public SearchCheckResult checkPw(String id, String pw) {
		SearchCheckResult result = null; // 결과값 초기화: 실패로 설정
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection(); // db연결
			pstmt = conn.prepareStatement(QueryCollect.CHECK_PW); // 동적쿼리생성
			pstmt.setString(1, id); // 동적 쿼리에 비밀번호 매개변수 설정
			pstmt.setString(2, pw); // 동적 쿼리에 비밀번호 매개변수 설정
			rs = pstmt.executeQuery(); // 쿼리 실행 및 결과셋 획득
			if (rs.next()) { // 비밀번호 성공실패
				result = SearchCheckResult.SUCCESS; // 성공은 1
			} else { // 실패는 -1
				result = SearchCheckResult.FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	// 아이디찾기
	@Override
	public String searchId(String nickname) {
		String id = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.SEARCH_ID);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return id;
	}

	// 비밀번호 찾기
	@Override
	public String searchPw(String id, String nickname) {
		String password = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(QueryCollect.SEARCH_PW);
			pstmt.setString(1, id);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString("pw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return password;
	}

}
