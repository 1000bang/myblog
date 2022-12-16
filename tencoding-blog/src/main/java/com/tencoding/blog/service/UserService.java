package com.tencoding.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional
	public int saveUser(User user) {
		
		try {
			//비밀번호를 넣을 때 여기서 암호화 처리하고 DB에 저장하기 ! 
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
	
			return 1;
		} catch (Exception e) {
			//회원가입 오류는 여기서 다 잡아줌 
			e.printStackTrace();
			return -1;
		}
	}


	@Transactional
	public void updateUser(User reqUser) {
		
		//내 정보가 있는가 확
		User userEntity = userRepository.findById(reqUser.getId())
				 .orElseThrow(()->{
			return new IllegalArgumentException("해당 유저를 찾을 수 없습니다. ");
			
		});
		
		// 우리 사이트 회원가입자 (소셜로그인이 x)
		if(userEntity.getOauth() == null || userEntity.getOauth().equals("")) {
			//여기로 내려온다는건 성공했다는 뜻이니까 
			// 암호화 처리 하고 넣어주기 
			String rawPassword = reqUser.getPassword();
			String encPassword = encoder.encode(rawPassword);
			userEntity.setUsername(reqUser.getUsername());
			userEntity.setPassword(encPassword);
			userEntity.setEmail(reqUser.getEmail());
			//더티체킹 업데이트  
		}
		
		
	}

	@Transactional
	//데이터가 있나없나 확
	public User searchUserNam(String username) {
		
		return userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
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
