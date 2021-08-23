package com.ojt.post.service;

import java.util.List;

import com.ojt.post.model.Label;

public interface LabelService {
	
	/**
	 * 라벨 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<Label> selectLabelList() throws Exception;
	
	/**
	 * 라벨 번호에 해당하는 라벨명 조회
	 * @param labelSeq
	 * @return
	 * @throws Exception
	 */
	public String selectLabelName(int labelSeq) throws Exception;
}
