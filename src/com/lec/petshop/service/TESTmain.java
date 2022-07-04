package com.lec.petshop.service;

import java.util.ArrayList;

public class TESTmain {
	public static void main(String[] args) {
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add("가");arr1.add("나");arr1.add("다");
		ArrayList<String> arr2 = new ArrayList<String>();
		arr2.add("마");arr2.add("카");arr2.add("하");
		arr1.addAll(arr2);
		System.out.println(arr1);
	}
}
