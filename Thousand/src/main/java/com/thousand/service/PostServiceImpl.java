package com.thousand.service;

import java.util.List;
import java.util.Map;

import com.thousand.dto.PostDTO;

public class PostServiceImpl implements PostService {

	@Override
	public List<PostDTO> selectPostsAll(Map<String, Object> map) {
		return null;
	}

	@Override
	public List<PostDTO> selectMyPost(String id, Map<String, Object> map) {
		return null;
	}

	@Override
	public PostDTO selectOnePost(int pno) {
		return null;
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
