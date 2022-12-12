package com.bang.blog.controller;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@PutMapping("/user/{id}")
	public User udateUser(@PathVariable int id, @RequestBody User reqUser) {

		log.info(">>> id : {}, >>> password >>> {}, email >>> {}", 
				id, reqUser.getPassword(), reqUser.getEmail());
		
		
		User user =  userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패 하였습니다.");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		userRepository.save(user);
		
		return null; 
	}
	
	@Transactional
	@PutMapping("/user2/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {

		log.info(">>>id : {}, >>> password : {}, >>> email :{}", 
				id, reqUser.getPassword(), reqUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("잘못된 요청입니다. 수정에 실패하였습니다.");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		return user;
	}
	
	
	@GetMapping("/users2")
	public Page<User> pageList(@PageableDefault(size = 5, 
			sort = "id", direction = Direction.DESC) Pageable  pageable) {
		
		Page<User> userPage = userRepository.findAll(pageable);
		
		
		List<User> users = userRepository.findAll(pageable).getContent();
		return userPage;
	}
	
	@GetMapping("dummy/users2")
	public Page<User> pageList1 (@PageableDefault(size = 3,
		sort = "id", direction = Direction.ASC)Pageable pageable){
		
		Page<User> userPage = userRepository.findAll(pageable);
		List<User> users = userRepository.findAll(pageable).getContent();
		System.out.println(users);
		return userPage;
		
	}
	
	
	@GetMapping("/dummy/user1/{id}")
	public Optional<User> detail(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	

	
	@GetMapping("/dummy/user2/{id}")
	public User detailUser(@PathVariable int id) {
		User user = userRepository.findById(id).orElseGet(()->{
			//없으면 새로 만들어라?
			return new User();
		});
		return user;
	}
	
	@GetMapping("/dummy/user3/{id}")
	public User detailUser2(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(()->{
			
			return new IllegalArgumentException("해당 유저는 없습니다. : " + id); 
		});
		return user;
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
