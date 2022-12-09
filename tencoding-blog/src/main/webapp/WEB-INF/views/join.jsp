<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

	<h1>form 테스</h1>
	<form action="/blog/dummy/signup" method="post">
  <div class="form-group">
    <label for="username">UserName:</label>
    <input type="text" class="form-control" placeholder="Enter username" id="username" name="username" value="홍길">
  </div>
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="123">
  </div>
<div class="form-group">
    <label for="email">Email:</label>
    <input type="email" class="form-control" placeholder="Enter email" id="email" name="email" value="sd@gmail.com">
  </div>
  <!--   <button id = "" type="submit" class="btn btn-primary"> 회원가입 </button> -->
  
 
</form>

 <button id = "join--submit" class="btn btn-primary"> 회원가입 </button>
	


<script src="/blog/js/join.js"></script>

</body>
</html>