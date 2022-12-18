<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="Post">
		<div class="form-group">
			<label for="usernmae">username :</label>
			<input type="text" class="form-control" placeholder="Enter usernmae" id="username" name="username"  value="홍길동삼">
			
		</div>
		<div class="form-group">
			<label for="password">password :</label>
			 <input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="asd123">
		</div>		
		<button type="submit"  id="btn--login" class="btn btn-primary">Log-in</button>
	</form>
</div>
<script type="text/javascript" src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>