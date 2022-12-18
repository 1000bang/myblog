package com.bang.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bang.blog.dto.User;
import com.bang.blog.repository.UserRepository;

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

	public void finByUsernamePassword(User user) {
		userRepository.findbyUsernamePasswords(user.getUsername(), user.getPassword());
		
	}
	
}
