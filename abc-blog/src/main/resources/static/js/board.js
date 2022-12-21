/**
 * 
 */


let index = {

	init: function(){
		$("#btn--save").bind("click", () => {
			this.save();
		});
		
		$("#btn--update").bind("click", () => {
			this.update();
		});
		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		});

	},
	save: function() {
		let data = {
			title: $("#title").val(), 
			content: $("#content").val(),
		};

		$.ajax({
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
	
	
	update: function() {
		let data = {
			title: $("#title").val(), 
			content: $("#content").val(),
		};

		$.ajax({
			type: "Put",
			url: `/api/board/${$("#board-id").attr("data-id")}`,
			data: JSON.stringify(data),
			contentType: "application/json; charset = utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			console.log(textStatus);
			console.log(xhr);
			if (data.httpStatus == "OK") {
				alert("수정 완료 ");
			}
			location.href = "/";
		}).fail(function(error) {
			console.log(error);
			alert("수정 실패 " + error.responseJSON.error)
		});
		
	},
	
	replySave: function() {

		let replyData = {
			boardId: $("#board-id").val(),  //fk (board.pk)
			content: $("#content").val(),

		};


		$.ajax({
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

	},
	
	replyUpdate: function(boardId, replyIds) {
		let replyData = {
			content: $("input[name=content2]").val(),
			replyId: replyIds,
		}
	
		$.ajax({
			type: "put",
			url: `/api/board/${boardId}/reply/${replyIds}`,
			data: JSON.stringify(replyData),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resData) {
			if (resData.httpStatus == "OK") {
				alert("수정 완료 되었습니다. ");
				location.href = `/board/${boardId}`;
			}
		}).fail(function(error) {
			alert("수정 실패하였습니다. ");
		});

	}



}

index.init();