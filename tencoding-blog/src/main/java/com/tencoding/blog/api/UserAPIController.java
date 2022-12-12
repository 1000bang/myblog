package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.dto.responseUserDto;
import com.tencoding.blog.service.UserService;

@RestController
public class UserAPIController {

	//DI
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public responseUserDto<Integer> save(@RequestBody User user) {
		System.out.println("UserAPIController 호출 user : " + user);
		
		//여기는 1 또는 -1 이 무조건 넘어옴 
		int result = userService.saveUser(user);
		return new responseUserDto<Integer>(HttpStatus.OK, result);
	}

	
}
