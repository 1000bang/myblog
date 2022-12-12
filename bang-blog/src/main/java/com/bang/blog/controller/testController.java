package com.bang.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bang.blog.dto.User;
import com.bang.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class testController {


	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("dummy")
	public String test() {
		return "dummy";
	}
	
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/dummy/signup")
	public String SignUp(@RequestBody User user) {

		log.info(">>>> User : {}", user);
		userRepository.save(user);
		// 로직 수행...
		return "회원가입이 완료되었습니다";
	}
	
}
