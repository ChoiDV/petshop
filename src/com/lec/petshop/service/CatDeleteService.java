package com.lec.petshop.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dao.CatZimDao;
import com.lec.petshop.dao.Cat_ReplyDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.Cat_ReplyDto;

public class CatDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CatDao dao = CatDao.getInstance();
		Cat_ReplyDao rdao = Cat_ReplyDao.getInstance();
		CatZimDao zdao = CatZimDao.getInstance();
		zdao.deleteAllZim(cnum);
		ArrayList<Cat_ReplyDto> dtos = rdao.replyListCat(cnum);
		for(Cat_ReplyDto reply : dtos ) {
			rdao.deleteCReply(reply.getRno());
		}
		int result = dao.deleteCat(cnum);
		if(result == CatDao.SUCCESS) {
			request.setAttribute("deleteResult", result);
		} else {
			request.setAttribute("deleteResult", result);
		}
		request.setAttribute("pageNum", pageNum);
	}
}
