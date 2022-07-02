package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DogDeleteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dnum", dnum);
	}

}
