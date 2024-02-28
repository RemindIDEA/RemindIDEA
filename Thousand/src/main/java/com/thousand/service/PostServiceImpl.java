package com.thousand.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thousand.dto.PostDTO;
import com.thousand.dto.SearchDTO;
import com.thousand.repository.PostRepository;
import com.thousand.repository.PostRepositoryImpl;

import util.ThousandPage;

public class PostServiceImpl implements PostService {
	//singleton pattern
	private PostServiceImpl() {
	}
	private static PostServiceImpl instance = new PostServiceImpl();
	public static PostServiceImpl getInstance() {
		return instance;
	}
	//repository 접근 위한 instance생성
	PostRepository postRepo = PostRepositoryImpl.getInstance();

	//전체글 불러오기
	@Override
	public List<PostDTO> selectPostsAll(Map<String, Object> map){
		//게시물목록조회
		List<PostDTO> postList= postRepo.selectPostsAll(map);
		return postList;
	}
	//작성자 글만 불러오기
	@Override
	public List<PostDTO> selectMyPost(String id,Map<String, Object> map) {
		//게시물목록조회
		List<PostDTO> postList= postRepo.selectMyPost(id,map);
		return postList;
	}
	//페이징 처리 위한 메서드
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

		int pageSize = 6; // 페이지당 글수
		int blockPage = 5; // 목록 아랫쪽  페이지번호 수

		// 현재 페이지 확인
		int pageNum = 1;  // 기본값
		String pageTemp = searchDTO.getPageTemp();
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
					blockPage, pageNum, "/Thousand/main.do?");  // 바로가기 영역 HTML 문자열
		}
		map.put("pagingString", pagingString);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
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
	@Override
	public int selectCount() {
		return 0;
	}

	@Override
	public int selectCount(String id) {
		return 0;
	}

	@Override
	public void insertPost(PostDTO pDTO) {
	}

	@Override
	public void updatePost(PostDTO pDTO) {
	}

	@Override
	public void deletePost(PostDTO pDTO) {

	}
	

}
