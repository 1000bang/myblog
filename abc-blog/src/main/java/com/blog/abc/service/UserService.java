package com.blog.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.abc.dto.User;
import com.blog.abc.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcencoder;

	@Transactional
	public int saveUser(User user) {
		String rawPassword = user.getPassword();
		String bcPassword = bcencoder.encode(rawPassword);
		user.setPassword(bcPassword);
		user.setRole("user");
		userRepository.save(user);
		return 1;
	}

	@Transactional
	public void finByUsernamePassword(User user) {
		userRepository.findbyUsernamePasswords(user.getUsername(), user.getPassword());
		
	}
	@Transactional
	public User searchUserName(String username) {
		
		return userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
	}
	
}
