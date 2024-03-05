package com.thousand.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.dto.SearchDTO;
import com.thousand.service.PostService;
import com.thousand.service.PostServiceImpl;

@WebServlet("/myPost.do")
public class MyPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyPostServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}
		//id 받아오기
		String id = (String)session.getAttribute("loginUser");
		//서비스 객체 생성
		PostService postService = PostServiceImpl.getInstance();
		//받아온 검색관련 변수들을 담아주어 보내주기
		SearchDTO sDto = new SearchDTO(request.getParameter("searchField"),
				request.getParameter("searchWord"),
				request.getParameter("pageNum"));
		//페이징 처리위해 jsp로 보내주기
		request.setAttribute("map", postService.paging(id,sDto));
		//받아온 리스트 내 게시물 다시 jsp로 보내주기
		request.setAttribute("postList", postService.selectMyPost(id,postService.paging(id,sDto))); // view 에 전달할 데이터
		RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/myPost.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
