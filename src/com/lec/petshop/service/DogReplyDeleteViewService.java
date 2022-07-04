package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DogReplyDeleteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		request.setAttribute("rno", rno);
		request.setAttribute("dnum", dnum);
	}

}
