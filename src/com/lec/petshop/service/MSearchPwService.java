package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.MemberDao;

public class MSearchPwService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mname = request.getParameter("mname");
		String mbirth = request.getParameter("mbirth");
		MemberDao dao = MemberDao.getInstance();
		request.setAttribute("searchPwResult", dao.searchPw(mid, mname, mbirth));
		
		
	}

}
