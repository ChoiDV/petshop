package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dto.AdminDto;

public class AdminJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		String apw = request.getParameter("apw");
		String aname = request.getParameter("aname");
		AdminDao dao = AdminDao.getInstance();
		AdminDto admin = new AdminDto(aid, apw, aname);
		int result = dao.joinAdmin(admin);
		if(result == AdminDao.SUCCESS) {
			request.setAttribute("adminJoinResult", admin.getAname()+" 관리자 등록 성공");
		} else {
			request.setAttribute("adminJoinError", "관리자 등록 실패(중복된 관리자 아이디)");
		}
	}

}
