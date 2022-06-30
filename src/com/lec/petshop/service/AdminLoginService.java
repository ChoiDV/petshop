package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dto.AdminDto;

public class AdminLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");
		AdminDao dao = AdminDao.getInstance();
		int result = dao.loginAdmin(aid, apw);
		if(result == AdminDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			AdminDto admin = dao.aLoginSuccess(aid);
			request.setAttribute("adminLoginResult", admin.getAname()+" 관리자님 안녕하세요^^");
			session.setAttribute("admin", admin);
		} else {
			request.setAttribute("adminLoginError", "관리자 아이디와 비밀번호를 확인해주세요.");
		}

	}

}
