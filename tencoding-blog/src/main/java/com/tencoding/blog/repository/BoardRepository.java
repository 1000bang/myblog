package com.tencoding.blog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

//	@Query(value = " select * "
//			+ " from board "
//			+ " where  title like '%?%' "
//			, nativeQuery = true)  //?1 첫번째꺼 넣어라 
//	Page<Board> search(String q, Pageable pageable );
	
	Page<Board> findByTitleContaining(String q, Pageable pageable);
	
}
