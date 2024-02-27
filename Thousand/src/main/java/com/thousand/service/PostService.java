package com.thousand.service;

import java.util.List;
import java.util.Map;

import com.thousand.dto.PostDTO;

public interface PostService {
	//전체글 불러오기
	public List<PostDTO> selectPostsAll(Map<String,Object> map);
	//내가작성한 전체글 불러오기
	public List<PostDTO> selectMyPost(String id, Map<String,Object> map);
	//상세글 불러오기
	//plusReadCount 사용.
	public PostDTO selectOnePost(int pno);
	//전체 글 수 확인
	public int selectCount();
	//해당 아이디 글 수 확인
	public int selectCount(String id);
	//글 작성
	public void insertPost(PostDTO pDTO);
	// 글 수정
	public void updatePost(PostDTO pDTO);
	// 글 삭제
	public void deletePost(PostDTO pDTO);
	
}