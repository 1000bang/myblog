package com.blog.abc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.abc.dto.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = " SELECT * "
			+ " FROM user "
			+ " where username = ?1 "
			+ " and password = ?2 ", nativeQuery = true)
	User findbyUsernamePasswords(String username, String password);

	Optional<User> findByUsername(String username);
	

}
