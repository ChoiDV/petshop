package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CatReplyDeleteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		request.setAttribute("rno", rno);
		request.setAttribute("cnum", cnum);
	}

}
