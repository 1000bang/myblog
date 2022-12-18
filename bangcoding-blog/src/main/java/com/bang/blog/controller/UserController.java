package com.bang.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/auth/login_form")
	public String user() {
		
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String join() {
		
		return "user/join_form";
	}
	
	
}
