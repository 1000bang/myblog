package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tencoding.blog.dto.User;

// 제네릭 첫번째 테이블명, 두번째 pk의 데이터 타입 
//	Dao 역
// 여기서는 굳이 bean으로 등록 요청 하지 않아도 등록을 시켜준다 . -> jparepository 가
//	@Repository  선언이 불필요하다는 의미  
public interface UserRepository extends JpaRepository<User, Integer>{

	//없는 함수는 직접 함수를 만들거나 spring jpa 네이밍 전략을 사용 
	
	//select * from user where  username = ? and password = ?
	User findByUsernameAndPassword(String username, String password);
	
	//두번째 방법 native query 만들기
	@Query(value = " select * "
			+ " from user "
			+ " where  username = ?1 "
			+ " and password = ?2 ", nativeQuery = true)  //?1 첫번째꺼 넣어라 
	User login(String username, String password);
	
}
