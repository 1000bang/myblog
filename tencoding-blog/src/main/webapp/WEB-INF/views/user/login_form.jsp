<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Email address:</label> <input type="text" class="form-control" placeholder="Enter username" name = "username" id="username" value="teco">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" name = "password" id="password" value="asd123">
		</div>
			<button type="submit" id="btn--login" class="btn btn-primary">Sign-In</button>
	</form>

	
</div>


<%@ include file="../layout/footer.jsp"%>