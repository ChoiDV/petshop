package com.lec.petshop.dto;

import java.sql.Date;

public class ReviewDto {
	private int rnum;
	private String mid;
	private String aid;
	private String rtitle;
	private String rcontent;
	private Date rrdate;
	private int rhit;
	private int rgroup;
	private int rstep;
	private int rindent;
	private String rip;
	private String rfilename1;
	private String rfilename2;
	private String rfilename3;
	private String mname;
	
	public ReviewDto(int rnum, String mid, String aid, String rtitle, String rcontent, Date rrdate, int rhit,
			int rgroup, int rstep, int rindent, String rip, String rfilename1, String rfilename2, String rfilename3,
			String mname) {
		super();
		this.rnum = rnum;
		this.mid = mid;
		this.aid = aid;
		this.rtitle = rtitle;
		this.rcontent = rcontent;
		this.rrdate = rrdate;
		this.rhit = rhit;
		this.rgroup = rgroup;
		this.rstep = rstep;
		this.rindent = rindent;
		this.rip = rip;
		this.rfilename1 = rfilename1;
		this.rfilename2 = rfilename2;
		this.rfilename3 = rfilename3;
		this.mname = mname;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getRtitle() {
		return rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public Date getRrdate() {
		return rrdate;
	}

	public void setRrdate(Date rrdate) {
		this.rrdate = rrdate;
	}

	public int getRhit() {
		return rhit;
	}

	public void setRhit(int rhit) {
		this.rhit = rhit;
	}

	public int getRgroup() {
		return rgroup;
	}

	public void setRgroup(int rgroup) {
		this.rgroup = rgroup;
	}

	public int getRstep() {
		return rstep;
	}

	public void setRstep(int rstep) {
		this.rstep = rstep;
	}

	public int getRindent() {
		return rindent;
	}

	public void setRindent(int rindent) {
		this.rindent = rindent;
	}

	public String getRip() {
		return rip;
	}

	public void setRip(String rip) {
		this.rip = rip;
	}

	public String getRfilename1() {
		return rfilename1;
	}

	public void setRfilename1(String rfilename1) {
		this.rfilename1 = rfilename1;
	}

	public String getRfilename2() {
		return rfilename2;
	}

	public void setRfilename2(String rfilename2) {
		this.rfilename2 = rfilename2;
	}

	public String getRfilename3() {
		return rfilename3;
	}

	public void setRfilename3(String rfilename3) {
		this.rfilename3 = rfilename3;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "ReviewDto [rnum=" + rnum + ", mid=" + mid + ", aid=" + aid + ", rtitle=" + rtitle + ", rcontent="
				+ rcontent + ", rrdate=" + rrdate + ", rhit=" + rhit + ", rgroup=" + rgroup + ", rstep=" + rstep
				+ ", rindent=" + rindent + ", rip=" + rip + ", rfilename1=" + rfilename1 + ", rfilename2=" + rfilename2
				+ ", rfilename3=" + rfilename3 + ", mname=" + mname + "]";
	}
	
	
	
	
	
}
