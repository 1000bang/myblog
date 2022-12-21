<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<br>
<div class="container">
	<form action="/api/board" method="post" enctype="application/json; charset = utf-8">
		<div class="form-group">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}">
			<label for="title">Title</label> <input type="text" name="title" id="title" class="form-control">

		</div>
		<div>
			<label for="content">content</label>
			<textarea name="content" id="content" rows="5" class="form-control content">
	</textarea>
		</div>

	<button type="submit" id="" class="btn btn-primary">글쓰기 완료</button>
	</form>


</div>

<script>
	$('.content').summernote({
		placeholder : '내용을 입력하세요 ',
		tabsize : 2,
		height : 300
	});
</script>

<<script type="text/javascript" src="/js/board.js">

</script>
<%@ include file="../layout/footer.jsp"%>