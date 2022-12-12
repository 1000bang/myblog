/**
 * 
 
 */
 
 let index = {
	 
	 init :function(){
		 $("#btn--save").bind("click", () => {
			this.save();	 
		 }); //bind, on 같음
	 },
	 
	save : function(){
		//form 태그에 사용자가 입력한 값을 가지고 오기 --> 자바스크립트 변수로
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);
		//Todo : ajax로 통신 (data -> json 변환 자바 서버로 전송 )
		// ajax 통신구현
	//	$.ajax().done().fail();
		$.ajax({
			//회원가입 요청 
			type : "POST",
			url : "/api/user",
			data : JSON.stringify(data), //http 메세지 body영역에 들어감 
			contentType: "application/json; charset = utf-8", // 요청시? 보낼 때 데이터 타입
			dataType : "json" // 응답이 왔을 때 mime type 지정
		}).done(function(data, textStatus, xhr){ //positional 데이터 임 순서가 중요함 
			console.log(data);  
			if(data.httpStatus == "OK"){
				alert("회원가입완료  ");
				location.href = "/";  //루트 컨텍스트로 가라 
			}
		}).fail(function (error) {
			console.log(error);
			console.log(error.responseJSON.message);
			alert("회원가입실패  " + error.responseJSON.message)
		});
	}
 
 } 
 
 // 이녀석을 실행 시키려면 
 index.init();