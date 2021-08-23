/**
 * 게시물 기능 스크립트
 */

/**
 * 조회 목록의 레이블 변경
 */
function changeLabel(selectObject){
	let selectOption = selectObject.options[selectObject.selectedIndex].value;
	let url = '/post/list';
	let param = "?labelSeq=" + selectOption +
				"&page=" + $('input#page').val() +
				"&limit=" + $('input#limit').val();
	location.href = url + param;
}

/**
 * 게시물 작성 페이지로 이동
 */
function writePost(){
	location.href = '/post/writeView?mode=create';
}

/**
 * 게시물 상세보기 페이지로 이동
 */
function postDetailView(postSeq) {
	let url = '/post/detailView';
	let param = '?postSeq=' + postSeq;
	location.href = url + param;
}

/**
 * 게시물 수정 or 수정값을 선택 가능한 모달 띄움
 * @param type
 * @returns
 */
function modifyPost(type, value){
	switch (type) {
		// 라벨 변경
		case 'label':
			$('#selectLabel').show();
			break;
		
		// 중요 표시/해제
		case 'important':
			// 선택한 게시물 번호 문자열로 세팅
			let postSeqs = setTargetPostSeqs();
			if (0 == postSeqs.length) {
				alert("1개 이상의 게시물을 선택해야 합니다.");
				return false;
			}
			
			// 변경 URL과 파라미터
			let url = "/post/modify";
			let param = "?postSeqs=" + postSeqs.substr(1) +
						"&type=" + type +
						"&value=" + value +
						"&labelSeq=" + $('input#labelSeq').val() +
						"&page=" + $('input#page').val() + 
						"&limit=" + $('input#limit').val();
			
			location.href= url + param;
			break;
	}
	
}

/**
 * 게시물 일괄 삭제
 * @param seqList
 */
function deletePost() {
	let postSeqs = setTargetPostSeqs();
	if (0 == postSeqs.length) {
		alert("1개 이상의 게시물을 선택해야 합니다.");
		return false;
	}
	
    let url = "/posts/delete";
    let param = "?postSeqs=" + postSeqs.substr(1) +
    			"&labelSeq=" + $('input#labelSeq').val() +
    			"&page=" + $('input#page').val() + 
    			"&limit=" + $('input#limit').val();
    location.href = url + param;
}

/**
 * 게시물 번호를 |로 구분하여 합친다. (문자열 리턴)
 * @returns postSeqs
 */
function setTargetPostSeqs() {
	let postSeqs = "";
	let posts = $('.post_list_content_element').find('#postSeq:checked').get();
	let postSize = posts.length;
	
	if (0 < postSize) {
		for (let i = 0; i < postSize; i++) {
			postSeqs += "|" + posts[i].value;
		}
	}
	return postSeqs;
}

/**
 * 선택한 게시물의 라벨을 변경한다. (모달에서 선택한 라벨로)
 * @param labelSeq
 * @returns
 */
function selectLabel(labelSeq) {
	let postSeqs = setTargetPostSeqs();
	if (0 == postSeqs.length) {
		alert("1개 이상의 게시물을 선택해야 합니다.");
		return false;
	}
	
	let url = "/posts/modify";
	let param = "?postSeqs=" + postSeqs.substr(1) +
				"&type=label" +
				"&value=" + labelSeq +
				"&labelSeq=" + $('input#labelSeq').val() +
				"&page=" + $('input#page').val() + 
				"&limit=" + $('input#limit').val();
	
	location.href= url + param;
}