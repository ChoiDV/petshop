package com.lec.petshop.dto;

import java.sql.Date;

public class DogDto {
	private int dnum;
	private String dname;
	private String dgender;
	private Date dbirth;
	private int dprice;
	private int dbreedno;
	private String aid;
	private String dcontent;
	private String dimage1;
	private String dimage2;
	private String dimage3;
	private String dimage4;
	private String dimage5;
	private String dip;
	private int dhit;
	private int dr_check;
	private Date drdate;
	private String dbreedname;
	
	public DogDto(int dnum, String dname, String dgender, Date dbirth, int dprice, int dbreedno, String aid,
			String dcontent, String dimage1, String dimage2, String dimage3, String dimage4, String dimage5, String dip,
			int dhit, int dr_check, Date drdate, String dbreedname) {
		super();
		this.dnum = dnum;
		this.dname = dname;
		this.dgender = dgender;
		this.dbirth = dbirth;
		this.dprice = dprice;
		this.dbreedno = dbreedno;
		this.aid = aid;
		this.dcontent = dcontent;
		this.dimage1 = dimage1;
		this.dimage2 = dimage2;
		this.dimage3 = dimage3;
		this.dimage4 = dimage4;
		this.dimage5 = dimage5;
		this.dip = dip;
		this.dhit = dhit;
		this.dr_check = dr_check;
		this.drdate = drdate;
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

	public String getDgender() {
		return dgender;
	}

	public void setDgender(String dgender) {
		this.dgender = dgender;
	}

	public Date getDbirth() {
		return dbirth;
	}

	public void setDbirth(Date dbirth) {
		this.dbirth = dbirth;
	}

	public int getDprice() {
		return dprice;
	}

	public void setDprice(int dprice) {
		this.dprice = dprice;
	}

	public int getDbreedno() {
		return dbreedno;
	}

	public void setDbreedno(int dbreedno) {
		this.dbreedno = dbreedno;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getDcontent() {
		return dcontent;
	}

	public void setDcontent(String dcontent) {
		this.dcontent = dcontent;
	}

	public String getDimage1() {
		return dimage1;
	}

	public void setDimage1(String dimage1) {
		this.dimage1 = dimage1;
	}

	public String getDimage2() {
		return dimage2;
	}

	public void setDimage2(String dimage2) {
		this.dimage2 = dimage2;
	}

	public String getDimage3() {
		return dimage3;
	}

	public void setDimage3(String dimage3) {
		this.dimage3 = dimage3;
	}

	public String getDimage4() {
		return dimage4;
	}

	public void setDimage4(String dimage4) {
		this.dimage4 = dimage4;
	}

	public String getDimage5() {
		return dimage5;
	}

	public void setDimage5(String dimage5) {
		this.dimage5 = dimage5;
	}

	public String getDip() {
		return dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}

	public int getDhit() {
		return dhit;
	}

	public void setDhit(int dhit) {
		this.dhit = dhit;
	}

	public int getDr_check() {
		return dr_check;
	}

	public void setDr_check(int dr_check) {
		this.dr_check = dr_check;
	}

	public Date getDrdate() {
		return drdate;
	}

	public void setDrdate(Date drdate) {
		this.drdate = drdate;
	}

	public String getDbreedname() {
		return dbreedname;
	}

	public void setDbreedname(String dbreedname) {
		this.dbreedname = dbreedname;
	}

	@Override
	public String toString() {
		return "DogDto [dnum=" + dnum + ", dname=" + dname + ", dgender=" + dgender + ", dbirth=" + dbirth + ", dprice="
				+ dprice + ", dbreedno=" + dbreedno + ", aid=" + aid + ", dcontent=" + dcontent + ", dimage1=" + dimage1
				+ ", dimage2=" + dimage2 + ", dimage3=" + dimage3 + ", dimage4=" + dimage4 + ", dimage5=" + dimage5
				+ ", dip=" + dip + ", dhit=" + dhit + ", dr_check=" + dr_check + ", drdate=" + drdate + ", dbreedname="
				+ dbreedname + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
