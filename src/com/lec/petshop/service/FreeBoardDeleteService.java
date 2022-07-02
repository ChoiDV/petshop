package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.FreeBoardDto;

public class FreeBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		FreeBoardDao dao = FreeBoardDao.getInstance();
		FreeBoardDto dto = dao.modifyView_replyView(fnum);
		int result = dao.delete(dto.getFgroup(), dto.getFstep(), dto.getFindent());
		if(result >= FreeBoardDao.SUCCESS) {
			request.setAttribute("deleteResult", result);
		} else {
			request.setAttribute("deleteResult", result);
		}
		request.setAttribute("pageNum", pageNum);
	}

}
