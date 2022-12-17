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
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=026e230aaa7d30f98a0e8cad9f217377&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code"> 
			<img alt="카카오 로그인 " src="/image/kakao_login.png" width="78" height="40">
			</a>
			<a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=581731462324-vd9i5jd12s0j4d97jcv6n57l1nf2suik.apps.googleusercontent.com&redirect_uri=http://localhost:9090/auth/google/callback&response_type=code&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email"> 
			<img alt="카카오 로그인 " src="/image/google_login.png" width="78" height="40">
			</a>
			
	</form>

	
</div>


<%@ include file="../layout/footer.jsp"%>