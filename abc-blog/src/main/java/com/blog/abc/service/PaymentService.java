package com.blog.abc.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.blog.abc.dto.ApproveRes;
import com.blog.abc.dto.PaymentReqDto;
import com.blog.abc.dto.PaymentRes;
import com.blog.abc.dto.ResponseDto;

@Service
public class PaymentService {

	
	
	public ApproveRes payApprove(String tid, String pgToken, PaymentReqDto data) {
		
		RestTemplate rt = new RestTemplate();
		rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		// header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 9d0a458b987ea7c9d2180dd705393b9d");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", tid);
		params.add("partner_order_id", "123123123");
		params.add("partner_user_id", data.getUsername());
		params.add("pg_token", pgToken);
		
		
		System.out.println(tid);
		System.out.println(pgToken);
		 
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//		System.out.println(requestEntity.getBody());
//		System.out.println(requestEntity.getHeaders());
		
        
		String url = "https://kapi.kakao.com/v1/payment/approve";
        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스. 
		ResponseEntity<ApproveRes> response = rt.exchange(url, HttpMethod.POST,
				requestEntity, ApproveRes.class);
		
		
		
		
		
		return response.getBody();
	}

}
