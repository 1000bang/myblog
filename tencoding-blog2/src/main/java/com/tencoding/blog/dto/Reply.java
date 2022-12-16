package com.tencoding.blog.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//보더 연관관계 처리 
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board; 
	
	//유저 연관관계 처리 
	@ManyToOne // user하나가 여러개의 reply 를 가질 수 있  
	@JoinColumn(name = "userId")
	private User user;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@CreationTimestamp 
	private Timestamp createDate;
	
}
