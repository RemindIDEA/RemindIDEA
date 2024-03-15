package com.thousand.service;

import com.thousand.dto.MemberDTO;
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
	public int selectMember(String id, String pw) {
		return 0;
	}

	//아이디 중복 확인
	@Override
	public int confirmId(String id) {
		return 0;
	}

	@Override
	public int confirmNickname(String nickname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkPw(String id, String pw) {
		return 0;
	}

	@Override
	public String searchId(String nickname) {
		return null;
	}

	@Override
	public String searchPw(String id, String nickname) {
		return null;
	}




}
