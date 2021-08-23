package com.ojt.post.model;

import org.springframework.stereotype.Component;

/**
 * 사용자(계정) 정보
 */
@Component
public class User {
	private String seq = null;			// 사용자 고유번호
	private String id = null;			// 사용자 아이디
	private String password = null;	// 사용자 비밀번호
	private String name = null;		// 사용자 이름
	private int level = 0;			// 사용자 권한 등급
	
	/**
	 * 사용자 고유번호
	 */
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 사용자 아이디
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 사용자 비밀번호
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 사용자 이름
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 사용자 권한 등급
	 */
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
