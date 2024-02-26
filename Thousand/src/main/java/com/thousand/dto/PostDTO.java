package com.thousand.dto;

import java.sql.Timestamp;

public class PostDTO {
	private int pno;            //게시글 번호
	private String id;            //회원 아이디(작성자용)
	private String title;         //게시글 제목
	private String summary;         //게시글 요약
	private int categorycode;   //카테고리 번호 -> 카테고리 불러오기
	private String mainimg;         //메인사진
	private int readcount;         //게시글 전체 조회수
	private String[] content;      //본문내용1 -> 재료, 2~11 내용
	private String[] produceImg;      //본문내용 2~11까지의 사진
	private Timestamp postdate;      //게시글 작성 날짜
	private int rnum;

	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public PostDTO(){
		content = new String[11];
		produceImg = new String[10];
	}
	public String[] getContent() {
		return content;
	}
	public void setContent(String[] content) {
		this.content = content;
	}
	public String[] getProduceImg() {
		return produceImg;
	}
	public void setProduceImg(String[] produceImg) {
		this.produceImg = produceImg;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getCategorycode() {
		return categorycode;
	}
	public void setCategorycode(int categorycode) {
		this.categorycode = categorycode;
	}
	public String getMainimg() {
		return mainimg;
	}
	public void setMainimg(String mainimg) {
		this.mainimg = mainimg;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public Timestamp getPostdate() {
		return postdate;
	}
	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}

}