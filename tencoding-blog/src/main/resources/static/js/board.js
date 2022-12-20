/**
 * 
 */

// csrf token & header 
let token = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--delete").bind("click", () => {
			this.deleteById();
		});
		$("#btn--update").bind("click", () => {
			this.update();
		});
		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		});


	},

	save: function() {
		let xcheckTitle = XSSCheck($("#title").val());

		let data = {
			title: xcheckTitle,

			content: $("#content").val()



		};

		//ajax 통신 요청  
		// $.ajax({}).done().fail();
		$.ajax({
			beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeader, token);
			},
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset = utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			console.log(textStatus);
			console.log(xhr);
			if (data.httpStatus == "OK") {
				alert("글쓰기 완료 ");
			}
			location.href = "/";
		}).fail(function(error) {
			console.log(error);
			alert("글쓰기 실패  " + error.responseJSON.error)
		});
	},

	deleteById: function() {
		let id = $("#board-id").val();

		//ajax통신

		$.ajax({
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "DELETE",
			url: "/api/board/" + id

		}).done(function(data, textStatus, xhr) {
			if (data.httpStatus == "OK") {
				alert("삭제가 완료 되었습니다. ");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글삭제 실패하였습니다. ");
		});

	},

	update: function() {

		//html 태그에 직접 속성을 정의할 수 있다. 규칙은 data-* 이다
		//data-* 값을 가지고 오기 위해서 jQuery 문법 -> 선택자.attr("data-[*]")
		let boardId = $("#board-id").attr("data-id");

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};


		$.ajax({
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(data, textStatus, xhr) {
			if (data.httpStatus == "OK") {
				alert("글 수정이 완료되었습니다. ");
				location.href = "/";
			}

		}).fail(function(error) {
			alert("글 수정에 실패하였습니다.");
		});

	},

	replySave: function() {

		let replyData = {
			boardId: $("#board-id").val(),  //fk (board.pk)
			content: $("#content").val(),

		};


		$.ajax({
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "POST",
			url: `/api/board/${replyData.boardId}/reply`,
			data: JSON.stringify(replyData),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(data, textStatus, xhr) {
			if (data.httpStatus == "OK") {
				alert("댓글작성이 완료되었습니다. ");
				location.href = `/board/${replyData.boardId}`;
			}

		}).fail(function(error) {
			alert("댓글 작성에 실패하였습니다.");
		});

	},

	replyDelete: function(boardId, replyId) {

		$.ajax({
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" // 적어도되고 안적어도 되고 
		}).done(function(resData) {
			if (resData.httpStatus == "OK") {
				alert("삭제가 완료 되었습니다. ");
				location.href = `/board/${boardId}`;
			}
		}).fail(function(error) {
			alert("글삭제 실패하였습니다. ");
		});

	}





}

function XSSCheck(str, level) {
	if (level == undefined || level == 0) {
		str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g, "");
	} else if (level != undefined && level == 1) {
		str = str.replace(/\</g, "&lt;");
		str = str.replace(/\>/g, "&gt;");
	}
	return str;
}



index.init();