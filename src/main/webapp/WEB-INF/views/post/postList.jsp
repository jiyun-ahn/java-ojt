<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>OFFICE NOTICE</title>
	
	<link rel="stylesheet" type="text/css" href="/css/base/normal.css" />
	<link rel="stylesheet" type="text/css" href="/css/postList.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/postList.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/base/header.jsp"%>
	<!-- input hidden -->
	<input id="labelSeq" type="hidden" value="${result.labelSeq}">
	<input id="page" type="hidden" value="${result.page}">
	<input id="limit" type="hidden" value="${result.limit}">
	<input id="changeLabel" type="hidden" value="0">

	<div class="post_list_main">
		<hr>
		<div class="post_list_menubar">
			<!-- 라벨 셀렉트 리스트 -->
			<div class="post_list_menubar_labels">
				<select id="labelList" class="post_list_menubar_label" onChange="changeLabel(this);">
					<option value="0">전체</option>
					<c:forEach var="label" items="${result.labelList}">
						<c:choose>
							<c:when test="${result.labelSeq == label.seq}">
								<option value="${label.seq}" selected="selected">${label.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${label.seq}">${label.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			
			<!-- 메뉴 버튼 -->
			<div class="post_list_menubar_btns">
				<input id="btnImportant" type="button" value="중요 표시" class="post_list_menu_btn_2" onclick="modifyPost('important', 'Y');">
				<input id="btnImportant" type="button" value="중요 해제" class="post_list_menu_btn_2" onclick="modifyPost('important', 'N');">
				<input id="btnDelete" type="button" value="일괄삭제" class="post_list_menu_btn_2" onclick="deletePost();">
				<input id="btnModify" type="button" value="라벨변경" class="post_list_menu_btn_2" onclick="modifyPost('label');">
				<input id="btnWrite" type="button" value="글쓰기" class="post_list_menu_btn_1" onclick="writePost();">
			</div>
		</div>
		
		<!-- 게시물 목록 헤더 -->
		<div class="post_list_header">
			<div class="post_list_header_element" style="width: 3%">&nbsp;</div>
			<div class="post_list_header_element" style="width: 15%">라벨</div>
			<div class="post_list_header_element" style="width: 50%">제목</div>
			<div class="post_list_header_element" style="width: 12%">작성일</div>
			<div class="post_list_header_element" style="width: 10%">작성자</div>
			<div class="post_list_header_element" style="width: 10%">조회수</div>
		</div>
		
		<!-- 게시물 목록 데이터 -->
		<div class="post_list_content">
			<c:forEach var="post" items="${result.postList}">
				<!-- 게시물 번호 -->
				<div class="post_list_content_element" style="width: 3%">
					<input id="postSeq" type="checkbox" value="${post.seq}">
				</div>
				<!-- 게시물 라벨 -->
				<div class="post_list_content_element" style="width: 15%">
					${post.labelName}
				</div>
				<!-- 게시물 제목 -->
				<div class="post_list_content_element post_title" style="width: 50%" onclick="postDetailView('${post.seq}')">
					<c:if test="${'Y' == post.importantYN}">
						<span class="important_mark">★&nbsp;</span>
					</c:if>
					${post.title}
				</div>
				<!-- 게시물 등록일 -->
				<div class="post_list_content_element" style="width: 10%">
					${post.createDate}
				</div>
				<!-- 게시물 작성자 -->
				<div class="post_list_content_element" style="width: 10%">
					${post.authorName}
				</div>
				<!-- 게시물 조회수 -->
				<div class="post_list_content_element" style="width: 10%">
					${post.viewCount}
				</div>
			</c:forEach>
		</div>
		<div class="post_list_nav">
		</div>
		
		<!-- 라벨 변경 버튼 리스트 -->
		<div id="selectLabel" class="post_list_select_label">
			<b>선택한 게시물을 아래의 라벨로 변경합니다.</b>
			<hr>
			<c:forEach var="label" items="${result.labelList}">
				<input type="button" value="${label.name}" onclick="selectLabel(${label.seq})">
			</c:forEach>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/base/footer.jsp"%>
</body>
</html>
