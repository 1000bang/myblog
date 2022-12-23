<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br>
<div class="container">
<br>
<br>


	<%-- <form action="/story/image/upload" method="post" enctype="multipart/form-data" class="m-3">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
		<div class="custom-file">
			<input type="file" name="file" onchange="preview()" accept=".jpg, .png" class="custom-file-input" id="customFile" required="required"> 
			<label class="custom-file-label" for="customFile">썸네일 이미지 선택해주세요 </label>
			
			<div class="container">
				<!-- <div id="profileImage" style="width: 700px; height: 300px; margin-top: 10px; margin-bottom: 10px; display: none"></div> -->
 				<img id="profileImage" onclick="popImage(this.src)">
			</div>
			
			 <input type="file" id="image" accept="image/*" onchange="setThumbnail(event);"/>
    <div id="image_container"></div>
			
			
			<div class="input-group mt-3" >
				<div class="input-group-prepend">
					<span class="input-group-text"> 이모티콘 이름 </span>
				</div>
				<input type="text" class="form-control" name="storyText">
				
				<div class="input-group-prepend">
					<span class="input-group-text"> 가격  </span>
				</div>
				<input type="text" class="form-control" name="price">
				
				<div class="input-group justify-content-end">
				<button type="submit" class="btn btn-success mt-3"> 업로드 하기 </button>
				</div>
			 </div>
		</div>

	</form>

</div>
<br>
<br>




<script>

/* function readURL(input) {
	 $(".custom-file-input").on("change", function() {
	} */
	 function setThumbnail(event) {
        var reader = new FileReader();

        reader.onload = function(event) {
          var img = document.createElement("img");
          img.setAttribute("src", event.target.result);
          document.querySelector("div#image_container").appendChild(img);
        };

        reader.readAsDataURL(event.target.files[0]);
      }
	
	
	
	let fileTag = document.querySelector("input[name=file]");
	 $(".custom-file-input").on("change", function(inputer) {
		var fileName = $(this).val().split("\\").pop();
		$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
		
		
		let imgTag = document.querySelector("#profileImage");
		
		if(inputer.files) {
			let reader = new FileReader();
			
			reader.onload = function(datas){
				console.log(datas);
				imgTag.src = data.target.result;
				imgTag.width = 250;
				imgTag.height = 250;
			}
			reader.readAsDataURL(fileTag.files[0]);
		}else{
			imgTag.src = "";
		}
		
	 });
 


</script>


<%@ include file="../layout/footer.jsp"%>