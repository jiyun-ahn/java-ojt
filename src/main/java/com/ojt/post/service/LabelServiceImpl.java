package com.ojt.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojt.post.dao.LabelDao;
import com.ojt.post.model.Label;

@Service
public class LabelServiceImpl implements LabelService {
	
	@Autowired
	private LabelDao labelDao;

	@Override
	public List<Label> selectLabelList() throws Exception {
		return labelDao.selectLabelList();
	}

	@Override
	public String selectLabelName(int labelSeq) throws Exception {
		return labelDao.selectLabelName(labelSeq);
	}

}
