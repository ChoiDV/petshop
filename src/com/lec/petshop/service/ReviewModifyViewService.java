package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.ReviewDao;

public class ReviewModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		ReviewDao dao = ReviewDao.getInstance();
		request.setAttribute("ReviewContent", dao.modifyGetDto(rnum));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rnum", rnum);
		
		AdminDao adao = AdminDao.getInstance();
		request.setAttribute("adminList", adao.listAdmin());
	}

}
