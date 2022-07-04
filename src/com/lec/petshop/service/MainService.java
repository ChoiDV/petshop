package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dao.DogDao;

public class MainService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DogDao ddao = DogDao.getInstance();
		request.setAttribute("mainDogList", ddao.hitListDog());
		
		CatDao cdao = CatDao.getInstance();
		request.setAttribute("mainCatList", cdao.hitListCat());
	}

}
