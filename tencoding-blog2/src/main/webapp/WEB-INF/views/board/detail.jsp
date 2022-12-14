
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>


<div class="container">
		
	<button class="btn bg-secondary" onclick="history.back();" >돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
	<a class="btn btn-warning" id="" href="/board/${board.id}/update_form">수정</a>
	<button class="btn btn-danger" id="btn--delete">삭제</button>
	</c:if>
	<br/><br/><br/>
		
	<div>글 번호 :  <span id="board-id"> <i> ${board.id} </i></span> </div>
	<div>글 작성자 :  <span id=""> <i> ${board.user.username} </i></span> </div>
	<br/><br/><br/>
	<div class="">
		<h3>${board.title}</h3>
	</div>
	<br/><br/>
	<div>
		${board.content}
	</div>
	<br/><br/><br/>
</div>
<div class="card">

		<div class="card-body">
			<textarea rows="1" class="form-control" id="content"></textarea>
		</div>
		<div class="card-footer">
			<button class="btn btn-primary" id="btn-reply-save"  style="float: right">add reply</button>
		</div>

		<br>
		<div class="card">
			<div class="card-header">댓글 목록</div>
		</div>

		<ul class="list-group">

			<c:forEach var="reply" items="${board.replys}">
				<li class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div>작성자 :&nbsp; ${reply.user.username} &nbsp;&nbsp;</div>
						<c:if test="${reply.user.id eq principal.user.id}">
							<button class="btn btn-danger" onclick="index.replyDelete(${board.id}, ${reply.id})" style="height: 35">삭제</button>
						</c:if>				
					</div>
				</li>
		
			</c:forEach>
 			
		</ul>


	</div>

<<script type="text/javascript" src="/js/board.js">

</script>
<%@ include file="../layout/footer.jsp" %>