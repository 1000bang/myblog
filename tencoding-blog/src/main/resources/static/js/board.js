/**
 * 
 */

let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--delete").bind("click", () => {
			this.deleteById();
		});

	},

	save: function() {
		let data = {
			title: $("#title").val(),

			content: $("#content").val()



		};

		//ajax 통신 요청  
		// $.ajax({}).done().fail();
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

	deleteById: function() {
		let id = $("#board-id").text();
		
		//ajax통신

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id

		}).done(function(data, textStatus, xhr){
			if(data.httpStatus == "OK"){
				alert("삭제가 완료 되었습니다. ");
				location.href = "/";
			}
		}).fail(function(error){
			alert("글삭제 실패하였습니다. ");
		});

	}



}

index.init();