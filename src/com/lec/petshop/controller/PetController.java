package com.lec.petshop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.service.AdminDeleteService;
import com.lec.petshop.service.AdminDeleteViewService;
import com.lec.petshop.service.AdminJoinService;
import com.lec.petshop.service.AdminLoginService;
import com.lec.petshop.service.CatAllService;
import com.lec.petshop.service.CatBreedService;
import com.lec.petshop.service.CatContentService;
import com.lec.petshop.service.CatDeleteService;
import com.lec.petshop.service.CatDeleteViewService;
import com.lec.petshop.service.CatInsertService;
import com.lec.petshop.service.CatModifyContentSerivce;
import com.lec.petshop.service.CatModifyService;
import com.lec.petshop.service.CatReplyDeleteSerivice;
import com.lec.petshop.service.CatReplyDeleteViewService;
import com.lec.petshop.service.CatReplyService;
import com.lec.petshop.service.DogAllService;
import com.lec.petshop.service.DogBreedService;
import com.lec.petshop.service.DogContentService;
import com.lec.petshop.service.DogDeleteService;
import com.lec.petshop.service.DogDeleteViewService;
import com.lec.petshop.service.DogInsertService;
import com.lec.petshop.service.DogModifyContentService;
import com.lec.petshop.service.DogModifyReplyService;
import com.lec.petshop.service.DogModifyReplyViewService;
import com.lec.petshop.service.DogModifyService;
import com.lec.petshop.service.DogReplyDeleteService;
import com.lec.petshop.service.DogReplyDeleteViewService;
import com.lec.petshop.service.DogReplyService;
import com.lec.petshop.service.FreeBoardContentService;
import com.lec.petshop.service.FreeBoardDeleteService;
import com.lec.petshop.service.FreeBoardDeleteViewService;
import com.lec.petshop.service.FreeBoardListService;
import com.lec.petshop.service.FreeBoardModifyService;
import com.lec.petshop.service.FreeBoardModifyViewService;
import com.lec.petshop.service.FreeBoardReplyService;
import com.lec.petshop.service.FreeBoardReplyViewService;
import com.lec.petshop.service.FreeBoardWriteService;
import com.lec.petshop.service.MIdConfirmService;
import com.lec.petshop.service.MJoinService;
import com.lec.petshop.service.MLoginService;
import com.lec.petshop.service.MLogoutService;
import com.lec.petshop.service.MModifyAllService;
import com.lec.petshop.service.MModifyAllViewService;
import com.lec.petshop.service.MainService;
import com.lec.petshop.service.Service;
import com.lec.petshop.service.deleteCatZimService;
import com.lec.petshop.service.deleteZimservice;
import com.lec.petshop.service.insertCatZimService;
import com.lec.petshop.service.insertZimservice;

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
	private boolean modifyFreeBoard = false;
	private boolean writeFreeBoard = false;
	private boolean replyFreeBoard = false;
	private boolean dogModifyForm = false;
	private boolean dogDeleteForm = false;
	private boolean catInsertForm = false;
	private boolean catModifyForm = false;
	private boolean catDeleteForm = false;
	private boolean dogReplyDeleteForm = false;
	private boolean catReplyDeleteForm = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;

		if (command.equals("/main.do")) { // 첫 실행시
			service = new MainService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (command.equals("/loginView.do")) { // 로그인 페이지
			viewPage = "member/loginView.jsp";
			loginForm = true;
		} else if (command.equals("/login.do")) { // 로그인 처리
			if (loginForm) {
				service = new MLoginService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			loginForm = false;
		} else if (command.equals("/joinAgree.do")) { // 회원가입 약관 동의
			viewPage = "member/joinAgree.jsp";
		} else if (command.equals("/joinView.do")) { // 회원가입 정보입력
			viewPage = "member/joinView.jsp";
			joinForm = true;
		} else if (command.equals("/idConfirm.do")) { // id 중복확인
			service = new MIdConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		} else if (command.equals("/join.do")) { // 회원가입 처리
			if (joinForm) {
				service = new MJoinService();
				service.execute(request, response);
			}
			viewPage = "member/joinResult.jsp";
			joinForm = false;
		} else if (command.equals("/logout.do")) { // 로그아웃 처리
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		} else if (command.equals("/adminLoginView.do")) { // 관리자 로그인
			viewPage = "admin/adminLoginView.jsp";
			adminloginForm = true;
		} else if (command.equals("/adminLogin.do")) { // 관리자 로그인 처리
			if (adminloginForm) {
				service = new AdminLoginService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			adminloginForm = false;
		} else if (command.equals("/adminJoinView.do")) { // 관리자 등록
			viewPage = "admin/adminJoinView.jsp";
			adminJoinForm = true;
		} else if (command.equals("/adminJoin.do")) { // 관리자 등록 처리
			if (adminJoinForm) {
				service = new AdminJoinService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			adminJoinForm = false;
		} else if (command.equals("/DogAllView.do")) { // Cute Dog -> 전체보기 클릭시
			service = new DogAllService();
			service.execute(request, response);
			viewPage = "pet/DogAllView.jsp";
		} else if (command.equals("/DogContentView.do")) { // 특정 강아지 content
			service = new DogContentService();
			service.execute(request, response);
			viewPage = "pet/DogContent.jsp";
		} else if (command.equals("/DogInsertView.do")) { // 관리자가 강아지 등록
			service = new DogBreedService();
			service.execute(request, response);
			viewPage = "admin/adminDogInsert.jsp";
			dogInsertForm = true;
		} else if (command.equals("/DogInsert.do")) { // 강아지 등록 처리
			if (dogInsertForm) {
				service = new DogInsertService();
				service.execute(request, response);
			}
			viewPage = "admin/adminDogListView.jsp";
			dogInsertForm = false;
		} else if (command.equals("/mypageView.do")) { // 마이 페이지
			viewPage = "member/MyPage.jsp";
		} else if (command.equals("/modifyView.do")) { // 비밀번호 입력받는 페이지
			viewPage = "member/modify.jsp";
		} else if (command.equals("/modifyAllView.do")) { // 수정할 것들 입력받는 페이지
			service = new MModifyAllViewService();
			service.execute(request, response);
			viewPage = "member/modifyAll.jsp";
			modifyForm = true;
		} else if (command.equals("/modify.do")) { // 수정 처리
			if (modifyForm) {
				service = new MModifyAllService();
				service.execute(request, response);
			}
			viewPage = "member/MyPage.jsp";
			modifyForm = false;
		} else if (command.equals("/freeBoardListView.do")) { // 자유 게시판 리스트 출력 (소통)
			service = new FreeBoardListService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardList.jsp";
		} else if (command.equals("/freeBoardContentView.do")) { // 자유게시판 특정 글 content
			service = new FreeBoardContentService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardContent.jsp";
		} else if (command.equals("/freeBoardModifyView.do")) { // 자유게시판 특정 글 수정 View 단
			service = new FreeBoardModifyViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardModify.jsp";
			modifyFreeBoard = true;
		} else if (command.equals("/freeBoardModify.do")) { // // 자유게시판 특정 글 수정 처리
			if (modifyFreeBoard) {
				service = new FreeBoardModifyService();
				service.execute(request, response);
			}
			viewPage = "freeBoardContentView.do";
			modifyFreeBoard = false;
		} else if (command.equals("/freeBoardWriteView.do")) { // 자유게시판 글 등록 View 단
			viewPage = "FreeBoard/FreeBoardWrite.jsp";
			writeFreeBoard = true;
		} else if (command.equals("/freeBoardWrite.do")) { // 자유게시판 글 등록 처리
			if (writeFreeBoard) {
				service = new FreeBoardWriteService();
				service.execute(request, response);
			}
			viewPage = "freeBoardListView.do";
			writeFreeBoard = false;
		} else if (command.equals("/freeBoardDeleteView.do")) { // 자유게시판 특정 글 삭제
			service = new FreeBoardDeleteViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardDeleteView.jsp";
		} else if (command.equals("/freeBoardDelete.do")) { // 자유게시판 글 삭제 처리
			service = new FreeBoardDeleteService();
			service.execute(request, response);
			viewPage = "freeBoardListView.do";
		} else if (command.equals("/freeBoardReplyView.do")) { // 자유게시판 답글View단 이동
			service = new FreeBoardReplyViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardWrite.jsp";
			replyFreeBoard = true;
		} else if (command.equals("/freeBoardReply.do")) { // 자유게시판 답글 처리
			if (replyFreeBoard) {
				service = new FreeBoardReplyService();
				service.execute(request, response);
			}
			viewPage = "freeBoardListView.do";
			replyFreeBoard = false;
		} else if (command.equals("/adminPageView.do")) { // 관리자 마이페이지 이동
			viewPage = "admin/adminPage.jsp";
		} else if (command.equals("/adminPageDogListView.do")) { // 관리자 마이페이지 강아지 리스트 출력
			service = new DogAllService();
			service.execute(request, response);
			viewPage = "admin/adminDogListView.jsp";
		} else if (command.equals("/DogModifyView.do")) { // 강아지 수정 View단
			service = new DogModifyContentService();
			service.execute(request, response);
			viewPage = "admin/adminDogModifyView.jsp";
			dogModifyForm = true;
		} else if (command.equals("/DogModify.do")) { // 강아지 수정 처리 단
			if (dogModifyForm) {
				service = new DogModifyService();
				service.execute(request, response);
			}
			viewPage = "adminPageDogListView.do";
			dogModifyForm = false;
		} else if (command.equals("/DogDeleteView.do")) { // 강아지 분양글 삭제 View단
			service = new DogDeleteViewService();
			service.execute(request, response);
			viewPage = "admin/adminDogDeleteView.jsp";
			dogDeleteForm = true;
		} else if (command.equals("/DogDelete.do")) { // 강아지 삭제 처리 단
			if (dogDeleteForm) {
				service = new DogDeleteService();
				service.execute(request, response);
			}
			viewPage = "adminPageDogListView.do";
			dogDeleteForm = false;
		} else if (command.equals("/dogReplyInsert.do")) { // 강아지 댓글 처리 단
			service = new DogReplyService();
			service.execute(request, response);
			viewPage = "DogContentView.do";
		} else if (command.equals("/CatAllView.do")) { // 고양이 전체출력
			service = new CatAllService();
			service.execute(request, response);
			viewPage = "pet/CatAllView.jsp";
		} else if (command.equals("/CatContentView.do")) { // 고양이 상세보기
			service = new CatContentService();
			service.execute(request, response);
			viewPage = "pet/CatContent.jsp";
		} else if (command.equals("/CatInsertView.do")) { // 관리자 고양이 등록
			service = new CatBreedService();
			service.execute(request, response);
			viewPage = "admin/adminCatInsert.jsp";
			catInsertForm = true;
		} else if (command.equals("/CatInsert.do")) { // 고양이 등록 처리
			if (catInsertForm) {
				service = new CatInsertService();
				service.execute(request, response);
			}
			viewPage = "admin/adminCatListView.jsp";
			catInsertForm = false;
		} else if (command.equals("/adminPageCatListView.do")) { // 관리자 고양이 출력 View단
			service = new CatAllService();
			service.execute(request, response);
			viewPage = "admin/adminCatListView.jsp";
		} else if (command.equals("/CatModifyView.do")) { // 고양이 수정 View단
			service = new CatModifyContentSerivce();
			service.execute(request, response);
			viewPage = "admin/adminCatModifyView.jsp";
			catModifyForm = true;
		} else if (command.equals("/CatModify.do")) { // 고양이 수정 처리 단
			if (catModifyForm) {
				service = new CatModifyService();
				service.execute(request, response);
			}
			viewPage = "adminPageCatListView.do";
			catModifyForm = false;
		} else if (command.equals("/CatDeleteView.do")) { // 고양이 삭제 View 단
			service = new CatDeleteViewService();
			service.execute(request, response);
			viewPage = "admin/adminCatDeleteView.jsp";
			catDeleteForm = true;
		} else if (command.equals("/CatDelete.do")) { // 고양이 삭제 처리 단
			if (catDeleteForm) {
				service = new CatDeleteService();
				service.execute(request, response);
			}
			viewPage = "adminPageCatListView.do";
			catDeleteForm = false;
		} else if (command.equals("/catReplyInsert.do")) { // 고양이 댓글 작성 처리
			service = new CatReplyService();
			service.execute(request, response);
			viewPage = "CatContentView.do";
		} else if (command.equals("/deleteDogReplyView.do")) {
			service = new DogReplyDeleteViewService();
			service.execute(request, response);
			viewPage = "pet/DogDeleteReplyView.jsp";
			dogReplyDeleteForm = true;
		} else if (command.equals("/DogDeleteReply.do")) {
			if (dogReplyDeleteForm) {
				service = new DogReplyDeleteService();
				service.execute(request, response);
			}
			viewPage = "DogContentView.do";
			dogReplyDeleteForm = false;
		} else if (command.equals("/deleteCatReplyView.do")) {
			service = new CatReplyDeleteViewService();
			service.execute(request, response);
			viewPage = "pet/CatDeleteReplyView.jsp";
			catReplyDeleteForm = true;
		} else if (command.equals("/CatDeleteReply.do")) {
			if (catReplyDeleteForm) {
				service = new CatReplyDeleteSerivice();
				service.execute(request, response);
			}
			viewPage = "CatContentView.do";
			catReplyDeleteForm = false;
		} else if (command.equals("/insertZim.do")) {
			service = new insertZimservice();
			service.execute(request, response);
			viewPage = "DogContentView.do";
		} else if (command.equals("/deleteZim.do")) {
			service = new deleteZimservice();
			service.execute(request, response);
			viewPage = "DogContentView.do";
		} else if (command.equals("/insertCatZim.do")) {
			service = new insertCatZimService();
			service.execute(request, response);
			viewPage = "CatContentView.do";
		} else if (command.equals("/deleteCatZim.do")) {
			service = new deleteCatZimService();
			service.execute(request, response);
			viewPage = "CatContentView.do";
		} else if(command.equals("/adminDeleteView.do")) {
			service = new AdminDeleteViewService();
			service.execute(request, response);
			viewPage = "admin/adminDeleteView.jsp";			
		} else if(command.equals("/adminDelete.do")) {
			service = new AdminDeleteService();
			service.execute(request, response);
			viewPage =  "admin/adminDeleteView.jsp";
		} else if(command.equals("/DogBreedView.do")) {
			service = new DogBreedService();
			service.execute(request, response);
			viewPage = "pet/DogBreedView.jsp";
					
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
