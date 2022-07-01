package com.lec.petshop.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dto.FreeBoardDto;

public class FreeBoardListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum.equals("")) {
			if(request.getAttribute("pageNum")==null) {
				pageNum = "1";
			}else {
				pageNum = String.valueOf(request.getAttribute("pageNum"));
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10; 
		final int BLOCKSIZE = 5;
		int startRow = (currentPage -1 ) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE -1;
		FreeBoardDao dao = FreeBoardDao.getInstance();
		request.setAttribute("freeList", dao.listBoard(startRow, endRow));
		int totalCnt = dao.getBoardTotalCnt();
		int pageCnt = (int)Math.ceil((double)totalCnt/PAGESIZE); 
		int startPage = ((currentPage -1 )/BLOCKSIZE) * BLOCKSIZE +1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		
	}

}
