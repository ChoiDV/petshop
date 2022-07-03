package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.CatDao;

public class CatBreedService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CatDao dao = CatDao.getInstance();
		request.setAttribute("breedlist", dao.breedList());
	}

}
