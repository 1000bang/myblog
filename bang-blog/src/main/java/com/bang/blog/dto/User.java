package com.bang.blog.dto;

//import java.sql.Timestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import org.hibernate.annotations.CreationTimestamp;
//
//@Entity
//public class User {
////
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	@Column(length = 30, nullable = false)
//	private String username;
//	@Column(length = 30, nullable = false)	
//	private String password;
//	@Column(length = 30, nullable = true)
//	private String email;
//	@CreationTimestamp
//	private Timestamp createDate;
//}


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

// 1. mysql server 실행
// 2. 테이블 생성 
// 3. 제약 추가 
@Entity
@Data
@ToString
public class User {
	
	@Id // Primary key PK 로 지정한다.  
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB 넘버링 전략을 따라 간다. 
	private int id; 
	
	@Column(nullable = false, length = 30)
	private String username; 
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email; 
	
//	@ColumnDefault(" 'user' ") // 문자라는 것을 알려 주어야 한다.  --> (' ')
//	private String role; // admin, user, manager
//	
	@CreationTimestamp // 시간이 자동으로 입력 된다. 
	private Timestamp createDate; 
	
}


