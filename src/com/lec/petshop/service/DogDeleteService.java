package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.FreeBoardDto;

public class DogDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		DogDao dao = DogDao.getInstance();
		int result = dao.deleteDog(dnum);
		if(result >= FreeBoardDao.SUCCESS) {
			request.setAttribute("deleteResult", result);
		} else {
			request.setAttribute("deleteResult", result);
		}
		request.setAttribute("pageNum", pageNum);

	}

}
