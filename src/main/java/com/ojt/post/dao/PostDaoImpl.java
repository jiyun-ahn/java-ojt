package com.ojt.post.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ojt.post.model.Post;

@Repository
public class PostDaoImpl implements PostDao {
	private static final String namespace = "com.ojt.post.mappers.post";
	
	@Inject
	private SqlSession sqlSession;	// DB SQL명령어를 실행하기 위한 메소드를 제공한다.
	
	@Override
	public List<Post> selectPostList(int labelSeq, int offset, int limit) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("labelSeq", labelSeq);
		param.put("offset", offset);
		param.put("limit", limit);
		return sqlSession.selectList(namespace + ".selectPostList", param);
	}

	@Override
	public Post selectPostDetail(String postSeq) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("postSeq", postSeq);
		return sqlSession.selectOne(namespace + ".selectPostDetail", param);
	}

	@Override
	public int deletePost(String postSeqs) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("postSeqs", postSeqs);
		return sqlSession.delete(namespace + ".deletePost", param);
	}

	@Override
	public int deletePostCount(String postSeqs) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("postSeqs", postSeqs);
		return sqlSession.delete(namespace + ".deletePostCount", param);
	}

	@Override
	public int deleteAttachFile(String postSeqs) throws Exception {
		// 미구현 기능
		return 0;
	}

	@Override
	public int insertPost(Post post) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("seq", post.getSeq());
		param.put("title", post.getTitle());
		param.put("content", post.getContent());
		param.put("authorSeq", post.getAuthorSeq());
		param.put("labelSeq", post.getLabelSeq());
		return sqlSession.insert(namespace + ".insertPost", param);
	}

	@Override
	public int insertPostCount(String postSeq) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("postSeq", postSeq);
		return sqlSession.delete(namespace + ".insertPostCount", param);
	}

	@Override
	public int insertAttachFile(Post post) throws Exception {
		// 미구현 기능
		return 0;
	}

	@Override
	public int updatePosts(Post post) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("seqs", post.getSeq());
		param.put("importantYN", post.getImportantYN());
		param.put("labelSeq", post.getLabelSeq());
		return sqlSession.update(namespace + ".updatePosts", param);
	}

	@Override
	public int updatePost(Post post) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("postSeq", post.getSeq());
		param.put("title", post.getTitle());
		param.put("content", post.getContent());
		param.put("labelSeq", post.getLabelSeq());
		return sqlSession.update(namespace + ".updatePost", param);
	}

	@Override
	public int increPostCount(String postSeq) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("postSeq", postSeq);
		return sqlSession.update(namespace + ".increPostCount", param);
	}

}
