package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.FreeBoardDto;

public class FreeBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		FreeBoardDao dao = FreeBoardDao.getInstance();
		request.setAttribute("freeBoardContent", dao.modifyView_replyView(fnum));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("fnum", fnum);
	}

}
