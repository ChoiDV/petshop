package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dao.DogDao;

public class CatAllService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 12;
		final int BLOCKSIZE = 10;
		int startRow = (currentPage - 1) * PAGESIZE +1;
		int endRow = startRow + PAGESIZE -1;
		CatDao dao = CatDao.getInstance();
		request.setAttribute("catList", dao.listCat(startRow, endRow));
		
		int totalCnt = dao.totalCat();
		int pageCnt = (int)Math.ceil((double)totalCnt/PAGESIZE);
		int startPage = ((currentPage - 1)/BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("pageNum", pageNum);
	}

}
