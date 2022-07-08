package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.FreeBoardDao;
import com.lec.petshop.dao.ReviewDao;
import com.lec.petshop.dto.ReviewDto;

public class ReviewContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		String rnumStr = request.getParameter("rnum");
		if(rnumStr == null ) {
			rnumStr = String.valueOf(request.getAttribute("rnum"));
		}
		int rnum = Integer.parseInt(rnumStr);
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto review =  dao.contentReview(rnum);
		if(review.getAid() != null) {   // 글을 작성했을때 도와준 관리자를 체크했으면 Aid가 null이 아니므로 관리자 이름 갖고와서 request.setAttribute
			AdminDao adao = AdminDao.getInstance();
			request.setAttribute("aname",adao.getName(review.getAid()));
		}
		request.setAttribute("reviewContent", review);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("rnum", rnum);		
	
	}

}
