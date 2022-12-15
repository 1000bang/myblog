package com.bang.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bang.blog.dto.User;
import com.bang.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	public int saveUser(User user) {
		user.setRole("user");
		userRepository.save(user);
		return 1;
	}

	public void finByUsernamePassword(User user) {
		userRepository.findbyUsernamePasswords(user.getUsername(), user.getPassword());
		
	}
	
}
