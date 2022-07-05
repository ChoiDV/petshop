package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;

public class AdminDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		AdminDao dao = AdminDao.getInstance();
		int result = dao.deleteAdmin(aid);
		if(result == AdminDao.SUCCESS) {
			request.setAttribute("adminDeleteResult", result);
		} else {
			request.setAttribute("adminDeleteResult", result);
		}
	}

}
