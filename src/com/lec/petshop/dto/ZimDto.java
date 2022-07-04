package com.lec.petshop.dto;

import java.sql.Date;

public class ZimDto {
	private int dnum;
	private String dname;
	private String dimage1;
	private String dbreedname;
	
	public ZimDto(int dnum, String dname, String dimage1, String dbreedname) {
		super();
		this.dnum = dnum;
		this.dname = dname;
		this.dimage1 = dimage1;
		this.dbreedname = dbreedname;
	}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDimage1() {
		return dimage1;
	}

	public void setDimage1(String dimage1) {
		this.dimage1 = dimage1;
	}

	public String getDbreedname() {
		return dbreedname;
	}

	public void setDbreedname(String dbreedname) {
		this.dbreedname = dbreedname;
	}

	@Override
	public String toString() {
		return "ZimDto [dnum=" + dnum + ", dname=" + dname + ", dimage1=" + dimage1 + ", dbreedname=" + dbreedname
				+ "]";
	}
	
	
	
	
	
	
	
}
