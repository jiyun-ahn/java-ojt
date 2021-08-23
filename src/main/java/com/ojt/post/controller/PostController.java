package com.ojt.post.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ojt.post.model.Label;
import com.ojt.post.model.Post;
import com.ojt.post.model.User;
import com.ojt.post.service.LabelService;
import com.ojt.post.service.PostService;
import com.ojt.post.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private LabelService labelService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String pageMain( HttpServletRequest request, Model model) {
		return getPostList(request, 0, 1, 10, model);
	}
	
	/**
	 * 게시물 목록 조회
	 */
	@RequestMapping(value = "/post/list", method = RequestMethod.GET)
	public String getPostList( HttpServletRequest request,
			@RequestParam(value="labelSeq", required=true, defaultValue="0") Integer labelSeq,
			@RequestParam(value="page", required=true, defaultValue="1") Integer page,
			@RequestParam(value="limit", required=true, defaultValue="10") Integer limit,
			Model model) {
		List<Label> labelList = new ArrayList<Label>();
		List<Post> postList = new ArrayList<Post>();
		
		try {
			// 라벨 리스트 조회
			labelList = labelService.selectLabelList();
			
			// 게시물 리스트 조회
			postList = postService.selectPostList(labelSeq, page, limit);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("labelList", labelList);
		result.put("postList", postList);
		result.put("labelSeq", labelSeq);
		result.put("page", page);
		result.put("limit", limit);
		
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");
		model.addAttribute("result", result);
		
		return "post/postList";
	}
	
	/**
	 * 게시물 상세정보 조회
	 */
	@RequestMapping(value = "/post/detailView", method = RequestMethod.GET)
	public String getPostDetail( HttpServletRequest request,
			@RequestParam(value="postSeq", required=true, defaultValue="0") String postSeq,
			Model model) {
		
		Post post = null;
		try {
			// 게시물 상세정보 조회
			post = postService.selectPostDetail(postSeq);
			
			// 조회수 증가
			int increResult = postService.increPostCount(postSeq);
			if (0 == increResult) {
				logger.error("PostController.getPostDetail() : 게시물 조회 오류");
			}
			
			if (null == post) {
				throw new Exception("게시물 정보가 존재하지 않습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("post", post);
		
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");
		model.addAttribute("result", result);
				
		return "post/postDetail";
	}
	
	
	/**
	 * 게시물 등록/수정 페이지로 이동
	 */
	@RequestMapping(value = "/post/writeView", method = RequestMethod.GET)
	public String writePostView(HttpServletRequest request,
			@RequestParam(value="mode", required=true) String mode,
			@RequestParam(value="postSeq", required=false) String postSeq,
			Model model) {
		
		List<Label> labelList = new ArrayList<Label>();
		Post post = null;
		
		try {
			// 라벨 목록 조회
			labelList = labelService.selectLabelList();
			
			// 수정 모드
			if ("modify".equals(mode)){
				// 게시물 정보 조회
				post = postService.selectPostDetail(postSeq);
				if (null == post) {
					throw new Exception("게시물 정보가 존재하지 않습니다.");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("mode", mode);
		result.put("labelList", labelList);
		result.put("post", post);
		
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");
		model.addAttribute("result", result);
		
		return "post/postWrite";
	}

	
	/**
	 * 게시물 등록
	 */
	@RequestMapping(value = "/post/create", method = RequestMethod.POST)
	public String createPost(HttpServletRequest request,
			@RequestParam(value="title", required=true) String title,
			@RequestParam(value="content", required=false, defaultValue = "") String content,
			@RequestParam(value="labelSeq", required=true) Integer labelSeq,
			@RequestParam(value="authorId", required=true) String authorId,
			Model model) {
		try {
			// 게시물 고유번호 생성
			String postSeq = UUID.randomUUID().toString();
			postSeq = postSeq.replace("-", "").toUpperCase();
			
			Post post = new Post();
			post.setSeq(postSeq);
			post.setTitle(title);
			post.setContent(content);
			post.setLabelSeq(labelSeq);
			post.setAuthorId(authorId);
			
			// 사용자 고유번호 조회
			User user = userService.selectUserInfo(authorId);
			post.setAuthorSeq(user.getSeq());
			
			// 게시물 등록
			if (0 < postService.insertPost(post)) {
				// 게시물 카운트 정보 등록
				postService.insertPostCount(postSeq);
				
				// 첨부파일 등록
				postService.insertAttachFile(post);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");
		
		// 완료 후 리스트 페이지로 이동
		return getPostList(request, 0, 1, 10, model);
	}
	
	
	/**
	 * 게시물 수정
	 */
	@RequestMapping(value = "/post/modify", method = RequestMethod.PUT)
	public String modifyPost(HttpServletRequest request,
			@RequestParam(value="title", required=true) String title,
			@RequestParam(value="content", required=false, defaultValue = "") String content,
			@RequestParam(value="labelSeq", required=true) Integer labelSeq,
			@RequestParam(value="postSeq", required=true) String postSeq,
			Model model) {
		try {
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setLabelSeq(labelSeq);
			post.setSeq(postSeq);

			// 게시물 수정
			if (0 < postService.updatePost(post)) {
				// 첨부파일 등록 및 삭제 (미구현)
				postService.insertAttachFile(post);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");
		
		return getPostList(request, 0, 1, 10, model);
	}
	
	/**
	 * 게시물 일괄 변경
	 */
	@RequestMapping(value = "/posts/modify", method = RequestMethod.DELETE)
	public String modifyPosts(HttpServletRequest request,
			@RequestParam(value="type", required=true, defaultValue="") String type,
			@RequestParam(value="value", required=true, defaultValue="") String value,
			@RequestParam(value="postSeqs", required=true, defaultValue="") String postSeqs,
			
			Model model) {
		try {
			Post post = new Post();
			
			String[] postSeqArray = postSeqs.split("|");
			String paramSeqs = "";
			for (String postSeq : postSeqArray) {
				paramSeqs += ",'" + postSeq + "'";
			}
			post.setSeq(paramSeqs);
			
			switch (type) {
				case "importantYN":
					post.setImportantYN(value);
					break;
				case "labelSeq":
					post.setLabelSeq(Integer.parseInt(value));
					break;
				default:
					throw new Exception("수정할 수 없는 변경값입니다.");
			}
		
			// 게시물 수정
			if (0 < postService.updatePosts(post)) {
				// 첨부파일 등록 및 삭제 (미구현)
				postService.insertAttachFile(post);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");
		
		int labelSeq = Integer.parseInt(request.getParameter("labelSeq"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		return getPostList(request, labelSeq, page, limit, model);
	}

	/**
	 * 게시물 일괄 삭제
	 */
	@RequestMapping(value = "/posts/delete", method = RequestMethod.DELETE)
	public String deletePosts(HttpServletRequest request,
			@RequestParam(value="postSeqs", required=true, defaultValue="") String postSeqs,
			Model model) {
		
		try {
			ArrayList<String> postSeqlist = new ArrayList<String>(Arrays.asList(postSeqs.split("|")));
		
			// 첨부파일 등록 및 삭제 (미구현)
			postService.deleteAttachFile(postSeqs);

			// 게시물 카운트 정보 삭제
			postService.deletePostCount(postSeqlist);
			
			// 게시물 삭제
			postService.deletePost(postSeqlist);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", 204);
			model.addAttribute("desc", e.getMessage());
			return "error";
		}
		
		// 결과값 세팅
		model.addAttribute("status", 200);
		model.addAttribute("desc", "success");

		int labelSeq = Integer.parseInt(request.getParameter("labelSeq"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		return getPostList(request, labelSeq, page, limit, model);
	}
	
}
