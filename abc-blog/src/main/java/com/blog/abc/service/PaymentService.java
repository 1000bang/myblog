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

@Service
public class PaymentService {

	private String username;
	
	public PaymentRes payReady(PaymentReqDto data) {
		
		username = data.getUsername();
		
		RestTemplate rt = new RestTemplate();
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 9d0a458b987ea7c9d2180dd705393b9d");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", data.getUsername());
		params.add("partner_user_id", data.getUsername());
		params.add("item_name", data.getItemname());
		params.add("quantity", data.getCount());
		params.add("total_amount", data.getAmount());
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:9090/item/pay/success");
		params.add("cancel_url", "http://localhost:9090/item/pay/cancel");
		params.add("fail_url", "http://localhost:9090/item/pay/fail");
		
		HttpEntity<MultiValueMap<String, String>> requestPayment = new HttpEntity<>(params, headers);
		ResponseEntity<PaymentRes> response = rt.exchange("https://kapi.kakao.com/v1/payment/ready", HttpMethod.POST,
				requestPayment, PaymentRes.class);
		
		return response.getBody(); 
	}

	public ApproveRes payApprove(String tid, String pgToken) {
		
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
		params.add("partner_order_id", username);
		params.add("partner_user_id", username);
		params.add("pg_token", pgToken);
		
		
		System.out.println(username);
		System.out.println(tid);
		System.out.println(pgToken);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers, params);
		System.out.println(requestEntity.getBody());
		System.out.println(requestEntity.getHeaders());
		
        
		String url = "https://kapi.kakao.com/v1/payment/approve";
        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스. 
		ApproveRes response = rt.postForObject(url,
				requestEntity, ApproveRes.class);
		
		System.out.println(response);
		
		
		return null;
	}

}
