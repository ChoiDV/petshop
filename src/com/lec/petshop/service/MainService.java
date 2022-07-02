package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.DogDao;

public class MainService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DogDao dao = DogDao.getInstance();
		request.setAttribute("mainList", dao.hitListDog());
	}

}
