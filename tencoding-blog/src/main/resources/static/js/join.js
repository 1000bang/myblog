/**
 *  버튼을 찾아서 이벤트 바인딩 하기  
 */
 
 $("#join--submit").on('click',function(){
	 let data = {
		 
		 username : $("#username").val(),
		 password : $("#password").val(),
		 email : $("#email").val()
		 
	 };
	 
	 
	 console.log("data : " + data.username);
	 console.log("data : " + data.password);
	 console.log("data : " + data.email);
	 
	 //js도 http 통신 가능 ajax통신 

	 
	 	$.ajax({
		type: 'POST', 
		url : '/blog/dummy/signup',
		data: JSON.stringify(data),   //json 형식으로 변환
		contentType : 'application/json; charset=utf-8', 
		dataType: 'json'
	}).done(function(response) {
		console.log(response);
		alert("회원가입 성공");
	}).fail(function(error) {
		console.log(error);
	}); 
	 
	 
 });