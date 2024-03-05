package com.thousand.service;

import java.util.List;
import java.util.Map;

import com.thousand.dto.PostDTO;
import com.thousand.dto.SearchDTO;

public interface PostService {
	//전체글 불러오기
	public List<PostDTO> selectPostsAll(Map<String, Object> map);
	//내가작성한 전체글 불러오기
	public List<PostDTO> selectMyPost(String id,Map<String, Object> map);
	//페이징 처리(전체게시물)
	public Map<String, Object> paging(SearchDTO searchDTO);
	//페이징 처리(내게시물)
	public Map<String, Object> paging(String id, SearchDTO searchDTO);
	//상세글 불러오기
	//plusReadCount 사용.
	public PostDTO selectOnePost(int pno);
	//상세보기시 글 작성자와 해당 아이디가 일치하는지 체크
	public boolean checkPnoId(int pno,String id);
	//전체 글 수 확인
	public int selectCount();
	//해당 아이디 글 수 확인
	public int selectCount(String id);
	//글 작성
	public int insertPost(PostDTO pDTO);
	// 글 수정
	public void updatePost(PostDTO pDTO);
	// 글 삭제
	public void deletePost(PostDTO pDTO);
}
