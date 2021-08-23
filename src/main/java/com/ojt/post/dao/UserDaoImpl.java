package com.ojt.post.dao;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ojt.post.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	private static final String namespace = "com.ojt.post.mappers.user";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public User selectUserInfo(String id) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return sqlSession.selectOne(namespace + ".selectUserInfo", param);
	}

}
