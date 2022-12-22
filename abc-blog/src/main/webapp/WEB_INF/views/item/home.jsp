<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br>
<br>

<div class="container-foalt">
	<div class="row">
		<%-- <c:forEach var="num" items="${boardData.content}"> --%>

		<div class="col-md-6">
			<div class="card m-2">
				<div class="card-body">
					<h4>기본 블로그 이모티콘</h4>
					<select id="count" class="custom-select-sm mb-1">
						<option selected>수량</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>

					<p id="basic-price">$4.9</p>
					
					<a href="/item/pay" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="card m-2">
				<div class="card-body">
					<h4>고급 블로그 이모티콘</h4>
					<select name="cars" class="custom-select-sm mb-1">
						<option selected>수량</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
					<p id="Advanced-price">$9.9</p>
					<a href="" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>

		<%-- </c:forEach> --%>
	</div>
</div>


<%@ include file="../layout/footer.jsp"%>