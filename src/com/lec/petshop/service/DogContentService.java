package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.DogDto;

public class DogContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		DogDao dao = DogDao.getInstance();
		DogDto dog = dao.dogContent(dnum);
		int age = dao.dogAge(dog.getDbirth(), dnum);
		int year = 0;
		int month = 0;
		if(age >13) {
			month = age%12;
			year = age/12;
		} else {
			month = age;
		}
		if(month == 0) {
			month = 1;
		}
		// 관리자 이름 보내주기
		AdminDao adao = AdminDao.getInstance();
		String aname = adao.getName(dog.getAid());
		request.setAttribute("dogContent", dog);
		request.setAttribute("dogAge", month);
		request.setAttribute("dogYear", year);
		request.setAttribute("aname", aname);
		request.setAttribute("pageNum", pageNum);
		
	}

}
