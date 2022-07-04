package com.lec.petshop.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.Dog_ReplyDao;
import com.lec.petshop.dto.Dog_ReplyDto;

public class DogDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		DogDao dao = DogDao.getInstance();		
		Dog_ReplyDao rdao = Dog_ReplyDao.getInstance();
		ArrayList<Dog_ReplyDto> dtos = rdao.replyListDog(dnum);
		for(Dog_ReplyDto reply : dtos ) {
			rdao.deleteDReply(reply.getRno());
		}
		int result = dao.deleteDog(dnum);
		if(result == DogDao.SUCCESS) {
			request.setAttribute("deleteResult", result);
		} else {
			request.setAttribute("deleteResult", result);
		}
		request.setAttribute("pageNum", pageNum);

	}

}
