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
import com.thousand.dto.MemberDTO;

@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginServlet() {
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

		// 
		String url="mypage/login.jsp";

		//
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");

		//dao instance
		ThousandDAO tDao = ThousandDAO.getInstance();

		int result=tDao.selectMember(id, pw);
		//
		if(result==1) { // pw가 일치할 때
			MemberDTO mDto=tDao.getMember(id);
			HttpSession session=request.getSession(); //session
			session.setAttribute("loginUser", mDto.getId());// session attribute "loginUser" 에 mDto 저장
			url="main.do"; // main 이동
		}else if(result==0) { // pw가 일치하지 않을때
			request.setAttribute("message", "비밀번호를 확인해주세요.");
		}else if(result==-1) { // id가 존재하지 않을 때
			request.setAttribute("message", "회원가입이 필요합니다.");
		}

		//
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}