package com.thousand.service;

import com.thousand.dto.MemberDTO;

public class LoginServiceImpl implements LoginService {
	@Override
	public int createMember(MemberDTO mDTO) {
		return 0;
	}
	@Override
	public int selectMember(String id, String pw) {
		return 0;
	}

	@Override
	public int updateMember(MemberDTO mDTO) {
		return 0;
	}

	@Override
	public void deleteMember(MemberDTO mDTO) {
		
	}

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
