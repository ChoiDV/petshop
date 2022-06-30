package com.lec.petshop.dto;

public class DbreedDto {
	private int dbreedno;
	private String dbreedname;
	
	public DbreedDto(int dbreedno, String dbreedname) {
		super();
		this.dbreedno = dbreedno;
		this.dbreedname = dbreedname;
	}

	public int getDbreedno() {
		return dbreedno;
	}

	public void setDbreedno(int dbreedno) {
		this.dbreedno = dbreedno;
	}

	public String getDbreedname() {
		return dbreedname;
	}

	public void setDbreedname(String dbreedname) {
		this.dbreedname = dbreedname;
	}

	@Override
	public String toString() {
		return "DbreedDto [dbreedno=" + dbreedno + ", dbreedname=" + dbreedname + "]";
	}
	
	
	
	
}
