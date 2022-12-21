<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br><br>
<div class="container">
	<form action="">
			<input type="hidden" name="id" id="id" value = "${principal.user.id}"></input>
		<div class="form-group">
			<label for="username">UserName:</label> <input type="text" class="form-control" id="username" value="${principal.user.username}" readonly="readonly">
		</div>
		
		<c:if test="${empty principal.user.oauth}">
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" id="password" value="">
		</div>
		</c:if>
		
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" id="email" value="${principal.user.email}">
		</div>

	</form>
	<button type="button" id="btn--update" class="btn btn-success">Update</button>
</div>
<script type="text/javascript" src="/js/user.js"></script>



<%@ include file="../layout/footer.jsp"%>