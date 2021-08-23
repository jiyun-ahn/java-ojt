package com.ojt.post.service;

import java.util.ArrayList;
import java.util.List;

import com.ojt.post.model.Post;

public interface PostService {
	
	/**
	 * 게시물 목록 조회
	 * @param labelSeq
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Post> selectPostList(int labelSeq, int page, int limit) throws Exception;

	/**
	 * 게시물 상세정보 조회하는 함수
	 * @param postSeq
	 * @return
	 * @throws Exception
	 */
	public Post selectPostDetail(String postSeq) throws Exception;
	
	/**
	 * 게시물 목록 조회하는 함수
	 * @param postSeqs
	 * @return
	 * @throws Exception
	 */
	public int deletePost(ArrayList<String> postSeqList) throws Exception;
	
	/**
	 * 게시물의 카운트 정보 삭제
	 * @param postSeqs
	 * @return
	 * @throws Exception
	 */
	public int deletePostCount(ArrayList<String> postSeqList) throws Exception;
	
	/**
	 * 게시물의 첨부파일 삭제
	 * @param postSeqs
	 * @return
	 * @throws Exception
	 */
	public int deleteAttachFile(String postSeqs) throws Exception;
	
	/**
	 * 게시물 등록
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int insertPost(Post post) throws Exception;
	
	/**
	 * 게시물 카운트 정보 등록
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int insertPostCount(String postSeq) throws Exception;
	
	/**
	 * 게시물 첨부파일 등록
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int insertAttachFile(Post post) throws Exception;
	
	/**
	 * 게시물 일괄 수정
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int updatePosts(Post post) throws Exception;
	
	/**
	 * 게시물 수정
	 * @param post
	 * @return
	 * @throws Exception
	 */
	public int updatePost(Post post) throws Exception;
	
	/**
	 * 게시물 카운트 증가
	 * @param postSeq
	 * @return
	 * @throws Exception
	 */
	public int increPostCount(String postSeq) throws Exception;
	
}
