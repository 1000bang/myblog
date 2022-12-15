package com.bang.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user/login-form")
	public String user() {
		
		return "user/login_form";
	}
	
	@GetMapping("/user/join-form")
	public String join() {
		
		return "user/join_form";
	}
	
	
}
