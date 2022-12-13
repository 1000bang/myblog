package com.bang.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bang.blog.dto.User;
import com.bang.blog.dto.responseUserDto;
import com.bang.blog.service.UserService;



@RestController
public class ApiController {

	//DI
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public responseUserDto<Integer> save(@RequestBody User user) {
		System.out.println("UserAPIController 호출 user : " + user);
		
		
		int result = userService.saveUser(user);
		return new responseUserDto<Integer>(HttpStatus.OK, result);
	}
	
	

	
}