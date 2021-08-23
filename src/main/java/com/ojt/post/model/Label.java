package com.ojt.post.model;

import org.springframework.stereotype.Component;

/**
 * 게시물의 라벨 정보
 */
@Component
public class Label {
	
	private int seq = 0;			// 라벨 번호
    private String name = null;		// 라벨명

	/**
	 * 라벨 번호
	 */
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * 라벨명
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	
    
    
}
