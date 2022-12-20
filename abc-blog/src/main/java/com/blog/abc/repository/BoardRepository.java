package com.blog.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.abc.dto.Board;



public interface BoardRepository extends JpaRepository<Board, Integer>{
}
