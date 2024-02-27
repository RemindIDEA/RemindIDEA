package com.thousand.repository;

import com.thousand.dto.MemberDTO;

public class LoginRepositoryImpl implements LoginRepository{

	@Override
	public int createMember(MemberDTO mDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectMember(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
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
