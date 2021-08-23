package com.ojt.post.service;

import com.ojt.post.model.User;

public interface UserService {
	
	/**
	 * 사용자 정보를 조회
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User selectUserInfo(String id) throws Exception;
}
