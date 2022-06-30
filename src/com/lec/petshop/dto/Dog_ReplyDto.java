package com.lec.petshop.dto;

import java.sql.Date;

public class Dog_ReplyDto {
	private int rn;
	private int rno;
	private int dnum;
	private String mid;
	private String reply_content;
	private Date rdate;
	private String rip;
	
	public Dog_ReplyDto(int rn, int rno, int dnum, String mid, String reply_content, Date rdate, String rip) {
		super();
		this.rn = rn;
		this.rno = rno;
		this.dnum = dnum;
		this.mid = mid;
		this.reply_content = reply_content;
		this.rdate = rdate;
		this.rip = rip;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public String getRip() {
		return rip;
	}

	public void setRip(String rip) {
		this.rip = rip;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
