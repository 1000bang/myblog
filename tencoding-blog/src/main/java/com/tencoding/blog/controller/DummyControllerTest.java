package com.tencoding.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class DummyControllerTest {

	@Autowired //순환참조 조심
	private UserRepository userRepository;
	
	@GetMapping("/dummy/user/{id}")
	public Optional<User>  detail(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
//	@GetMapping("/dummy/user/{id}")
//	public User  detail(@PathVariable int id) {
//		// 
////		User user = userRepository.findById(id).orElseGet(()->{
////			// 없으면 커스텀한 데이터를 넣을 수 있다. 
////			return new User();
////		});
//		
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. : " + id); 
//		});
//		
//		return user;
//	}
	
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/dummy/signup")
	public String SignUp(@RequestBody User user) {

		log.info(">>>User : {} ", user);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		// 로직 수행...
		return "회원가입이 완료되었습니다";
	}
	
	
	
}
