package com.thousand.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.dao.ThousandDAO;
import com.thousand.dto.PostDTO;

@WebServlet("/recommandPost.do")
public class recommandPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public recommandPostServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}else {
			/* 오늘의 검색 */
			ThousandDAO tDao = ThousandDAO.getInstance();
			List<PostDTO> todayTopSearch = tDao.todayTopSearch();
			List<PostDTO> menuRecommand = tDao.menuRecommand();
			
			request.setAttribute("todayTopSearch", todayTopSearch);		//오늘의 추천 가져오기
			request.setAttribute("menuRecommand", menuRecommand);		//메뉴별 추천 가져오기
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("recommandPost.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}