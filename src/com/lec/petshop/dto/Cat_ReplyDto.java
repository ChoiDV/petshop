package com.lec.petshop.dto;

import java.sql.Date;

public class Cat_ReplyDto {
	private int rn;
	private int rno;
	private int cnum;
	private String mid;
	private String reply_content;
	private Date rdate;
	private String rip;
	
	public Cat_ReplyDto(int rn, int rno, int cnum, String mid, String reply_content, Date rdate, String rip) {
		super();
		this.rn = rn;
		this.rno = rno;
		this.cnum = cnum;
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

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
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

	@Override
	public String toString() {
		return "Cat_ReplyDto [rn=" + rn + ", rno=" + rno + ", cnum=" + cnum + ", mid=" + mid + ", reply_content="
				+ reply_content + ", rdate=" + rdate + ", rip=" + rip + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
