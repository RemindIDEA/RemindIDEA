package com.thousand.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.thousand.dao.ThousandDAO;
import com.thousand.dto.CategoryDTO;
import com.thousand.dto.PostDTO;

@WebServlet("/updatePosting.do")
public class updatePostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updatePostingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}
		//pno를 통해 글정보를 받아와서 updatePosting.jsp으로 보내주기
		int pno = -1;
		
		pno = Integer.parseInt(request.getParameter("pno"));
		if(pno != -1) {
			ThousandDAO tDao = ThousandDAO.getInstance();
			PostDTO pDto = new PostDTO();
			CategoryDTO cDto = new CategoryDTO();
			//pno로 글 가져오기
			pDto = tDao.selectOnePost(pno);
			//jsp로 해당 정보 넘겨주기
			cDto = tDao.selectCategory(pDto.getCategorycode());
			request.setAttribute("category", cDto);	
			request.setAttribute("postList", pDto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/updatePosting.jsp");		//updatePosting으로 넘어가기
			dispatcher.forward(request, response);
		}else {
			//받아온 글번호가 없을경우
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.do");		//main으로 넘어가기
			dispatcher.forward(request, response);
		}
		//pno를 통해 글정보를 받아와서 updatePosting.jsp으로 보내주기.end---------------

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘겨온 정보에 한글이 있을 시 깨지는 것을 방지하기 위함
		request.setCharacterEncoding("UTF-8");
		//수정할 글 번호 확인하기.

		//게시글 수정에서 정보받아오기 위한 객체 생성
		ServletContext context = getServletContext();
		String path = context.getRealPath("img");
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request,path,sizeLimit, encType, new DefaultFileRenamePolicy());
		//아이디 정보받기
		int pno = Integer.parseInt(multi.getParameter("pno"));
		String id = multi.getParameter("id");
		// 정보 받아오기 - 게시글 정보
		String title = multi.getParameter("title");
		String summary = multi.getParameter("summary");
		String mainimg = multi.getFilesystemName("mainimg");
		if(mainimg == null ){
			mainimg = multi.getParameter("nonmainimg");
		}
		//레시피 정보
		String recipe = multi.getParameter("recipe");				
		String local = multi.getParameter("local");
		String item = multi.getParameter("item");
		//요리순서 받아오기
		String[] content = new String[11];
		String[] produceImg = new String[10];
		for(int i=0; i<11; i++) {
			//재료 + //요리순서 1~10까지
			if(multi.getParameter("content"+Integer.toString(i+1))!=null){
				content[i] = multi.getParameter("content"+Integer.toString(i+1));
			}else if(multi.getParameter("content"+Integer.toString(i+1)).isBlank()) {
				content[i] = null;
			}
			if(i!= 10) {		//재료 + 순서는 11가지 이지만 사진은 1~10까지 이므로 11번때는 실행되지 않게하기
				if(multi.getFilesystemName("produceImg"+Integer.toString(i+2))!=null) {
					produceImg[i] = multi.getFilesystemName("produceImg"+Integer.toString(i+2));
				}else if(multi.getFilesystemName("produceImg"+Integer.toString(i+2))==null) {
					produceImg[i] = multi.getParameter("nonproduceImg"+Integer.toString(i+2));
				}
			}
		}
		//dao instance 가져오기
		ThousandDAO tDao = ThousandDAO.getInstance();
		PostDTO pDto = new PostDTO();

		//pno로 categorycode 받아오기
		pDto = tDao.selectOnePost(pno);

		//변경된 카테고리 정보 저장하기.
		//그 후 받아온 카테고리코드로 정보 수정하기
		int categorycode = pDto.getCategorycode();
		CategoryDTO cDto = new CategoryDTO();
		cDto.setRecipe(recipe);		//선택한 카테고리 각각 넣어주기
		cDto.setLocal(local);
		cDto.setItem(item);
		cDto.setCategorycode(categorycode);
		tDao.updateCategory(cDto);		

		//불러온 정보 전체 저장 
		pDto.setPno(pno);
		pDto.setId(id);
		pDto.setTitle(title);
		pDto.setSummary(summary);
		pDto.setCategorycode(categorycode);		//기존에 등록된 카테고리 내용 변경 후 
		pDto.setMainimg(mainimg);
		pDto.setContent(content);
		pDto.setProduceImg(produceImg);
		tDao.updatePost(pDto);		//정보가 저장된 객체를 넘겨서 insert해주기

		//이동할 페이지 변수 선언
		String url;

		//pno가 0이면 게시글 못찾았음 -> 글쓰기 실패
		if(pno!= 0) {		//수정한 글 게시글번호를 가져오면 view로 넘겨주기
			request.setAttribute("pno", pno);		
			url = "main.do";		//글 작성이 완료되면 자기가 작성한 글로 보여주기
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {		//게시글 작성이 실패할 경우 메인으로 돌아가기
			url = "main.do";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
