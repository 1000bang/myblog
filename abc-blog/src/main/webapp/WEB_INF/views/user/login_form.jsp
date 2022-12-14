<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br><br>
<div class="container">
	<form action="/auth/loginProc" method="Post">
		<div class="form-group">
			<label for="usernmae">username :</label>
			<input type="text" class="form-control" placeholder="Enter usernmae" id="username" name="username"  value="홍길동">
			
		</div>
		<div class="form-group">
			<label for="password">password :</label>
			 <input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="asd123">
		</div>
		
		 <input type="hidden" name="${error}" content="${exception}"></input>
		<c:choose>
		<c:when test = "${empty error}">
		</c:when>
		<c:otherwise>
		<span>
            <p id="valid" class="alert alert-danger">"${exception}"</p>
            </span>
		</c:otherwise>		
		 </c:choose> 
		
		
		<button type="submit"  id="btn--login" class="btn btn-success">Log-in</button>
		
		<a
			href="https://kauth.kakao.com/oauth/authorize?client_id=026e230aaa7d30f98a0e8cad9f217377&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code">
			<img alt="카카오 로그인 " src="/image/kakao_login.png" width="78" height="40">
		</a> 
		<%
		String clientId = "IdFsFqY3HF0S1iEY73kF";//애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:9090/auth/naver/callback", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		session.setAttribute("state", state);
		%>
		<a
			href="<%=apiURL%>"><img
			height="38" src="http://static.nid.naver.com/oauth/small_g_in.PNG" /></a>
			<a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=581731462324-vd9i5jd12s0j4d97jcv6n57l1nf2suik.apps.googleusercontent.com&redirect_uri=http://localhost:9090/auth/google/callback&response_type=code&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email">
			
			<img alt="구글 로그인 " src="/image/google.png" >
		</a>
	</form>
</div>
<!-- <script type="text/javascript" src="/js/user.js"></script> -->
<%@ include file="../layout/footer.jsp"%>