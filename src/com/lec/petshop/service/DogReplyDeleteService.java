package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.Dog_ReplyDao;

public class DogReplyDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		Dog_ReplyDao dao = Dog_ReplyDao.getInstance();
		int result = dao.deleteDReply(rno);
		if(result == Dog_ReplyDao.SUCCESS) {
			request.setAttribute("DogReplyDeleteResult", result);
		} else {
			request.setAttribute("DogReplyDeleteResult", result);
		}
		request.setAttribute("dnum", dnum);
	}

}
