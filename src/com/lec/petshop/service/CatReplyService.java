package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.Cat_ReplyDao;
import com.lec.petshop.dao.Dog_ReplyDao;
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.Cat_ReplyDto;
import com.lec.petshop.dto.Dog_ReplyDto;
import com.lec.petshop.dto.MemberDto;

public class CatReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		HttpSession session = request.getSession();
		String mid = ((MemberDto)session.getAttribute("member")).getMid();
		if(mid == null || mid.equals("")) {
			mid = ((AdminDto)session.getAttribute("admin")).getAid();
		}
		String rip = request.getRemoteAddr();
		String reply_content = request.getParameter("reply_content");
		Cat_ReplyDao dao = Cat_ReplyDao.getInstance();
		int result = dao.replyCat(new Cat_ReplyDto(0, 0, cnum, mid, reply_content, null, rip));
		if(result == Dog_ReplyDao.SUCCESS) {
			request.setAttribute("replyResult", result);
		} else {
			request.setAttribute("replyResult", result);
		}
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dnum", cnum);
		
	}

}
