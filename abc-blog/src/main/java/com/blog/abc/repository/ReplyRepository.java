package com.blog.abc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.abc.dto.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Integer>{


}
