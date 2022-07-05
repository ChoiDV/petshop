package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;

public class AdminDeleteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AdminDao dao = AdminDao.getInstance();
		request.setAttribute("adminList", dao.listAdmin());

	}

}
