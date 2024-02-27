package com.thousand.repository;

import java.util.List;
import java.util.Map;

import com.thousand.dto.PostDTO;

public class PostRepositoryImpl implements PostRepository{

	@Override
	public List<PostDTO> selectPostsAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> selectMyPost(String id, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO selectOnePost(int pno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plusReadCount(int pno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertPost(PostDTO pDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePost(PostDTO pDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(PostDTO pDTO) {
		// TODO Auto-generated method stub
		
	}

}
