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

@WebServlet("/checkMyPw.do")
public class CheckMyPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckMyPwServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/checkMyPw.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String id = (String)session.getAttribute("loginUser");
		String pw = request.getParameter("pw");
		ThousandDAO tDao=ThousandDAO.getInstance();
		int result= tDao.checkPw(id,pw);
		if(result == 1){     // 비밀번호 일치시 mypage/updateinform.jsp페이지로 이동
			request.setAttribute("id", id);
			request.setAttribute("pw", pw);
			response.sendRedirect("updateMyInform.do");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMyInform.do");
//			dispatcher.forward(request, response);
		} else {               //비밀번호 일치하지 않을 경우. 실패 메시지 출력 하고 다시 mypage/checkMyPw.jsp로 이동
			request.setAttribute("message", "비밀번호 확인에 실패하셨습니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/checkMyPw.jsp");
			dispatcher.forward(request, response);
		}
	}

}