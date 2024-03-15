package com.thousand.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.enums.SearchCheckResult;
import com.thousand.service.LoginService;
import com.thousand.service.LoginServiceImpl;


@WebServlet("/idcheck.do")
public class IdcheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IdcheckServlet() {
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
			LoginService loginService = LoginServiceImpl.getInstance();
			//id와 중복여부 결과값 전달.
			if(loginService.confirmId(id) == SearchCheckResult.SUCCESS) {
				request.setAttribute("result", 1);
			}else {
				request.setAttribute("result", -1);
			}
			request.setAttribute("id", id);
			RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/checkId.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}