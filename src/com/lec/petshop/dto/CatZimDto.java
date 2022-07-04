package com.lec.petshop.dto;

public class CatZimDto {
	private int cnum;
	private String cname;
	private String cimage1;
	private String cbreedname;
	
	public CatZimDto(int cnum, String cname, String cimage1, String cbreedname) {
		super();
		this.cnum = cnum;
		this.cname = cname;
		this.cimage1 = cimage1;
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

	public String getCimage1() {
		return cimage1;
	}

	public void setCimage1(String cimage1) {
		this.cimage1 = cimage1;
	}

	public String getCbreedname() {
		return cbreedname;
	}

	public void setCbreedname(String cbreedname) {
		this.cbreedname = cbreedname;
	}

	@Override
	public String toString() {
		return "CatZimDto [cnum=" + cnum + ", cname=" + cname + ", cimage1=" + cimage1 + ", cbreedname=" + cbreedname
				+ "]";
	}
	
	
	
	
}
