<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<c:forEach var="board" items="${boards.content}">
	<div>
		<div class="card m-2">
			<div class="card-body">
				<h4>${board.title}</h4>
				<p>${board.content}</p>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기 </a>
			</div>
		</div>
	</div>
	<br>
</c:forEach>
<ul class="pagination justify-content-center" style="margin:20px 0">
  
  <c:set var = "isDisabled" value = "disabled"> </c:set>
  <c:set var = "isNotDisabled" value = ""> </c:set>
	<li class="page-item ${boards.first ? isDisabled : isNotDisabled}"><a class="page-link" href="?page=${boards.number - 1}" >Previous</a></li>
	<c:forEach var="page" items="${pageList}">
	<c:choose>
	<c:when test="${nowPage eq page}">
		<li class="page-item active"><a class="page-link" href="?page=${page - 1}">${page}</a></li>
	</c:when>
	<c:otherwise>
		<li class="page-item"><a class="page-link" href="?page=${page - 1}">${page}</a></li>
	</c:otherwise>
	
	</c:choose>
	
	</c:forEach>
	<li class="page-item ${boards.last ? isDisabled : isNotDisabled}"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
</ul>

<%@ include file="layout/footer.jsp"%>
