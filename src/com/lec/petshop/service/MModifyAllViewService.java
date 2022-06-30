package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.MemberDao;
import com.lec.petshop.dto.MemberDto;

public class MModifyAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String mid = ((MemberDto)session.getAttribute("member")).getMid();
		String mpw = request.getParameter("mpw");
		String modify = request.getParameter("chK");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.loginCheck(mid, mpw);	
		if(result == MemberDao.LOGIN_SUCCESS) {
			MemberDto dto = dao.loginSuccess(mid);
			if(modify.equals("all")) {
				request.setAttribute("modifyAllResult", dto );
			} else if(modify.equals("id")) {
				request.setAttribute("modifyId", dto);
			} else if(modify.equals("pw")) {
				request.setAttribute("modifyPw", dto);
			}
		}
		request.setAttribute("modify", modify);
	}
}
