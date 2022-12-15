package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.UserService;

@RestController
public class UserAPIController {

	//DI
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserAPIController 호출 user : " + user);
		
		//여기는 1 또는 -1 이 무조건 넘어옴 
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}

	// object mapper 가 매핑 해줌 알아
	@PutMapping("api/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		
		
		//validation처리 예외잡아서 사용자한테 떨궈주면 됨 
		userService.updateUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
//		
//	
//		return new responseUserDto<Integer>(HttpStatus.OK, 1);

}
	
	

	

