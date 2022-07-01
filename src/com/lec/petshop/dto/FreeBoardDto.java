package com.lec.petshop.dto;

import java.sql.Date;

public class FreeBoardDto {
	private int fnum;
	private String mid;
	private String ftitle;
	private String fcontent;
	private Date frdate;
	private int fhit;
	private int fgroup;
	private int fstep;
	private int findent;
	private String fip;
	private String ffilename1;
	private String ffilename2;
	private String ffilename3;
	private String mname;
	
	public FreeBoardDto(int fnum, String mid, String ftitle, String fcontent, Date frdate, int fhit, int fgroup,
			int fstep, int findent, String fip, String ffilename1, String ffilename2, String ffilename3, String mname) {
		this.fnum = fnum;
		this.mid = mid;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.frdate = frdate;
		this.fhit = fhit;
		this.fgroup = fgroup;
		this.fstep = fstep;
		this.findent = findent;
		this.fip = fip;
		this.ffilename1 = ffilename1;
		this.ffilename2 = ffilename2;
		this.ffilename3 = ffilename3;
		this.mname = mname;
	}

	public int getFnum() {
		return fnum;
	}

	public void setFnum(int fnum) {
		this.fnum = fnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getFtitle() {
		return ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	public String getFcontent() {
		return fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	public Date getFrdate() {
		return frdate;
	}

	public void setFrdate(Date frdate) {
		this.frdate = frdate;
	}

	public int getFhit() {
		return fhit;
	}

	public void setFhit(int fhit) {
		this.fhit = fhit;
	}

	public int getFgroup() {
		return fgroup;
	}

	public void setFgroup(int fgroup) {
		this.fgroup = fgroup;
	}

	public int getFstep() {
		return fstep;
	}

	public void setFstep(int fstep) {
		this.fstep = fstep;
	}

	public int getFindent() {
		return findent;
	}

	public void setFindent(int findent) {
		this.findent = findent;
	}

	public String getFip() {
		return fip;
	}

	public void setFip(String fip) {
		this.fip = fip;
	}

	public String getFfilename1() {
		return ffilename1;
	}

	public void setFfilename1(String ffilename1) {
		this.ffilename1 = ffilename1;
	}

	public String getFfilename2() {
		return ffilename2;
	}

	public void setFfilename2(String ffilename2) {
		this.ffilename2 = ffilename2;
	}

	public String getFfilename3() {
		return ffilename3;
	}

	public void setFfilename3(String ffilename3) {
		this.ffilename3 = ffilename3;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "FreeBoardDto [fnum=" + fnum + ", mid=" + mid + ", ftitle=" + ftitle + ", fcontent=" + fcontent
				+ ", frdate=" + frdate + ", fhit=" + fhit + ", fgroup=" + fgroup + ", fstep=" + fstep + ", findent="
				+ findent + ", fip=" + fip + ", ffilename1=" + ffilename1 + ", ffilename2=" + ffilename2
				+ ", ffilename3=" + ffilename3 + ", mname=" + mname + "]";
	}
	
	
	
	
	
	
	
}
