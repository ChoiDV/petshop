package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewDeleteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rnum", rnum);
	}

}
