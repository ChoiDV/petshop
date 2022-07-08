package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dao.ReviewDao;

public class ReviewReplyWriteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		ReviewDao dao = ReviewDao.getInstance();
		request.setAttribute("reply", dao.modifyGetDto(rnum));
		request.setAttribute("pageNum", pageNum);
	}

}
