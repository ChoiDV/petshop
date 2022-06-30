package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.MemberDao;

public class MIdConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.confirmID(mid);
		if(result == MemberDao.EXISTENT) {
			request.setAttribute("idConfirmResult", "중복된 ID 입니다.");
		} else {
			request.setAttribute("idConfirmResult", "사용가능한 ID 입니다.");
		}
	}

}
