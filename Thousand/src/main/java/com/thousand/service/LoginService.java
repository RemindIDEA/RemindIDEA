package com.thousand.service;

import com.thousand.dto.MemberDTO;
import com.thousand.enums.LoginResult;

public interface LoginService {
	// 회원 가입
	public int createMember(MemberDTO mDTO);
<<<<<<< Updated upstream
	// 회원 조회
	public int selectMember(String id, String pw);
	// 회원 정보 수정
	public int updateMember(MemberDTO mDTO);
	// 회원 탈퇴
	public void deleteMember(MemberDTO mDTO);
=======
	// 회원정보 가져오기
	public MemberDTO getMember(String id);
	// 회원 정보 수정
	public void updateMember(MemberDTO mDTO);
	// 회원 탈퇴
	public void deleteMember(String id);
	// 로그인 회원 조회
	public LoginResult validateMember(String id, String pw);
>>>>>>> Stashed changes
	// id 중복 체크
	public int confirmId(String id);
	// nickname 중복 체크
	public int confirmNickname(String nickname);
	// 마이페이지 접근 시, pw 확인
	public int checkPw(String id, String pw);
	// 아이디 찾기
	public String searchId(String nickname);
	// 비밀번호 찾기
	public String searchPw(String id, String nickname);
}
