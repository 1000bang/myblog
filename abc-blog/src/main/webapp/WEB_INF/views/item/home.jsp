<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br>
<br>

<div class="container-foalt">
<div class="container">
	<div class="d-flex justify-content-end m-2">
		<form action="" method="get" class="form-inline">
			<input class="form-control mr-2" type="text" placeholder="검색어를 입력하세요 " name="searchValue">
			<!-- &nbsp;&nbsp; -->
			<button type="submit" class="btn btn-success">검색</button>
		</form>
	</div>
</div>
	<div class="row">
		<%-- <c:forEach var="num" items="${boardData.content}"> --%>

		<div class="col-md-4">
			<div class="card m-2">
				<div class="card-body">
					<input type="hidden" name = "name" value="읏추추 춘식이! 이모티콘">
					<h2>읏추추 춘식이! 이모티콘</h2>
						<img alt="읏추추 춘식이 " src="/image/a1.png" width="70" height="70">

					<p id="basic-price">$4.9</p>
					
					<a href="/item/pay/" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="card m-2">
				<div class="card-body">
					<h4> 얄미운 늬에시 이모티콘</h4>
						<img alt="얄미운 늬에시 " src="/image/a2.png" width="70" height="70">
					
					
					<p id="Advanced-price">$9.9</p>
					<a href="" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>
			<div class="col-md-4">
			<div class="card m-2">
				<div class="card-body">
					<h4>오늘의 짤 #희노애짤 이모티콘</h4>
						<img alt="읏추추 춘식이 " src="/image/a3.png" width="70" height="70">
					
					
					<p id="Advanced-price">$9.9</p>
					<a href="" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>
		
			<div class="col-md-4">
			<div class="card m-2">
				<div class="card-body">
					<h4>누렁이 또왔어요 이모티콘</h4>
						<img alt="읏추추 춘식이 " src="/image/a4.png" width="70" height="70">
					
					
					<p id="Advanced-price">$9.9</p>
					<a href="" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>
		
			<div class="col-md-4">
			<div class="card m-2">
				<div class="card-body">
					<h4>혀 딻은 앙꼬 이모티콘</h4>
						<img alt="읏추추 춘식이 " src="/image/a5.png" width="70" height="70">
					
					
					<p id="Advanced-price">$9.9</p>
					<a href="" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>
		
			<div class="col-md-4">
			<div class="card m-2">
				<div class="card-body">
					<h4>주먹을부르는 바둑이 이모티콘</h4>
						<img alt="읏추추 춘식이 " src="/image/a6.png" width="70" height="70">
					
					
					<p id="Advanced-price">$9.9</p>
					<a href="" class="btn btn-success">구매하기 </a>
				</div>
			</div>
		</div>

		<%-- </c:forEach> --%>
	</div>
</div>


<%@ include file="../layout/footer.jsp"%>