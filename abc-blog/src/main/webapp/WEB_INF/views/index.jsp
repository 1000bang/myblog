<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<div class="container-fluid">
	<div class="row">

		<c:forEach var="board" items="${boardData}">
			<div class="col-md">

				<div class="card m-2">
					<div class="card-body">
						<h4>${board.title}</h4>
						<p>${board.content}</p>
						<a href="/board/${board.id}" class="btn btn-success">상세보기</a>

					</div>
				</div>
			</div>
		</c:forEach>
	</div>

</div>


<%@ include file="layout/footer.jsp"%>
