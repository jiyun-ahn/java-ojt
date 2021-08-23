package com.ojt.post.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ojt.post.model.Label;

@Repository
public class LabelDaoImpl implements LabelDao{
	private static final String namespace = "com.ojt.post.mappers.label";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<Label> selectLabelList() throws Exception {
		return sqlSession.selectList(namespace + ".selectLabelList");
	}

	@Override
	public String selectLabelName(int labelSeq) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("labelSeq", labelSeq);
		return sqlSession.selectOne(namespace + ".selectLabelName", param);
	}

}
