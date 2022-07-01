package com.lec.petshop.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.MemberDao;
import com.lec.petshop.dto.MemberDto;

public class MModifyAllService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String modify = request.getParameter("modify");
		MemberDao dao = MemberDao.getInstance();
		int result;
		if(modify.equals("all")) {
			String mid = request.getParameter("mid");
			String mname = request.getParameter("mname");
			String mtel = request.getParameter("mtel");
			String memail = request.getParameter("memail");
			String maddress = request.getParameter("maddress");
			result = dao.updateAllMember(mname, mtel, memail, maddress, mid);
			if(result == MemberDao.SUCCESS) {
				request.setAttribute("ModifyAllResult", result);  // 개인정보 변경 성공하면 1
			} else {
				request.setAttribute("ModifyAllResult", result);  // 실패하면 0 
			}
		} else if(modify.equals("pw")) {
			HttpSession session = request.getSession();
			String mid = ((MemberDto)session.getAttribute("member")).getMid();
			String oldmpw = request.getParameter("oldmpw");
			String mpw = request.getParameter("mpw");
			result = dao.updatePwMember(mid, oldmpw, mpw);
			if(result == MemberDao.SUCCESS) {
				request.setAttribute("ModifyPwResult", result);  // 비밀번호 변경 성공 1
				session.invalidate();
			} else {
				request.setAttribute("ModifyPwResult", result);
			}
		}
		
		
		

	}

}
