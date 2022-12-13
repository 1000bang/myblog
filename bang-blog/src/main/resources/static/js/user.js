/**
 * 
 */

let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});

	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}

		$.ajax({
			type: "post",
			url: "auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset = utf-8", // 요청시? 보낼 때 데이터 타입
			dataType: "json"
		}).done(function(data, textStatus, xhr) { //positional 데이터 임 순서가 중요함 
			console.log(data);
			if (data.httpStatus == "OK") {
				alert("회원가입완료  ");
				location.href = "/";  //루트 컨텍스트로 가라 
			}
		}).fail(function(error) {
			console.log(error);
			console.log(error.responseJSON.message);
			alert("회원가입실패  " + error.responseJSON.message)

		});

	}


}

index.init();