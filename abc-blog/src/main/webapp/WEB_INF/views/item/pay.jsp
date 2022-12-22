<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">
	<form action="">
		<div class="form-group">
			<label for="item_name">상품명:</label> <input type="text" class="form-control" id="item_name" value="기본 블로그 이모티콘" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="usernmae">username:</label> <input type="text" class="form-control" id="username" value="${principal.username}" readonly="readonly">
		</div>
		
		<div class="form-group">
			<select  class="custom-select mb-1 form-control"id="count"  >
				<option selected>수량</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
		</div>
		<div class="form-group">
			<label for="total_amount">가격:</label> <input type="text" class="form-control" id="total_amount" value="1">
		</div>
	</form>
			<button type="submit" id="btn--pay" class="btn btn-success">결제하기</button>
</div>
<script type="text/javascript" src="/js/pay.js"></script>

<%@ include file="../layout/footer.jsp"%>