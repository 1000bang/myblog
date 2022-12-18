package com.bang.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bang.blog.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
}
