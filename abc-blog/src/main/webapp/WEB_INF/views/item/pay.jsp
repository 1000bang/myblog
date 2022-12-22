<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">
	<form action="">
		<div class="form-group">
			<label for="item_name">상품명:</label> <input type="text" class="form-control" id="item_name" value="기본 블로그 이모티콘" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="usernmae">username:</label> <input type="text" class="form-control" id="username" value="${principal.username}" readonly="readonly">
		</div>
		<div class="form-group">
			<div class="d-flex mb-1">
				<input type="text" class="form-control mr-1" id="sample4_postcode" placeholder="우편번호"> <input type="button" class="btn btn-success mr-1"
					onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br> <input type="button" onclick="sample5()" class="btn btn-success mr-1" value="지도로 검색"><br>
			</div>
			<div class="container">
				<div id="map" style="width: 700px; height: 300px; margin-top: 10px; margin-bottom: 10px; display: none"></div>

			</div>
			<!-- <input type="button" class="btn btn-success mb-1" onclick="sample5()" value="map"><br> -->

			<input type="text" class="form-control mb-1" id="sample4_roadAddress" placeholder="도로명주소"> <input type="hidden" class="form-control mb-1"
				id="sample4_jibunAddress" placeholder="지번주소"> <span id="guide" style="color: #999; display: none"></span> <input type="text"
				class="form-control mb-1" id="sample4_extraAddress" placeholder="참고항목"> <input type="text" class="form-control mb-1"
				id="sample4_detailAddress" placeholder="상세주소">
		</div>
		<div class="form-group">
			<select class="custom-select mb-1 form-control" id="count">
				<option selected>수량</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
		</div>
		<div class="form-group">
			<label for="total_amount">가격:</label> <input type="text" class="form-control" id="total_amount" value="4500">
		</div>
	</form>
	<button type="submit" id="btn--pay" class="btn btn-success">결제하기</button>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8c530edd51a40103966e7768c176ca6c&libraries=services"></script>

<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
		level : 5
	// 지도의 확대 레벨
	};

	//지도를 미리 생성
	var map = new daum.maps.Map(mapContainer, mapOption);
	//주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();
	//마커를 미리 생성
	/* var marker = new daum.maps.Marker({
		position : new daum.maps.LatLng(37.537187, 127.005476),
		map : map
	}); */

	var marker = new kakao.maps.Marker({
		position : new daum.maps.LatLng(37.537187, 127.005476),
		map : map
	}), // 클릭한 위치를 표시할 마커입니다
	infowindow = new kakao.maps.InfoWindow({
		zindex : 1
	}); //

	searchAddrFromCoords(map.getCenter());

	kakao.maps.event
			.addListener(
					map,
					'click',
					function(mouseEvent) {
						searchDetailAddrFromCoords(
								mouseEvent.latLng,
								function(result, status) {
									if (status === kakao.maps.services.Status.OK) {
										console.log(result);
										var detailAddr = !!result[0].road_address ? '<div>도로명주소 : '
												+ result[0].road_address.address_name
												+ '</div>'
												: '';
										detailAddr += '<div>지번 주소 : '
												+ result[0].address.address_name
												+ '</div>';
										detailAddr += !!result[0].road_address ? '<div>건물명  : '
											+ result[0].road_address.building_name
											+ '</div>' 
											: '';
										var content = '<div class="bAddr">'
												+ detailAddr + '</div>';

										var roadAddr = !!result[0].road_address ? result[0].road_address.address_name
												: '';
										var zipCode = !!result[0].road_address ? result[0].road_address.zone_no
												: '';
										extraAddr = result[0].address.address_name;

										marker.setPosition(mouseEvent.latLng);
										marker.setMap(map);

										// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
										//infowindow.setContent(content);
										// 주소 정보를 해당 필드에 넣는다.
										document
												.getElementById("sample4_roadAddress").value = roadAddr;
										document
												.getElementById("sample4_postcode").value = zipCode;
										document
												.getElementById("sample4_extraAddress").value = extraAddr;

										 infowindow.setContent(content);
										infowindow.open(map, marker);
									}
								});
					});

	// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'idle', function() {
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	});

	function searchAddrFromCoords(coords, callback) {
		// 좌표로 행정동 주소 정보를 요청합니다
		geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
	}

	function searchDetailAddrFromCoords(coords, callback) {
		// 좌표로 법정동 상세 주소 정보를 요청합니다
		geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}

	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			var infoDiv = document.getElementById('centerAddr');

			for (var i = 0; i < result.length; i++) {
				// 행정동의 region_type 값은 'H' 이므로
				if (result[i].region_type === 'H') {
					//infoDiv.innerHTML = result[i].address_name;
					break;
				}
			}
		}
	}

	//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	function sample5() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = data.address; // 최종 주소 변수

				console.log(addr);
				console.log(data);

				// 주소 정보를 해당 필드에 넣는다.
				document.getElementById("sample4_roadAddress").value = addr;
				// 주소로 상세 정보를 검색
				geocoder.addressSearch(data.address, function(results, status) {
					// 정상적으로 검색이 완료됐으면
					if (status === daum.maps.services.Status.OK) {
						console.log(results);
						var result = results[0]; //첫번째 결과의 값을 활용

						// 해당 주소에 대한 좌표를 받아서 (마커에 입력할 데이터 )
						var coords = new daum.maps.LatLng(result.y, result.x);
						// 지도를 보여준다.
						mapContainer.style.display = "block";
						map.relayout();
						// 지도 중심을 변경한다.
						map.setCenter(coords);
						// 마커를 결과값으로 받은 위치로 옮긴다.
						marker.setPosition(coords)
					}
				});
			}
		}).open();
	}

	//window.open("/item/map", 'pop01', 'top=10, left=10, width=800, height=800');

	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						console.log(data);

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}
</script>
<script type="text/javascript" src="/js/pay.js"></script>

<%@ include file="../layout/footer.jsp"%>