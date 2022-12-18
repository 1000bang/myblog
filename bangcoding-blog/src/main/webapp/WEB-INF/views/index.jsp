<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>

<!-- var = 여기서 쓸 변수명 items boardController에서 받아온 변수 -->
<c:forEach var="board" items="${boardData}">
	<div class="card m-2">
		<div class="card-body">
			<h4> ${board.title}</h4>
			<p> ${board.content}</p>
			<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>

		</div>
	</div>
	<br>
</c:forEach>

<%@ include file="layout/footer.jsp" %>