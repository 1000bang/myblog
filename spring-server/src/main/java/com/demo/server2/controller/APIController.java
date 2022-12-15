package com.demo.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.dto.User;

@RestController
@RequestMapping("/api/server")
public class APIController {

	@GetMapping("/hello")
	private User serverHello() {
		User user = new User();
		user.setName("홍길동");
		user.setAge("10");	
		return  user;
	}
	//path("/api/server/hello/{userId}/name/{username}")
	@GetMapping("/hello/{userId}/name/{username}")
	private User serverTest(@PathVariable String userId, @PathVariable String username) {
		User user = new User();
		user.setName(username);
		user.setAge(userId);	
		return  user;
	}
	
}
