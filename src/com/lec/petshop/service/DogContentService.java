package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dao.Dog_ReplyDao;
import com.lec.petshop.dao.ZimDao;
import com.lec.petshop.dto.DogDto;
import com.lec.petshop.dto.MemberDto;

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
		Dog_ReplyDao rdao = Dog_ReplyDao.getInstance();
		request.setAttribute("replyList", rdao.listDReply(dnum, startRow, endRow));
		int totalCnt = rdao.totalRCnt(dnum);
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
		
		// 찜
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		String mid = request.getParameter("mid");
		if(mid==null && member!=null) {
			mid = member.getMid();
		}
		ZimDao zdao = ZimDao.getInstance();
		int result = zdao.zimCheck(mid, dnum);
		request.setAttribute("ZimCount", result);
	}

}
