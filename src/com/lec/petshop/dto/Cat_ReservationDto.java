package com.lec.petshop.dto;

import java.sql.Date;

public class Cat_ReservationDto {
	private String mid;
	private int cnum;
	private Date cr_date;
	private String cname;
	private String cgender;
	private Date cbirth;
	private int cprice;
	private String cbreedname;
	
	public Cat_ReservationDto(String mid, int cnum, Date cr_date, String cname, String cgender, Date cbirth, int cprice,
			String cbreedname) {
		super();
		this.mid = mid;
		this.cnum = cnum;
		this.cr_date = cr_date;
		this.cname = cname;
		this.cgender = cgender;
		this.cbirth = cbirth;
		this.cprice = cprice;
		this.cbreedname = cbreedname;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
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

	public String getCbreedname() {
		return cbreedname;
	}

	public void setCbreedname(String cbreedname) {
		this.cbreedname = cbreedname;
	}

	@Override
	public String toString() {
		return "Cat_ReservationDto [mid=" + mid + ", cnum=" + cnum + ", cr_date=" + cr_date + ", cname=" + cname
				+ ", cgender=" + cgender + ", cbirth=" + cbirth + ", cprice=" + cprice + ", cbreedname=" + cbreedname
				+ "]";
	}
	
	
	
	
	
	

	
	
	
	
	
}
