package com.tencoding.blog.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {

	@Autowired //순환참조 조심
	private UserRepository userRepository;
	
	@GetMapping("/user/{id}")
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
	@PostMapping("/signup")
	public String SignUp(@RequestBody User user) {

		log.info(">>>User : {} ", user);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		// 로직 수행...
		return "회원가입이 완료되었습니다";
	}
	
	// form -> name 키값과 같아야 한다.  
	@PostMapping("/signup2")
	public String SignUp2(String username, String password, String email) {

		User reqUser = new User();
		reqUser.setUsername(username);
		reqUser.setPassword(password);
		reqUser.setEmail(email);
		reqUser.setRole(RoleType.USER);
		userRepository.save(reqUser);
		// 로직 수행...
		return "회원가입이 완료되었습니다";
	}
	
	
	// select all 전체보기
    @GetMapping("/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/users2")
    public List<User> pageList(@PageableDefault(size = 2, 
            sort = "id", direction = Direction.DESC) Pageable pageable){
        System.out.println("11111111111111");
    	Page<User> userPage = userRepository.findAll(pageable);
		
        
        List<User> users = userRepository.findAll(pageable).getContent();
        return users;
    }
	
    //회원수정
    @Transactional //트랜잭션 기본의미, 함수종료시 더티체킹하고 수정된 데이터가 있다면 commit이 된다.    
    @PutMapping("/update/{id}")
	public User udateUser(@PathVariable int id, @RequestBody User reqUser) {

		log.info(">>> id : {}, >>> password >>> {}, email >>> {}", 
				id, reqUser.getPassword(), reqUser.getEmail());
		
		// 사용자 여부를 먼저 확인 (select) 
		// 사용자 있다면 넘겨 받은 데이터를 가공해서 -> 저장 
		// 사용자가 없다면 클라언트에게 잘못된 요청, 없는 사용자 입니다. 
		
		User user =  userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패 하였습니다.");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		//userRepository.save(user);
		
		return user; 
	}
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id) {
    	try {
    		userRepository.deleteById(id);
		} catch (Exception e) {
			return "사용자를 찾을 수 없습니다. ";
		}
    	
    	return "삭제되었습니다" +id;
    }
    
    //스프링 기본 파싱 전략
    //http://localhost:9090/blog/dummy/user/test?name=hong&age=12
    //form 태그로 넘어오는 녀석도 처리 가능하다.
    
    @GetMapping("/user/test")
    public String getTest(String name, int age) {
		
    	System.out.println("name : " + name);
    	System.out.println("age : " + age);
    	return "";
	}
	
}
