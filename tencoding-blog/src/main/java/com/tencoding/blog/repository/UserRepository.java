package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.User;

// 제네릭 첫번째 테이블명, 두번째 pk의 데이터 타입 
//	Dao 역
// 여기서는 굳이 bean으로 등록 요청 하지 않아도 등록을 시켜준다 . -> jparepository 가
//	@Repository  선언이 불필요하다는 의미  
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}
