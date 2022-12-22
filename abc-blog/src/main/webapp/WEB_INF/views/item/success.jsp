<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">
	<h1 class="display-3 text-aline-center">결제 성공 </h1>
	<br>
	<form action="/">
		<div class="form-group">
			<label for="item_name">상품명:</label> <input type="text" class="form-control" id="item_name" value="${payment.itemName}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="usernmae">username:</label> <input type="text" class="form-control" id="username" value="${payment.partnerUserId}" readonly="readonly">
		</div>
		<div class="form-group">
		
		<label for="address">주소 :</label>
			  <input type="text"class="form-control mb-1" id="" value = "(${postcode}) ${roadAddress}"readonly="readonly">
			 <input type="text" class="form-control mb-1" id="" value = "${detailAddress}"readonly="readonly">
		
		</div>
		
		<div class="form-group">
		<label for="address"> 수량  :</label>
			 <input type="text"class="form-control mb-1" id="" value="${payment.quantity}">
		</div>
		<div class="form-group">
			<label for="total_amount">가격:</label> <input type="text" class="form-control" id="total_amount" value="${payment.amount.total}원  ">
		</div>
		<div class="form-group">
			<label for="total_amount">결제수단 :</label> <input type="text" class="form-control" id="total_amount" value="${payment.paymentMethodType}">
		</div>
		<div class="form-group">
			<label for="total_amount">상태 :</label> <input type="text" class="form-control" id="total_amount" value="성공 ${payment.approvedAt}">
		</div>
	</form>
	<button type="submit" id="btn--ok" class="btn btn-success">확인 </button>
</div>


<script type="text/javascript" src="/js/pay.js"></script>

<%@ include file="../layout/footer.jsp"%>