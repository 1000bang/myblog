package com.demo.client1.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.client1.dto.UserResponse;

@Service
public class RestTemplateService {

	//여기서 hello method 만들어서 다르서버에 접근해서 결과를 받아오기 
	public UserResponse 반가() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080")
				.path("/api/server/hello2")
				.encode()
				.queryParam("name", "홍길동")
				.queryParam("age", "10")
				.build()
				.toUri();
		System.out.println("주소확인 " + uri.toString());
		RestTemplate restTemplate = new RestTemplate();
		//String result = restTemplate.getForObject(uri, String.class);
		ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
		System.out.println(result.getStatusCode());

		return result.getBody();
	}
	

	
}
