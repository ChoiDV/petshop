package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.ZimDao;

public class insertZimService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		ZimDao dao = ZimDao.getInstance();
		int result = dao.insertZim(mid, dnum);
		if(result == ZimDao.SUCCESS) {
			request.setAttribute("zimResult", result);
		} else {
			request.setAttribute("zimResult", result);
		}
	}

}
