<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>OFFICE NOTICE</title>

		<link href="/css/base/normal.css" rel="stylesheet" type="text/css" />
		<link href="/css/postDetail.css" rel="stylesheet" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script type="text/javascript" src="/js/postDetail.js"></script>
	</head>
	<body>
		<%@ include file="/WEB-INF/views/base/header.jsp"%>
		<hr class="post_detail_hr">
		<div class="post_detail_main">
			<input type="hidden" id="postSeq" name="postSeq" value="${result.post.seq}">
			<div class="post_detail_view">
				<div class="post_detail_view_info">
					<!-- 라벨 -->
					<div class="post_detail_name">&nbsp;&nbsp;label&nbsp;&nbsp;</div>
					<span class="post_detail_value">${result.post.labelName}</span>
					<br>
					
					<!-- 제목 -->
					<div class="post_detail_name">subject</div>
					<span class="post_detail_value">${result.post.title}</span>
					<c:if test="${'Y' == post.importantYN}">
						<span class="important_mark">&nbsp;★</span>
					</c:if>
					<br>
					
					<!-- 작성자 -->
					<div class="post_detail_name">&nbsp;author&nbsp;</div>
					<span class="post_detail_value">${result.post.authorName}</span>
					<br>
				</div>
				<div class="post_detail_view_info">
					<!-- 등록일 -->
					<div class="post_detail_name sub_content">date created</div>
					<span class="post_detail_value">${result.post.createDate}</span>
					<br>
					
					<!-- 조회수 -->
					<div class="post_detail_name sub_content">&nbsp;view count&nbsp;&nbsp;</div>
					<span class="post_detail_value">${result.post.viewCount}</span>
					<br>
					
					<!-- 좋아요수 -->
					<div class="post_detail_name sub_content">&nbsp;&nbsp;like count&nbsp;&nbsp;</div>
					<span class="post_detail_value">${result.post.likeCount}</span>
					<br>
				</div>

				<!-- 내용 -->
				<div class="post_detail_view_info_content">
					<div class="post_detail_content_box">
					${result.post.content}
					</div>
				</div>
			</div>
			<div class="post_detail_submit">
				<input type="button" value="modify" class="post_detail_btn" onclick="modifyPostView();">
				<input type="button" value="back" class="post_detail_btn" onclick="window.history.back();">
			</div>
		</div>
		<%@ include file="/WEB-INF/views/base/footer.jsp"%>
	</body>
</html>