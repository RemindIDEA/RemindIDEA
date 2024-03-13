package com.thousand.service;

import com.thousand.dto.MemberDTO;
import com.thousand.enums.LoginResult;
import com.thousand.repository.LoginRepository;
import com.thousand.repository.LoginRepositoryImpl;

public class LoginServiceImpl implements LoginService {
	//singleton pattern
	private LoginServiceImpl() {}
	private static LoginServiceImpl instance = new LoginServiceImpl();
	public static LoginServiceImpl getInstance() {
		return instance;
	}	
	//repo instance 생성
	LoginRepository loginRepo = LoginRepositoryImpl.getInstance();
	
	//회원가입하기
	@Override
	public int createMember(MemberDTO mDTO) {
		//회원가입 정보 받아서 등록하기
		return loginRepo.createMember(mDTO);
	}
	//회원정보 가져오기
	@Override
	public LoginResult validateMember(String id, String pw) {
		//받은 아이디 비번으로 회원정보 맞는지 확인하기.
		return loginRepo.validateMember(id, pw);
	}
	//회원정보 변경
	@Override
	public int updateMember(MemberDTO mDTO) {
		return 0;
	}
	//회원정보 삭제
	@Override
	public void deleteMember(MemberDTO mDTO) {
		
	}
	//아이디 중복 확인
	@Override
	public int confirmId(String id) {
		//받은 아이디 중복여부 확인
		return loginRepo.confirmId(id);
	}
	//닉네임 중복 확인
	@Override
	public int confirmNickname(String nickname) {
		//받은 닉네임 중복여부 확인
		return loginRepo.confirmNickname(nickname);
	}
	//비밀번호 확인(회원정보 수정용)
	@Override
	public int checkPw(String id, String pw) {
		return 0;
	}
	//아이디찾기
	@Override
	public String searchId(String nickname) {
		return null;
	}
	//비밀번호 찾기
	@Override
	public String searchPw(String id, String nickname) {
		return null;
	}

}
