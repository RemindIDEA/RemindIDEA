package com.thousand.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thousand.dto.CategoryDTO;
import com.thousand.dto.PostDTO;
import com.thousand.dto.SearchDTO;
import com.thousand.repository.PostRepository;
import com.thousand.repository.PostRepositoryImpl;

import util.ThousandPage;

public class PostServiceImpl implements PostService {
	public static final int PAGE_SIZE = 6;	//페이지당 글의 갯수
	public static final int BLOCK_PAGE = 5;	//페이지시 노출된 페이징 갯수
	//singleton pattern
	private PostServiceImpl() {
	}
	private static PostServiceImpl instance = new PostServiceImpl();
	public static PostServiceImpl getInstance() {
		return instance;
	}
	
	//repository 접근 위한 instance생성
	PostRepository postRepo = PostRepositoryImpl.getInstance();
	CategoryService categoryService = CategoryServiceImpl.getInstance();
	
	//전체글 불러오기
	@Override
	public List<PostDTO> selectPostsAll(Map<String, Object> map){
		//게시물목록조회(전체글)
		List<PostDTO> postList= postRepo.selectPostsAll(map);
		return postList;
	}
	//작성자 글만 불러오기
	@Override
	public List<PostDTO> selectMyPost(String id,Map<String, Object> map) {
		//게시물목록조회(내게시물)
		List<PostDTO> postList= postRepo.selectMyPost(id,map);
		return postList;
	}
	//페이징 처리 위한 메서드(전체글불러오기)
	@Override
	public Map<String, Object> paging(SearchDTO searchDTO){
		/* 페이징 처리 *****************************************************/
		Map<String, Object> map = new HashMap<String, Object>();

		String searchField = searchDTO.getSearchField();
		String searchWord = searchDTO.getSearchWord();
		if (searchWord != null) {
			// 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = postRepo.selectCount();  // 게시물 개수

		/* 페이지 처리 start */

		// 현재 페이지 확인
		int pageNum = 1;  // 기본값
		String pageTemp = searchDTO.getPageTemp();
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정

		// 목록에 출력할 게시물 범위 계산
		int start = (pageNum - 1) * PAGE_SIZE + 1;  // 첫 게시물 번호
		int end = pageNum * PAGE_SIZE; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		/* 페이지 처리 end */

		// 뷰에 전달할 매개변수 추가
		String pagingString="";
		if(searchWord!=null) {//검색하는 경우
			pagingString = ThousandPage.pagingStr(totalCount, PAGE_SIZE,
					BLOCK_PAGE, pageNum, "");  // 바로가기 영역 HTML 문자열
		}else {//검색하지 않는 경우
			pagingString = ThousandPage.pagingStr(totalCount, PAGE_SIZE,
					BLOCK_PAGE, pageNum, "/Thousand/main.do?");  // 바로가기 영역 HTML 문자열
		}
		map.put("pagingString", pagingString);
		map.put("totalCount", totalCount);
		map.put("pageSize", PAGE_SIZE);
		map.put("pageNum", pageNum);
		return map;
	}
	//페이징 처리 위한 메서드(전체글불러오기)
	@Override
	public Map<String, Object> paging(String id, SearchDTO searchDTO){
		/* 페이징 처리 *****************************************************/
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = searchDTO.getSearchField();
		String searchWord = searchDTO.getSearchWord();
		if (searchWord != null) {
			// 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = postRepo.selectCount(id);  // 게시물 개수
		
		/* 페이지 처리 start */
		// 현재 페이지 확인
		int pageNum = 1;  // 기본값
		String pageTemp = searchDTO.getPageTemp();
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정
		
		// 목록에 출력할 게시물 범위 계산
		int start = (pageNum - 1) * PAGE_SIZE + 1;  // 첫 게시물 번호
		int end = pageNum * PAGE_SIZE; // 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		/* 페이지 처리 end */
		
		// 뷰에 전달할 매개변수 추가
		String pagingString="";
		if(searchWord!=null) {//검색하는 경우
			pagingString = ThousandPage.pagingStr(totalCount, PAGE_SIZE,
					BLOCK_PAGE, pageNum, "");  // 바로가기 영역 HTML 문자열
		}else {//검색하지 않는 경우
			pagingString = ThousandPage.pagingStr(totalCount, PAGE_SIZE,
					BLOCK_PAGE, pageNum, "/Thousand/main.do?");  // 바로가기 영역 HTML 문자열
		}
		map.put("pagingString", pagingString);
		map.put("totalCount", totalCount);
		map.put("pageSize", PAGE_SIZE);
		map.put("pageNum", pageNum);
		return map;
	}
	//글 상세보기
	@Override
	public PostDTO selectOnePost(int pno) {
		PostDTO pDto = new PostDTO();
		//글번호로 해당 글 정보 가져오기
		pDto = postRepo.selectOnePost(pno);
		postRepo.plusReadCount(pno);	//조회수 올리기
		return pDto;
	}
	//글 작성자와 로그인유저 같은지 체크
	@Override
	public boolean checkPnoId(int pno, String id) {
		boolean result = false;//해당 글 작성자와 아이디 일치에 대한 결과
		result = postRepo.checkPnoId(pno,id);
		return result;
	}
	//입력받은 정보로 새글 입력하기.
	@Override
	public int insertPost(PostDTO pDTO,CategoryDTO cDTO) {
		//받아온 카테고리 정보 먼저 입력
		int categorycode = categoryService.insertCategory(cDTO.getRecipe(),cDTO.getLocal(),cDTO.getItem());
		//카테고리 정보 넣어주기
		pDTO.setCategorycode(categorycode);
		//받아온 정보로 새로운 글 입력하기
		postRepo.insertPost(pDTO);
		//입력한 글의 카테고리코드로 불러오기
		int pno = postRepo.selectInsertingPost(pDTO.getCategorycode());
		return pno;
	}

	@Override
	public void updatePost(int pno,PostDTO pDTO) {
		//해당 글번호 확인해서 디비에 수정하기.
		pDTO.setPno(pno);
		postRepo.updatePost(pDTO);
	}

	@Override
	public void deletePost(int pno) {
		//받은 글번호로 해당 글 삭제
		postRepo.deletePost(pno);
	}
	//회원탈퇴 전 해당 글 전부 삭제
	@Override
	public void deletePost(String id) {
		postRepo.deletePost(id);
	}
}
