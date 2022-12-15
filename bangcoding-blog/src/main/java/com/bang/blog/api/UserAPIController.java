package com.bang.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bang.blog.dto.ResponseDto;
import com.bang.blog.dto.User;
import com.bang.blog.repository.UserRepository;
import com.bang.blog.service.UserService;



@RestController
public class UserAPIController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	
}
