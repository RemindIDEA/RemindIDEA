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

@WebServlet("/idcheck.do")
public class idcheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public idcheckServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐방지
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") !=null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else {
			String id = request.getParameter("id");
			
			ThousandDAO tDao=ThousandDAO.getInstance();
			int result=tDao.confirmId(id);
			request.setAttribute("id", id);
			request.setAttribute("result", result);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/checkId.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}