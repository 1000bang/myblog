package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.tencoding.blog.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// mysql server 실행  
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //null필드가 들어올 때 무시해라 디폴트값 적용됨  
public class User {
	@Id //primary key 선언 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB 넘버링 전략을 따라 간
	private int id;
	
	//컬럼에 제약 주기  
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	//dafault 값 지정  ' ' 밖에 "" 문자열임을 알려준다 -> dynamicInsert 필
	@Enumerated(EnumType.STRING) // DB에게 이넘이 string type이라고 알려줌 
	private RoleType role; //admin, user, manager
	
	@CreationTimestamp // 현재시간 자동 생성  
	private Timestamp createDate;
	
}
