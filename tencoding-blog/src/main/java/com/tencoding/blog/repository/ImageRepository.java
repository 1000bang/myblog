package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
