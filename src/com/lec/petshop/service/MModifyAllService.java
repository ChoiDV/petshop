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
		String mid = request.getParameter("mid");
		String mname = request.getParameter("mname");
		String mtel = request.getParameter("mtel");
		String memail = request.getParameter("memail");
		String maddress = request.getParameter("maddress");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.updateAllMember(mname, mtel, memail, maddress, mid);
		if(result == MemberDao.SUCCESS) {
			request.setAttribute("ModifyAllResult", result);  // 성공하면 1
		} else {
			request.setAttribute("ModifyAllResult", result);  // 실패하면 0 
		}

	}

}
