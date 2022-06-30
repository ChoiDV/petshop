package com.lec.petshop.dto;

import java.sql.Date;

public class CatDto {
	private int cnum;
	private String cname;
	private String cgender;
	private Date cbirth;
	private int cprice;
	private int cbreedno;
	private String aid;
	private String ccontent;
	private String cimage1;
	private String cimage2;
	private String cimage3;
	private String cimage4;
	private String cimage5;
	private String cip;
	private int chit;
	private int cr_check;
	private Date crdate;
	private String cbreedname;
	
	public CatDto(int cnum, String cname, String cgender, Date cbirth, int cprice, int cbreedno, String aid,
			String ccontent, String cimage1, String cimage2, String cimage3, String cimage4, String cimage5, String cip,
			int chit, int cr_check, Date crdate, String cbreedname) {
		super();
		this.cnum = cnum;
		this.cname = cname;
		this.cgender = cgender;
		this.cbirth = cbirth;
		this.cprice = cprice;
		this.cbreedno = cbreedno;
		this.aid = aid;
		this.ccontent = ccontent;
		this.cimage1 = cimage1;
		this.cimage2 = cimage2;
		this.cimage3 = cimage3;
		this.cimage4 = cimage4;
		this.cimage5 = cimage5;
		this.cip = cip;
		this.chit = chit;
		this.cr_check = cr_check;
		this.crdate = crdate;
		this.cbreedname = cbreedname;
	}
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCgender() {
		return cgender;
	}
	public void setCgender(String cgender) {
		this.cgender = cgender;
	}
	public Date getCbirth() {
		return cbirth;
	}
	public void setCbirth(Date cbirth) {
		this.cbirth = cbirth;
	}
	public int getCprice() {
		return cprice;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	public int getCbreedno() {
		return cbreedno;
	}
	public void setCbreedno(int cbreedno) {
		this.cbreedno = cbreedno;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCimage1() {
		return cimage1;
	}
	public void setCimage1(String cimage1) {
		this.cimage1 = cimage1;
	}
	public String getCimage2() {
		return cimage2;
	}
	public void setCimage2(String cimage2) {
		this.cimage2 = cimage2;
	}
	public String getCimage3() {
		return cimage3;
	}
	public void setCimage3(String cimage3) {
		this.cimage3 = cimage3;
	}
	public String getCimage4() {
		return cimage4;
	}
	public void setCimage4(String cimage4) {
		this.cimage4 = cimage4;
	}
	public String getCimage5() {
		return cimage5;
	}
	public void setCimage5(String cimage5) {
		this.cimage5 = cimage5;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public int getChit() {
		return chit;
	}
	public void setChit(int chit) {
		this.chit = chit;
	}
	public int getCr_check() {
		return cr_check;
	}
	public void setCr_check(int cr_check) {
		this.cr_check = cr_check;
	}
	public Date getCrdate() {
		return crdate;
	}
	public void setCrdate(Date crdate) {
		this.crdate = crdate;
	}
	public String getCbreedname() {
		return cbreedname;
	}
	public void setCbreedname(String cbreedname) {
		this.cbreedname = cbreedname;
	}
	@Override
	public String toString() {
		return "CatDto [cnum=" + cnum + ", cname=" + cname + ", cgender=" + cgender + ", cbirth=" + cbirth + ", cprice="
				+ cprice + ", cbreedno=" + cbreedno + ", aid=" + aid + ", ccontent=" + ccontent + ", cimage1=" + cimage1
				+ ", cimage2=" + cimage2 + ", cimage3=" + cimage3 + ", cimage4=" + cimage4 + ", cimage5=" + cimage5
				+ ", cip=" + cip + ", chit=" + chit + ", cr_check=" + cr_check + ", crdate=" + crdate + ", cbreedname="
				+ cbreedname + "]";
	}
	
	

	
	
	
	
}
