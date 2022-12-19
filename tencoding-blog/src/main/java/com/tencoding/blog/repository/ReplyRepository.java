package com.tencoding.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.User;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{


}
