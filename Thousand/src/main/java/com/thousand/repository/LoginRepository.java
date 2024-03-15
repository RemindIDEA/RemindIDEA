package com.thousand.repository;

import com.thousand.dto.MemberDTO;

public interface LoginRepository {
	// 회원 가입
	public int createMember(MemberDTO mDTO);
	// 회원 정보 가져오기
	public MemberDTO getMember(String id);
	// 회원 정보 수정
	public int updateMember(MemberDTO mDTO);
	// 회원 탈퇴
	public void deleteMember(String id);
	// 로그인 회원정보 확인
	public int selectMember(String id, String pw);
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
