package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.MemberDao;
import com.lec.petshop.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String mtel = request.getParameter("mtel");
		String mbirth = request.getParameter("mbirth");
		String memail = request.getParameter("memail");
		String maddress = request.getParameter("maddress");
		String mgender = request.getParameter("mgender");
		MemberDao dao = MemberDao.getInstance();
		MemberDto member = new MemberDto(mid, mpw, mname, mtel, mbirth, memail, maddress, mgender);
		int result = dao.joinMember(member);
		if(result == MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid);
			request.setAttribute("join", member);
			request.setAttribute("joinResult", "회원가입이 완료되었습니다.");
		} else {
			request.setAttribute("joinErrorMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
		}
		

	}

}
