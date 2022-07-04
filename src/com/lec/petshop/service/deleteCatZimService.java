package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.CatZimDao;

public class deleteCatZimService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CatZimDao dao = CatZimDao.getInstance();
		int result = dao.deleteZim(mid, cnum);
		if(result == CatZimDao.SUCCESS) {
			request.setAttribute("noZimResult", result );
		} else {
			request.setAttribute("noZimResult", result );
		}
	}

}
