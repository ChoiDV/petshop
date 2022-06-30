package com.lec.petshop.dto;

import java.sql.Date;

public class Dog_ReservationDto {
	private String mid;
	private int dnum;
	private Date dr_date;
	private String dname;
	private String dgender;
	private Date dbirth;
	private int dprice;
	private String dbreedname;
	
	public Dog_ReservationDto(String mid, int dnum, Date dr_date, String dname, String dgender, Date dbirth, int dprice,
			String dbreedname) {
		super();
		this.mid = mid;
		this.dnum = dnum;
		this.dr_date = dr_date;
		this.dname = dname;
		this.dgender = dgender;
		this.dbirth = dbirth;
		this.dprice = dprice;
		this.dbreedname = dbreedname;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public Date getDr_date() {
		return dr_date;
	}

	public void setDr_date(Date dr_date) {
		this.dr_date = dr_date;
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

	public String getDbreedname() {
		return dbreedname;
	}

	public void setDbreedname(String dbreedname) {
		this.dbreedname = dbreedname;
	}

	@Override
	public String toString() {
		return "Dog_ReservationDto [mid=" + mid + ", dnum=" + dnum + ", dr_date=" + dr_date + ", dname=" + dname
				+ ", dgender=" + dgender + ", dbirth=" + dbirth + ", dprice=" + dprice + ", dbreedname=" + dbreedname
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
}
