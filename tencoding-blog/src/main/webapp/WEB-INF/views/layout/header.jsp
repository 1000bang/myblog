<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta id="_csrf" name="${_csrf.parameterName}" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<title>TenCoding blog</title>
<link data-default-icon="https://static.cdninstagram.com/rsrc.php/yv/r/BTPhT6yIYfq.ico" rel="shortcut icon" type="image/x-icon" href="https://static.cdninstagram.com/rsrc.php/yv/r/BTPhT6yIYfq.ico">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${empty principal}">
						<li class="nav-item"><a class="nav-link" href="/auth/login_form">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/join_form">회원가입 </a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/board/save_form">글쓰기 </a></li>
						<li class="nav-item"><a class="nav-link" href="/user/update_form">회원정보 </a></li>
						
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> STORY </a>
						<ul class="dropdown-menu">
							<li >
							<a href="/story/home" class="dropdown-item"><span class = "text-dark"> <i class="fas fa-cloud"> </i>show story</span></a>
							</li>
							
							<li >
							<a href="/story/upload" class="dropdown-item"><span class = "text-dark"> <i class="fas fa-cloud"> </i>show upload</span></a>
							
							</li>
						</ul>
					</li>
						<!-- <a class="nav-link dropdown-toggle" href="/story/home" id="navbardrop" data-toggle="dropdown"> STORY </a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/story/upload">UPLOAD</a>
							</div> -->
							
							
							
							
							

						<li class="nav-item"><a class="nav-link" href="/m-logout">로그아웃 </a></li>
					</c:otherwise>

				</c:choose>

			</ul>
		</div>
	</nav>
	