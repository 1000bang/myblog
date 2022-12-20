package com.blog.abc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.abc.dto.ResponseDto;
import com.blog.abc.dto.User;
import com.blog.abc.service.UserService;



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
