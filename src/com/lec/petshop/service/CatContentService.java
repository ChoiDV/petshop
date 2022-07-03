package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dao.Cat_ReplyDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.Dog_ReplyDao;
import com.lec.petshop.dto.CatDto;
import com.lec.petshop.dto.DogDto;

public class CatContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CatDao dao = CatDao.getInstance();
		CatDto cat = dao.catContent(cnum);		
		int age = dao.catAge(cat.getCbirth(), cnum);
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
		String aname = adao.getName(cat.getAid());
		request.setAttribute("catContent", cat);
		request.setAttribute("catAge", month);
		request.setAttribute("catYear", year);
		request.setAttribute("aname", aname);
		request.setAttribute("pageNum", pageNum);
		
		// 댓글
		String replyPageNum = request.getParameter("replyPageNum");
		if(replyPageNum == null || replyPageNum.equals("")) {
			if(request.getAttribute("replyPageNum")==null) {
				replyPageNum = "1";
			}else {
				replyPageNum = String.valueOf(request.getAttribute("replyPageNum"));
			}
		}
		int currentReplyPage = Integer.parseInt(replyPageNum);
		final int PAGESIZE = 10; 
		final int BLOCKSIZE = 5;
		int startRow = (currentReplyPage -1 ) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE -1;
		Cat_ReplyDao cdao = Cat_ReplyDao.getInstance();
		request.setAttribute("replyList", cdao.listCReply(cnum, startRow, endRow));
		int totalCnt = cdao.totalRCnt(cnum);
		int pageCnt = (int)Math.ceil((double)totalCnt/PAGESIZE); 
		int startPage = ((currentReplyPage -1 )/BLOCKSIZE) * BLOCKSIZE +1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("replyPageNum", currentReplyPage);
		request.setAttribute("pageCnt", pageCnt);
	}

}
