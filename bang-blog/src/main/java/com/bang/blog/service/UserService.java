package com.bang.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bang.blog.dto.User;
import com.bang.blog.repository.UserRepository;



// 스프링이 컴포넌트 스캔을 통해서 Bean으로 등록해준다. IoC
@Service
public class UserService {

//	서비스를 만드는 이유 
//  트랜잭션 관리를 위해 
//	

	
	
	@Autowired
	private UserRepository userRepository;
	
// transaction 작업의 단위
//	하나의 기능 + 하나의 기능 들을 묶어서 단위의 기능을 만들어 처리 
//	DB 수정시 roll back  처리 가능
	
	
	@Transactional
	public int saveUser(User user) {
		
		try {
			//비밀번호를 넣을 때 여기서 암호화 처리하고 DB에 저장하기 ! 
			String rawPassword = user.getPassword();
		//	String encPassword = encoder.encode(rawPassword);
			//user.setPassword(encPassword);
			userRepository.save(user);
	
			return 1;
		} catch (Exception e) {
			//회원가입 오류는 여기서 다 잡아줌 
			e.printStackTrace();
			return -1;
		}
	}

//	public User login(User user) {
//		
//		//User userEntity = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
//		System.out.println("userEntity : " + userEntity);
//		
//		
//		return userEntity;
//	}
	
}
