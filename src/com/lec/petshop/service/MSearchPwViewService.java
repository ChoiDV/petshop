package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MSearchPwViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		if(mid != null) {
			request.setAttribute("mid", mid);
		}
	}

}
