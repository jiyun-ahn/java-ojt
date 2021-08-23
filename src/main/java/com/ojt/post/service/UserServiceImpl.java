package com.ojt.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojt.post.dao.UserDao;
import com.ojt.post.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User selectUserInfo(String id) throws Exception {
		return userDao.selectUserInfo(id);
	}
	
}
