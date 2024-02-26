package com.thousand.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thousand.dao.ThousandDAO;
import com.thousand.dto.PostDTO;

import util.ThousandPage;

@WebServlet("/index.do")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public indexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") !=null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else {
			ThousandDAO tDao =ThousandDAO.getInstance();
			
			/* 페이징 처리 *****************************************************/
			Map<String, Object> map = new HashMap<String, Object>();
			
			String searchField = request.getParameter("searchField");
			String searchWord = request.getParameter("searchWord");
			if (searchWord != null) {
				// 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}
			int totalCount = tDao.selectCount();  // 게시물 개수
			/* 페이지 처리 start */
			
			int pageSize = 6; // 페이지당 글수
			int blockPage = 5; // 목록 아랫쪽  페이지번호 수
			
			// 현재 페이지 확인
			int pageNum = 1;  // 기본값
			String pageTemp = request.getParameter("pageNum");
			if (pageTemp != null && !pageTemp.equals(""))
				pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정
			
			// 목록에 출력할 게시물 범위 계산
			int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
			int end = pageNum * pageSize; // 마지막 게시물 번호
			map.put("start", start);
			map.put("end", end);
			/* 페이지 처리 end */
			
			// 뷰에 전달할 매개변수 추가
			String pagingString="";
			if(searchWord!=null) {//검색하는 경우
				pagingString = ThousandPage.pagingStr(totalCount, pageSize,
						blockPage, pageNum, "");  // 바로가기 영역 HTML 문자열
			}else {//검색하지 않는 경우
				pagingString = ThousandPage.pagingStr(totalCount, pageSize,
						blockPage, pageNum, "/Thousand/index.do?");  // 바로가기 영역 HTML 문자열
			}
			map.put("pagingString", pagingString);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			// 전달할 데이터를 request 영역에 저장 후 포워드        
			request.setAttribute("map", map);
			
			//게시물목록조회
			List<PostDTO> postList= tDao.selectPostsAll(map);
			
			request.setAttribute("postList", postList); // view 에 전달할 데이터
			/* 페이징 처리.끝 */
			String url="mainView/index.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
