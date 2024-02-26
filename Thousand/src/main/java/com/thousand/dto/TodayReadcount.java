package com.thousand.dto;

import java.sql.Timestamp;

public class TodayReadcount {
	private int tno;				//당일 조회수 seq
	private Timestamp today;		//저장 당일 날짜
	private int readcount;			//당일 조회수
	private int bno;				//게시글 참조키
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public Timestamp getToday() {
		return today;
	}
	public void setToday(Timestamp today) {
		this.today = today;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
}
