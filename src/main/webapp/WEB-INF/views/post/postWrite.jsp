<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>OFFICE NOTICE</title>
	
	<link href="/css/base/normal.css" rel="stylesheet" type="text/css" />
	<link href="/css/postDetail.css" rel="stylesheet" type="text/css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/postDetail.js"></script>
	
	<!-- summernote 는 부트스트랩과 jQuery를 기본으로 사용함 -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/base/header.jsp"%>
	<hr class="post_detail_hr">
	<div class="post_detail_main">
		<c:choose>
			<c:when test="${'create' == result.mode}">
				<form method="POST" action="/post/create">
					<div class="post_detail_write">
						<div class="post_detail_name">&nbsp;&nbsp;label&nbsp;&nbsp;</div>
						<div class="post_detail_labels">
							<select id="labelList" name="labelSeq" class="post_detail_label" >
								<c:forEach var="label" items="${result.labelList}">
									<option value="${label.seq}">${label.name}</option>
								</c:forEach>
							</select>
						</div>
						<br>
						<div class="post_detail_name">subject</div>
						<input type="text" id="postTitle" name="title" size="100" class="post_detail_input_title" required="required"><br>
						<textarea id="postContent" name="content" required="required"></textarea>
						
						<!-- 회원 기능 미구현. 임의의 사용자 아이디 지정 -->
						<input type="hidden" id="authorId" name="authorId" value="admin"/>
					</div>
					<div class="post_detail_submit">
						<input type="submit" value="submit" class="post_detail_btn">
						<input type="button" value="back" class="post_detail_btn" onclick="window.history.back();">
					</div>
				</form>
			</c:when>
			
			<c:when test="${'modify' == result.mode}">
				<div class="post_detail_write">
					<!-- 라벨 -->
					<div class="post_detail_name">&nbsp;&nbsp;label&nbsp;&nbsp;</div>
					<div class="post_detail_labels">
						<select id="labelList" name="labelSeq" class="post_detail_label">
							<c:forEach var="label" items="${result.labelList}">
								<c:choose>
									<c:when test="${result.post.labelSeq == label.seq}">
										<option value="${label.seq}" selected="selected">${label.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${label.seq}">${label.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<br>
					<!-- 제목 -->
					<div class="post_detail_name">subject</div>
					<input type="text" id="postTitle" name="title" size="100" class="post_detail_input_title" required="required" value="${result.post.title}"><br>
					
					<!-- 내용 -->
					<textarea id="postContent" name="content" required="required">${result.post.content}</textarea>
					
					<!-- 회원 기능 미구현. 임의의 사용자 아이디 지정 -->
					<input type="hidden" id="authorId" name="authorId" value="admin"/>
				</div>
				<div class="post_detail_submit">
					<input type="button" value="submit" class="post_detail_btn" onclick="modifyPost('${result.post.seq}');">
					<input type="button" value="back" class="post_detail_btn" onclick="window.history.back();">
				</div>
			</c:when>
		</c:choose>
	</div>
	<%@ include file="/WEB-INF/views/base/footer.jsp"%>
</body>
</html>