package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.Cat_ReplyDao;
import com.lec.petshop.dao.Dog_ReplyDao;

public class CatReplyDeleteSerivice implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		Cat_ReplyDao dao = Cat_ReplyDao.getInstance();
		int result = dao.deleteCReply(rno);
		if(result == Cat_ReplyDao.SUCCESS) {
			request.setAttribute("CatReplyDeleteResult", result);
		} else {
			request.setAttribute("CatReplyDeleteResult", result);
		}
		request.setAttribute("cnum", cnum);
	}

}
