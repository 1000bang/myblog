<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="">
		<div class="form-group">
			<label for="usernmae">username :</label>
			<input type="text" class="form-control" placeholder="Enter usernmae" id="username" value="홍길동">
			
		</div>
		<div class="form-group">
			<label for="password">password :</label>
			 <input type="password" class="form-control" placeholder="Enter password" id="password" value="asd123">
		</div>
		
	</form>
		<button type="submit"  id="btn--login" class="btn btn-primary">Log-in</button>
</div>
<script type="text/javascript" src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>