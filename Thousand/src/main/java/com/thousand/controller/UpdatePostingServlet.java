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
import com.thousand.dto.CategoryDTO;
import com.thousand.dto.PostDTO;
import com.thousand.service.CategoryService;
import com.thousand.service.CategoryServiceImpl;
import com.thousand.service.PostService;
import com.thousand.service.PostServiceImpl;

@WebServlet("/updatePosting.do")
public class UpdatePostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdatePostingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); //session
		if(session.getAttribute("loginUser") ==null) {
			//로그인 되어 있을 시 메인으로 이동.
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.do");
			dispatcher.forward(request, response);
		}
		//pno가 있다면 실행
		if(request.getParameter("pno")!=null) {
			int pno =Integer.parseInt(request.getParameter("pno"));
			PostService postService = PostServiceImpl.getInstance();
			CategoryService categoryService = CategoryServiceImpl.getInstance();
			//선택 카테고리 가져오기.
			request.setAttribute("category", categoryService.selectCategory(postService.selectOnePost(pno).getCategorycode()));	
			//선택 글 가져오기.
			request.setAttribute("postList", postService.selectOnePost(pno));
			RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/updatePosting.jsp");		//updatePosting으로 넘어가기
			dispatcher.forward(request, response);
		}else {
			//pno가 없을경우 main페이지로 보내기
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.do");		//main으로 넘어가기
			dispatcher.forward(request, response);
		}
		//pno를 통해 글정보를 받아와서 updatePosting.jsp으로 보내주기.end---------------
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘겨온 정보에 한글이 있을 시 깨지는 것을 방지하기 위함
		request.setCharacterEncoding("UTF-8");
		//게시글 수정에서 정보받아오기 위한 객체 생성
		ServletContext context = getServletContext();
		String path = context.getRealPath("img");
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request,path,sizeLimit, encType, new DefaultFileRenamePolicy());
		//해당 글 번호받아오기
		int pno = Integer.parseInt(multi.getParameter("pno"));
		//아이디 정보받기
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
		//서비스 인스턴스 가져오기
		PostService postService = PostServiceImpl.getInstance();
		CategoryService categoryService = CategoryServiceImpl.getInstance();
		CategoryDTO categoryDTO = new CategoryDTO();
		
		categoryDTO.setCategorycode(postService.selectOnePost(pno).getCategorycode());
		categoryDTO.setRecipe(recipe);
		categoryDTO.setLocal(local);
		categoryDTO.setItem(item);
		//업데이트 된 카테고리 내용 넣어주기.
		categoryService.updateCategory(categoryDTO);
		//불러온 정보 전체 저장 
		postService.updatePost(pno,new PostDTO(id,title,summary,postService.selectOnePost(pno).getCategorycode(),mainimg,content,produceImg));		//정보가 저장된 객체를 넘겨서 insert해주기

		//pno가 0이면 게시글 못찾았음 -> 글쓰기 실패
		if(pno!= 0) {		//수정한 글 게시글번호를 가져오면 view로 넘겨주기
			request.setAttribute("pno", pno);		
			//글 작성이 완료되면 자기가 작성한 글로 보여주기
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}else {		//게시글 작성이 실패할 경우 메인으로 돌아가기
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.do");
			dispatcher.forward(request, response);
		}
	}
}
