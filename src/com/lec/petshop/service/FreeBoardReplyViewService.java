package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;

public class FreeBoardReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		FreeBoardDao dao = FreeBoardDao.getInstance();
		request.setAttribute("reply", dao.modifyView_replyView(fnum));
		
	}

}
