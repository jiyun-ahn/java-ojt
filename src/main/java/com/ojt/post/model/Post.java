package com.ojt.post.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 * 게시물의 정보
 */
@Component
public class Post {
	private String seq = null;				// 게시물 번호
    private String title = null;			// 제목
    private String content = null;			// 내용
    private Timestamp createDate = null;	// 등록일
    private String authorSeq = null;		// 작성자 고유번호
    private String authorId = null;			// 작성자 아이디
    private String authorName = null;		// 작성자 이름
    private int viewCount = 0;				// 조회수
    private int likeCount = 0;				// 좋아요 수
    private String importantYN = null;		// 중요여부
    private int labelSeq = 0;				// 라벨번호
    private String labelName = null;		// 라벨명
    private ArrayList<String> attachFiles = null;   // 첨부파일 리스트

	/**
     * 게시물 번호
     */
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 제목
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 내용
	 */
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 등록일
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 작성자 계정 고유번호
	 */
	public String getAuthorSeq() {
		return authorSeq;
	}
	public void setAuthorSeq(String authorSeq) {
		this.authorSeq = authorSeq;
	}
	
	/**
	 * 작성자 계정 ID
	 */
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	/**
	 * 작성자 이름
	 */
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	/**
	 * 조회수
	 */
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	/**
	 * 좋아요수
	 */
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	/**
	 * 중요 표시 여부
	 */
	public String getImportantYN() {
		return importantYN;
	}
	public void setImportantYN(String importantYN) {
		this.importantYN = importantYN;
	}
	
	/**
	 * 라벨번호
	 */
	public int getLabelSeq() {
		return labelSeq;
	}
	public void setLabelSeq(int labelSeq) {
		this.labelSeq = labelSeq;
	}
	
	/**
	 * 라벨명
	 */
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	/**
	 * 첨부파일 리스트
	 */
	public ArrayList<String> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(ArrayList<String> attachFiles) {
		this.attachFiles = attachFiles;
	}
	
}
