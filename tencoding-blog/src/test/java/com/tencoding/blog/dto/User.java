package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// mysql server 실행  
@Entity
public class User {
	@Id //primary key 선언 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB 넘버링 전략을 따라 간
	private int id;
	private String username;
	private String password;
	private String email;
	private Timestamp createDate;
	
}
