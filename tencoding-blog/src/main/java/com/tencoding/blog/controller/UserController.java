package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	HttpSession session;
	
	@GetMapping("/login-form")
	public String loginForm() {
		
		
//		/WEB-INF/views/user/login_form.jsp
		return "/user/login_form";
	}
	
	@GetMapping("/join-form")
	public String joinForm() {

		return "/user/join_form";
	}
	
	//기존 스프링에서 로그아웃처리는 따로 정리 !
	@GetMapping("/logout")
	public String logout() {
		HttpSession httpSession = session;
		httpSession.invalidate(); // 세션이 바로 삭제됨 로그아웃 처
	return "redirect:/";	
	}
	
}
