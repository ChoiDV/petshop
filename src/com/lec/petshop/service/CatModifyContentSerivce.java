package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dto.CatDto;
import com.lec.petshop.dto.DogDto;

public class CatModifyContentSerivce implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CatDao dao = CatDao.getInstance();
		CatDto cat = dao.catModifyContent(cnum);		
		request.setAttribute("breedlist", dao.breedList());
		request.setAttribute("catModifyContent", cat);
		request.setAttribute("pageNum", pageNum);
	}

}
