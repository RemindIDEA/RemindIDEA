package com.thousand.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.dao.ThousandDAO;

@WebServlet("/findId.do")
public class findIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public findIdServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") !=null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/findId.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 값 받기
		String nickname=request.getParameter("nickname");
		//
		ThousandDAO tDao=ThousandDAO.getInstance();
		String id=tDao.searchId(nickname);

		if(id !=null) {
			request.setAttribute("message", "일치하는 아이디는 "+ id +"입니다.");
			// ID를 찾았을 경우 로그인 페이지로 이동
			RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/login.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("message", "일치하는 아이디가 존재하지 않습니다.");
			// ID를 찾지 못했을 경우 아이디찾기 페이지
			RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/findId.jsp");
			dispatcher.forward(request, response);
		}
	}

}