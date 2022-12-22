/**
 * 
 */
let index = {

	init: function() {


		$("#btn--pay").bind("click", () => {
			this.pay();
		});

	},

	pay: function() {
		let data = {
			username: $("#username").val(),
			count: $("#count").val(),
			amount : $("#total_amount").val(),
			itemname : $("#item_name").val(),
			postcode :$("#sample4_postcode").val(),
			roadAddress : $("#sample4_roadAddress").val(),
			detailAddress : $("#sample4_detailAddress").val(),
		
		
		};
		
		$.ajax({
			type: "post",
			url: "/item/ready",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(status) {
			alert(" 잠시만 기다려주세요 ")
			if(status.httpStatus == "OK"){
			
			
			location.href = status.body.next_redirect_pc_url;
			//window.open(status.body.next_redirect_pc_url, 'pop01', 'top=10, left=10, width=500, height=600');
			
			}
			
			self.close();
			
			
			
		}).fail(function(error) {
			alert("실패" + error.responseJSON.message);
		});



	}

}

	index.init();