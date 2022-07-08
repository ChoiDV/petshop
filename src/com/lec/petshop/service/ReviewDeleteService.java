package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dao.ReviewDao;
import com.lec.petshop.dto.ReviewDto;

public class ReviewDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto dto = dao.modifyGetDto(rnum);
		int result = dao.deleteReview(dto.getRgroup(), dto.getRstep(), dto.getRindent());
		if(result >= FreeBoardDao.SUCCESS) {
			request.setAttribute("deleteResult", result);
		} else {
			request.setAttribute("deleteResult", result);
		}
		request.setAttribute("pageNum", pageNum);
	}

}
