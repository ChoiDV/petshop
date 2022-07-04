package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.Dog_ReplyDto;
import com.lec.petshop.dto.FreeBoardDto;

public class FreeBoardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String fnumStr = request.getParameter("fnum");
		if(fnumStr == null ) {
			fnumStr = String.valueOf(request.getAttribute("fnum"));
		}
		int fnum = Integer.parseInt(fnumStr);
		FreeBoardDao dao = FreeBoardDao.getInstance();
		request.setAttribute("freeContent", dao.contentView(fnum));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("fnum", fnum);		
	}

}
