package com.lec.petshop.dto;

public class CbreedDto {
	private int cbreedno;
	private String cbreedname;
	
	public CbreedDto(int cbreedno, String cbreedname) {
		super();
		this.cbreedno = cbreedno;
		this.cbreedname = cbreedname;
	}

	public int getCbreedno() {
		return cbreedno;
	}

	public void setCbreedno(int cbreedno) {
		this.cbreedno = cbreedno;
	}

	public String getCbreedname() {
		return cbreedname;
	}

	public void setCbreedname(String cbreedname) {
		this.cbreedname = cbreedname;
	}

	@Override
	public String toString() {
		return "CbreedDto [cbreedno=" + cbreedno + ", cbreedname=" + cbreedname + "]";
	}
	
	
	
	
	
}
