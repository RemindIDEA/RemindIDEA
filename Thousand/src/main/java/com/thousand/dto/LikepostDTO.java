package com.thousand.dto;


public class LikepostDTO {
	private int likeit;			//좋아요 표시
	private String id;			//좋아요 누른 아이디 rk
	private int pno;			//좋아요 누른 게시물 rk
	
	public int getLikeit() {
		return likeit;
	}
	public void setLikeit(int likeit) {
		this.likeit = likeit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
		
	
}

