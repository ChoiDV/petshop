package com.lec.petshop.dto;

import java.sql.Date;

public class MemberDto {
	private String mid;
	private String mpw;
	private String mname;
	private String mtel;
	private String mbirth;   // 주번 6자리로 받을거라서
	private String memail;
	private String maddress;
	private String mgender;
	private Date mrdate;
	private int mwithd;
	
	public MemberDto(String mid, String mpw, String mname, String mtel, String mbirth, String memail, String maddress,
			String mgender) {
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mtel = mtel;
		this.mbirth = mbirth;
		this.memail = memail;
		this.maddress = maddress;
		this.mgender = mgender;
	}
	

	public MemberDto(String mid, String mpw, String mname, String mtel, String mbirth, String memail, String maddress,
			String mgender, Date mrdate, int mwithd) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mtel = mtel;
		this.mbirth = mbirth;
		this.memail = memail;
		this.maddress = maddress;
		this.mgender = mgender;
		this.mrdate = mrdate;
		this.mwithd = mwithd;
	}


	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMtel() {
		return mtel;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}

	public String getMbirth() {
		return mbirth;
	}

	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public String getMgender() {
		return mgender;
	}

	public void setMgender(String mgender) {
		this.mgender = mgender;
	}

	public Date getMrdate() {
		return mrdate;
	}

	public void setMrdate(Date mrdate) {
		this.mrdate = mrdate;
	}

	public int getMwithd() {
		return mwithd;
	}

	public void setMwithd(int mwithd) {
		this.mwithd = mwithd;
	}

	@Override
	public String toString() {
		return "MemberDto [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mtel=" + mtel + ", mbirth=" + mbirth
				+ ", memail=" + memail + ", maddress=" + maddress + ", mgender=" + mgender + ", mrdate=" + mrdate
				+ ", mwithd=" + mwithd + "]";
	}
	
	
	
	
	
	
	
	
}
