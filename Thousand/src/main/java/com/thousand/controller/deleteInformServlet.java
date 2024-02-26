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

@WebServlet("/deleteInform.do")
public class deleteInformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public deleteInformServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}else {
			//아이디 받아오기
			String id = request.getParameter("id");
			//아이디로 글 긁어와서 전부 삭제하기
			ThousandDAO tDao = ThousandDAO.getInstance();
			tDao.deletePost(id);
			
			//아이디 삭제하기
			tDao.deleteMember(id);
			session.invalidate(); //session비활성화 => session attribute 소멸
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
