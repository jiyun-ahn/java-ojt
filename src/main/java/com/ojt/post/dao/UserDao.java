package com.ojt.post.dao;

import com.ojt.post.model.User;

public interface UserDao {
	
	/**
	 * 사용자 정보를 조회하는 함수
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User selectUserInfo(String id) throws Exception;
}
