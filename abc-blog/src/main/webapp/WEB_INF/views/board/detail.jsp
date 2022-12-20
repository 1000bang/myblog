
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">

	<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
	<c:if test="${boardData.userId.id == principal.user.id}">
		<a class="btn btn-warning" id="" href="/board/${boardData.id}/update_form">수정</a>
		<button class="btn btn-danger" id="btn--delete">삭제</button>
	</c:if> 
	<br /> <br /> <br />

	<div>
		<input type="hidden" id="board-id" value="${boardData.id}">
		글 번호 : <span> <i> ${boardData.id + 100} </i></span>
	</div>
	<div>
		글 작성자 : <span> <i> ${boardData.userId.username} </i></span>
	</div>
	<br /> <br /> <br />
	<div class="">
		<h3>${boardData.title}</h3>
	</div>
	<br /> <br />
	<div>${boardData.content}</div>
	<br /> <br /> <br />

	

</div>


<script type="text/javascript" src="/js/board.js">
	
</script>
<%@ include file="../layout/footer.jsp"%>