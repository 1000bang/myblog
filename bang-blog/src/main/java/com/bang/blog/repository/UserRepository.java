package com.bang.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bang.blog.dto.User;



//	@Repository  선언이 불필요하다는 의미  
public interface UserRepository extends JpaRepository<User, Integer>{

}

