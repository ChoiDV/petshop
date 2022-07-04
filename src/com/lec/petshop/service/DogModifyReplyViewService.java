package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.Dog_ReplyDao;
import com.lec.petshop.dto.Dog_ReplyDto;

public class DogModifyReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rno = Integer.parseInt(request.getParameter("rno"));
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		String replyPageNum = request.getParameter("replyPageNum");
		Dog_ReplyDao dao = Dog_ReplyDao.getInstance();
		Dog_ReplyDto dto = dao.dogModifyReplyContent(rno);
		request.setAttribute("reply", dto);
		request.setAttribute("dnum", dnum);
		request.setAttribute("replyPageNum", replyPageNum);
	}

}
