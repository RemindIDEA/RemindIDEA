package com.thousand.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.enums.LoginResult;
import com.thousand.service.LoginService;
import com.thousand.service.LoginServiceImpl;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") !=null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐방지
		//입력한 값 받기.
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");

		//service instance
		LoginService loginService = LoginServiceImpl.getInstance();
		//로그인 실패유무에 따라 페이지 이동
		LoginResult result=loginService.validateMember(id, pw);
		if(result==LoginResult.SUCCESS) { //id, pw가 일치할 때
			//session 등록
			HttpSession session=request.getSession(); 
			session.setAttribute("loginUser", id);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else if(result==LoginResult.PASSWORD_INCORRECT) { // pw가 일치하지 않을때
			request.setAttribute("message", "비밀번호를 확인해주세요.");
		}else if(result==LoginResult.USER_NOT_FOUND) { // id가 존재하지 않을 때
			request.setAttribute("message", "회원가입이 필요합니다.");
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/login.jsp");
		dispatcher.forward(request, response);
	}

}