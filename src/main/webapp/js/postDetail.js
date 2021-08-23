/**
 * 게시물 상세보기/신규작성/수정 기능 스크립트
 */
$(document).ready(function() {
	// 게시물 내용 작성용 에디터 설정
	$('#postContent').summernote({
		height: 300,				// 에디터 높이
		minHeight: null,			// 최소 높이
		maxHeight: null,			// 최대 높이
		focus: true,				// 에디터 로딩 후 포커스 여부
		lang: "ko-KR",				// 한글 설정
		placeholder: '최대 2048자까지 작성할 수 있습니다.'		// placeholder 설정
	 });
});


/**
 * 게시물 수정 페이지로 이동
 */
function modifyPostView(postSeq) {
	let url = '/post/writeView';
	let param = '?mode=modify&postSeq=' + $('input#postSeq').val();
	location.href = url + param;
}

/**
 * 게시물 수정
 */
function modifyPost(postSeq) {
	let labelSeq = $('#labelList').val();
	let title = $('#postTitle').val();
	let content = $('#postContent').val();
	
	if (null == title || "" == title) {
		alert("제목을 입력하세요.")
		return false;
	}
	
	if (null == content || "" == content) {
		alert("내용을 입력하세요.")
		return false;
	}
	
	$.ajax({
		url: "/post/modify",
		type: "PUT",
		data: {
			'postSeq': postSeq,
			'labelSeq': labelSeq,
			'title': title,
			'content': content,
		},
		success: function(result) {
			alert("수정 완료하였습니다.");
			location.href = "/";
		},
		error: function(e) {
			location.href = e.responseText;
		}
	});
}