package com.thousand.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private String id;				//회원 아이디
	private String pw;				//회원 비밀번호
	private String email;			//회원 이메일
	private String nickname;		//회원 닉네임
	private Timestamp joindate;		//회원 가입날짜
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Timestamp getJoindate() {
		return joindate;
	}
	public void setJoindate(Timestamp joindate) {
		this.joindate = joindate;
	}
	
	

}
