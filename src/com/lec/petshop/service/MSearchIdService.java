package com.lec.petshop.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.MemberDao;
import com.lec.petshop.dto.MemberDto;

public class MSearchIdService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mname = request.getParameter("mname");
		String mbirth = request.getParameter("mbirth");
		MemberDao dao = MemberDao.getInstance(); 
		ArrayList<MemberDto> members = new ArrayList<MemberDto>();
		members = dao.searchId(mname, mbirth);
		request.setAttribute("searchResult", members);
		if(members.size() == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("searchIdResult", members.get(0).getMid());
		}
	}

}
