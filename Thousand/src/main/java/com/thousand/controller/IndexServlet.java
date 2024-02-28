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


@WebServlet("/index.do")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") !=null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else {
			//service 객체색성
			PostService postService = PostServiceImpl.getInstance();
			//받아온 검색관련 변수들을 담아주어 보내주기
			SearchDTO sDto = new SearchDTO(request.getParameter("searchField"),
					request.getParameter("searchWord"),
					request.getParameter("pageNum"));
			
			//페이징 처리 띄우주기 위해 jsp로 보내주기
			request.setAttribute("map", postService.paging(sDto));
			//받아온 리스트 글 전체 다시 jsp로 보내주기
			request.setAttribute("postList", postService.selectPostsAll(postService.paging(sDto))); // view 에 전달할 데이터
			
			//메인페이지로 보내기
			RequestDispatcher dispatcher = request.getRequestDispatcher("mainView/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
