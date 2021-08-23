package com.ojt.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojt.post.dao.PostDao;
import com.ojt.post.model.Post;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;

	@Override
	public List<Post> selectPostList(int labelSeq, int page, int limit) throws Exception {
		int offset = ((page - 1) * limit) + 1;
		return postDao.selectPostList(labelSeq, offset, limit);
	}

	@Override
	public Post selectPostDetail(String postSeq) throws Exception {
		return postDao.selectPostDetail(postSeq);
	}

	@Override
	public int deletePost(ArrayList<String> postSeqList) throws Exception {
		String postSeqs = "";
		if (0 < postSeqList.size()) {
			for (String postSeq : postSeqList) {
				postSeqs += ",'" + postSeq + "'";
			}
			postSeqs = postSeqs.substring(1);
			
		} else {
			throw new Exception("게시물 번호가 존재하지 않습니다.");
		}
		return postDao.deletePost(postSeqs);
	}

	@Override
	public int deletePostCount(ArrayList<String> postSeqList) throws Exception {
		String postSeqs = "";
		if (0 < postSeqList.size()) {
			for (String postSeq : postSeqList) {
				postSeqs += ",'" + postSeq + "'";
			}
			postSeqs = postSeqs.substring(1);
			
		} else {
			throw new Exception("게시물 번호가 존재하지 않습니다.");
		}
		return postDao.deletePostCount(postSeqs);
	}

	@Override
	public int deleteAttachFile(String postSeqs) throws Exception {
		return postDao.deleteAttachFile(postSeqs);	// 미구현 기능
	}
	
	@Override
	public int insertPost(Post post) throws Exception {
		return postDao.insertPost(post);
	}

	@Override
	public int insertPostCount(String postSeq) throws Exception {
		return postDao.insertPostCount(postSeq);
	}

	@Override
	public int insertAttachFile(Post post) throws Exception {
		return postDao.insertAttachFile(post);	// 미구현 기능
	}

	@Override
	public int updatePosts(Post post) throws Exception {
		return postDao.updatePosts(post);
	}

	@Override
	public int updatePost(Post post) throws Exception {
		return postDao.updatePost(post);
	}

	@Override
	public int increPostCount(String postSeq) throws Exception {
		return postDao.increPostCount(postSeq);
	}

}
