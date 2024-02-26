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

@WebServlet("/findPw.do")
public class findPwServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public findPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/findPw.jsp");
      dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      String id=request.getParameter("id");
      String nickname=request.getParameter("nickname");
      
      ThousandDAO tDao=ThousandDAO.getInstance();
      String password=tDao.searchPw(id,nickname);
         
      if(password !=null) {
         // 비밀번호 표시 때 별로 가려져서 보이게 하고 싶음 // 뭐더라..
         request.setAttribute("message", "비밀번호는 "+password+"입니다");
         // PW를 찾았을 경우 로그인 페이지로 이동
         RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/login.jsp");
         dispatcher.forward(request, response);
      }else {
         request.setAttribute("message", "아이디와 닉네임을 확인해주세요.");
         // PW를 찾지 못했을 경우 비밀번호찾기 페이지
         RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/findPw.jsp");
         dispatcher.forward(request, response);
      }
   }

}