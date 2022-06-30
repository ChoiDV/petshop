package com.lec.petshop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.service.AdminJoinService;
import com.lec.petshop.service.AdminLoginService;
import com.lec.petshop.service.DogAllService;
import com.lec.petshop.service.DogBreedService;
import com.lec.petshop.service.DogContentService;
import com.lec.petshop.service.DogInsertService;
import com.lec.petshop.service.MIdConfirmService;
import com.lec.petshop.service.MJoinService;
import com.lec.petshop.service.MLoginService;
import com.lec.petshop.service.MLogoutService;
import com.lec.petshop.service.MModifyAllService;
import com.lec.petshop.service.MModifyAllViewService;
import com.lec.petshop.service.Service;

/**
 * Servlet implementation class PetController
 */
@WebServlet("*.do")
public class PetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean loginForm = false;
	private boolean joinForm = false;
	private boolean adminloginForm = false;
	private boolean adminJoinForm = false;
	private boolean dogInsertForm = false;
	private boolean modifyForm = false;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		
		if(command.equals("/main.do")) {   // 첫 실행시
//			service = new MainService();
//			service.execute(request, response);
			viewPage = "main/main.jsp";  
		} else if(command.equals("/loginView.do")) {  // 로그인  페이지
			viewPage = "member/loginView.jsp";
			loginForm = true;
		} else if(command.equals("/login.do")) {  //  로그인 처리
			if(loginForm) {
				service = new MLoginService();
				service.execute(request, response);
			}			
			viewPage = "main.do";
			loginForm = false;
		} else if(command.equals("/joinAgree.do")) {  // 회원가입 약관 동의 
			viewPage = "member/joinAgree.jsp";
		} else if(command.equals("/joinView.do")) { // 회원가입 정보입력
			viewPage = "member/joinView.jsp";
			joinForm = true;
		} else if(command.equals("/idConfirm.do")) {  // id 중복확인
			service = new MIdConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		} else if(command.equals("/join.do")) {  // 회원가입 처리 
			if(joinForm) {
				service = new MJoinService();
				service.execute(request, response);
			}
			viewPage = "member/joinResult.jsp";		
			joinForm = false;
		} else if(command.equals("/logout.do")) { // 로그아웃 처리
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";			
		} else if(command.equals("/adminLoginView.do")) {  // 관리자 로그인
			viewPage = "admin/adminLoginView.jsp";		
			adminloginForm = true;
		} else if(command.equals("/adminLogin.do")) {  // 관리자 로그인 처리 
			if(adminloginForm) {
				service = new AdminLoginService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			adminloginForm = false;
		} else if(command.equals("/adminJoinView.do")) {  // 관리자 등록 
			viewPage = "admin/adminJoinView.jsp";
			adminJoinForm = true;
		} else if(command.equals("/adminJoin.do")) {  // 관리자 등록 처리 
			if(adminJoinForm) {
				service = new AdminJoinService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			adminJoinForm = false;
		} else if(command.equals("/DogAllView.do")) {  // Cute Dog -> 전체보기 클릭시
			service = new DogAllService();
			service.execute(request, response);
			viewPage = "pet/DogAllView.jsp";
		} else if(command.equals("/DogContentView.do")) {  // Cute Dog -> 전체보기 -> 특정 강아지 content
			service = new DogContentService();
			service.execute(request, response);
			viewPage = "pet/DogContent.jsp";
		} else if(command.equals("/DogInsertView.do")) {  // 관리자가 강아지 등록 
			service = new DogBreedService();
			service.execute(request, response);
			viewPage = "pet/DogInsert.jsp";
			dogInsertForm = true;
		} else if(command.equals("/DogInsert.do")) {  // 강아지 등록 처리
			if(dogInsertForm) {
				service = new DogInsertService();
				service.execute(request, response);
			}
			viewPage = "DogAllView.do";
			dogInsertForm = false;
		} else if(command.equals("/mypageView.do")) {
			viewPage = "member/MyPage.jsp";
		} else if(command.equals("/modifyView.do")) {
			viewPage = "member/modify.jsp";
		} else if(command.equals("/modifyAllView.do")) {
			service = new MModifyAllViewService();
			service.execute(request, response);
			viewPage = "member/modifyAll.jsp";
			modifyForm = true;
		} else if(command.equals("/modify.do")) {
			if(modifyForm) {
				service = new MModifyAllService();
				service.execute(request, response);	
			}
			viewPage = "member/MyPage.jsp";
			modifyForm = false;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	

}
