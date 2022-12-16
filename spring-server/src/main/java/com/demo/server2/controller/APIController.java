package com.demo.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	//path("/api/server/hello/{username}/age/{age}")
	@GetMapping("hello/{username}/age/{age}")
	private User serverTest(@PathVariable String userId, @PathVariable String username) {
		User user = new User();
		user.setName(username);
		user.setAge(userId);	
		return  user;
	}
	
	@GetMapping("/hello2")
	public User serverHello(@RequestParam String name, @RequestParam String age) {
		System.out.println("name"+name);
		System.out.println("age"+age);
		
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}
	
}
