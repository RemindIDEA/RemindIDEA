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

@WebServlet("/loginForm.do")
public class loginFormServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public loginFormServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") !=null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/loginForm.jsp");
			dispatcher.forward(request, response);
		}
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8"); // 한글 깨짐방지
      
      // 적은 거 받기
      String id=request.getParameter("id");
      String pw=request.getParameter("pw");
      String email=request.getParameter("email");
      String nickname=request.getParameter("nickname");
      
      // 적은 거 저장
      MemberDTO mDto=new MemberDTO();
      mDto.setId(id);
      mDto.setPw(pw);
      mDto.setEmail(email);
      mDto.setNickname(nickname);
      
      // DAO 인스턴스 생성
      ThousandDAO tDao=ThousandDAO.getInstance();
      int result=tDao.createMember(mDto); // 영향을 받은 행의 수. insert하면 1행이 추가되므로 1리턴
      
      if(result==1) {
    	  request.setAttribute("message", "회원가입이 완료되었습니다.");
         // 회원가입 성공했을 경우 로그인 페이지로 넘어가기
         RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/login.jsp");
         dispatcher.forward(request, response);
      }else {
         request.setAttribute("message", "회원가입에 실패하였습니다.");
         //회원가입 실패했을 경우 회원가입 페이지로 넘어가기
         RequestDispatcher dispatcher=request.getRequestDispatcher("mypage/loginForm.jsp");
         dispatcher.forward(request, response);
      }
   }

}