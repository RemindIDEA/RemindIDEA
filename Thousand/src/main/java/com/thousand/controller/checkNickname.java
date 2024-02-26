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

/**
 * Servlet implementation class checkNickname
 */
@WebServlet("/checkNickname.do")
public class checkNickname extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public checkNickname() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐방지

		String nickname = request.getParameter("nickname");

		ThousandDAO tDao=ThousandDAO.getInstance();
		int result=tDao.confirmNickname(nickname);
		request.setAttribute("nickname", nickname);
		request.setAttribute("result", result);

		RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/checkNickname.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
