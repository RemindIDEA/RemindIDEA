package com.thousand.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.service.CategoryService;
import com.thousand.service.CategoryServiceImpl;
import com.thousand.service.PostService;
import com.thousand.service.PostServiceImpl;

@WebServlet("/viewPost.do")
public class ViewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewPostServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}else {
			PostService postService = PostServiceImpl.getInstance(); //post관련 서비스 객체
			CategoryService categoryService = CategoryServiceImpl.getInstance();	//카테고리관련 서비스객체
			//post상세보기
			//아이디 확인해서 자신인지 아닌지에 따라 화면 보여주기 
			int pno	= Integer.parseInt(request.getParameter("pno"));
			String id =(String)session.getAttribute("loginUser");
			//글 작성자와 로그인된 아이디가 같은지 확인
			boolean checkPnoId = postService.checkPnoId(pno, id);
			//글번호로 상세보기할 글 가져와서 저장
			request.setAttribute("postList", postService.selectOnePost(pno));
			//글번호로 상세보기 할 글의 카테고리 정보 가져오기
			request.setAttribute("category", categoryService.selectCategory(postService.selectOnePost(pno).getCategorycode()));
			if(checkPnoId==true) {	//작성자와 아이디가 일치할 경우
				RequestDispatcher dispatcher = request.getRequestDispatcher("mainView/viewPost.jsp");		//viewpost로 넘어가기
				dispatcher.forward(request, response);
			}else {	//작성자와 아이디가 일치하지 않을 경우
				//버튼 없는 페이지로 이동
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("mainView/nonviewPost.jsp");		//viewpost로 넘어가기
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	//post방식으로 넘어올때 get방식으로 넘겨서 처리
	}

}
