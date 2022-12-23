<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
 .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>
<title>abc blog</title>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body class= "d-flex flex-column min-vh-100">

    <nav class="navbar navbar-expand-sm bg-success navbar-dark fixed-top">
        
		<a class="navbar-brand" href="/">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
        <div class="collapse navbar-collapse  bg-success navbar-dark" id="collapsibleNavbar">
			
			<ul class="navbar-nav">
              	<c:choose>
				<c:when test = "${empty principal}">
                    <li class="nav-item"><a class="nav-link" href="/auth/login_form">로그인</a></li>
                    <li class="nav-item"><a class="nav-link" href="/auth/join_form">회원가입 </a></li>
             </c:when>
			<c:otherwise>
                    
                     <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          블로그  
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="/index">블로그 글 보기 </a>
                          <a class="dropdown-item"  href="/board/save_form"> 블로그 글 등록 </a></a>
                        </div>
                      </li>		
                    
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          이모티콘 
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="/item/save">이모티콘 등록 </a>
                          <a class="dropdown-item"  href="/item/home"> 이모티콘 보기  </a></a>
                        </div>
                      </li>		
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          설정
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="/user/update_form">회원정보</a>
                          <a class="dropdown-item"  href="/logout">로그아웃  </a></a>
                        </div>
                      </li>		
                  </c:otherwise>
			</c:choose>
                
			</ul>
			
		</div>
	</nav>

	<br>
	<br>