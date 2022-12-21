<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<c:choose>
	<c:when test="${empty principal}">
	</c:when>
	<c:otherwise>
		<div class="alert alert-success">
			<strong>${principal.username}</strong>&nbsp; 님 환영합니다 !
		</div>
	</c:otherwise>
</c:choose>

<br>
<div class="container-foalt">
	<div class="row">
		<c:forEach var="num" items="${boardData.content}">

			<div class="col-md-6">
				<div class="card m-2">
					<div class="card-body">
						<h4>${num.title}</h4>
						<p>${num.content}</p>
						<a href="/board/${num.id}" class="btn btn-success">상세보기</a>
					</div>
				</div>
			</div>

		</c:forEach>
	</div>
</div>

<ul class="pagination justify-content-center" style="margin: 20px 0">

	<c:set var="isDisabled" value="disabled">
	</c:set>
	<c:set var="isNotDisabled" value="">
	</c:set>
	<li class="page-item ${boardData.first ? isDisabled : isNotDisabled}">
	<a class="page-link text-dark" href="?page=${startPage}">처음</a></li>
	<li class="page-item ${boardData.first ? isDisabled : isNotDisabled}">
	<a class="page-link text-dark" href="?page=${boardData.number - 1}">◀</a></li>
	<c:forEach var="num" items="${pageNumbers}">
		<c:choose>
			<c:when test="${nowPage eq num}">
				<li class="page-item active bg-dark">
				<a class="page-link text-dark " href="?q=${q}&page=${num - 1}">${num}
				</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link text-dark" href="?q=${q}&page=${num - 1}">${num}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<li class="page-item ${boardData.last ? isDisabled : isNotDisabled}">
	<a class="page-link text-dark" href="?page=${boardData.number + 1}">▶</a></li>
	<li class="page-item ${boardData.last ? isDisabled : isNotDisabled}">
	<a class="page-link text-dark" href="?page=${endPage}">끝 </a></li>
</ul>





<%@ include file="layout/footer.jsp"%>
