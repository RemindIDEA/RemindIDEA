package com.thousand.service;

import com.thousand.dto.MemberDTO;
import com.thousand.enums.LoginResult;
//import com.thousand.enums.LoginResult;
import com.thousand.enums.SearchCheckResult;
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
	PostService postService = PostServiceImpl.getInstance();
	
	//회원가입하기
	@Override
	public int createMember(MemberDTO mDTO) {
		return 0;
	}

	//id로 회원정보 가져오기
	@Override
	public MemberDTO getMember(String id) {
		return loginRepo.getMember(id);
	}
	//회원정보 변경
	@Override
	public void updateMember(MemberDTO mDTO) {
		loginRepo.updateMember(mDTO);
	}
	//회원정보 삭제
	@Override
	public void deleteMember(String id) {
		//회원정보 삭제전 회원이 작성한 글 먼저 지워주기(FK 이슈)
		postService.deletePost(id);
		//회원 정보 삭제
		loginRepo.deleteMember(id);
	}
	//로그인시 회원 맞는지 여부 확인

	@Override
	public LoginResult validateMember(String id, String pw) {
		//받은 아이디 비번으로 회원정보 맞는지 확인하기.
		return loginRepo.validateMember(id, pw);
	}

	//아이디 중복 확인
	@Override
	public SearchCheckResult confirmId(String id) {
		//받은 아이디 중복여부 확인
		return loginRepo.confirmId(id);

	}

	@Override

	public SearchCheckResult confirmNickname(String nickname) {
		//받은 닉네임 중복여부 확인
		return loginRepo.confirmNickname(nickname);
	}

	@Override
	public SearchCheckResult checkPw(String id, String pw) {
		return loginRepo.checkPw(id, pw);
	}

	@Override
	public String searchId(String nickname) {
		return loginRepo.searchId(nickname);
	}

	@Override
	public String searchPw(String id, String nickname) {
		return loginRepo.searchPw(id, nickname);
	}

	




}
