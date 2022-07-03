package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.FreeBoardDao;

public class CatDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CatDao dao = CatDao.getInstance();
		int result = dao.deleteCat(cnum);
		if(result == CatDao.SUCCESS) {
			request.setAttribute("deleteResult", result);
		} else {
			request.setAttribute("deleteResult", result);
		}
		request.setAttribute("pageNum", pageNum);
	}
}
