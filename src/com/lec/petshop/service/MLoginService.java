package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.MemberDao;
import com.lec.petshop.dto.MemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.loginCheck(mid, mpw);
		if(result == MemberDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			MemberDto member = dao.loginSuccess(mid);
			session.setAttribute("member", member);
		} else if (result == MemberDao.LOGIN_FAIL){
			request.setAttribute("loginErrorMsg", "아이디와 비번을 확인하세요.");
		} else if (result == MemberDao.LEAVE_MEMBER) {
			request.setAttribute("loginErrorMsg", "탈퇴처리된 회원입니다.");
		}

	}

}
