package com.blog.abc.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 1, max = 20, message = "제목은 1자 이상 20자 미만으로 넣어주세요 ")
	@Column(nullable = false, length = 150)
	private String title;
	
	@Lob //대용량 데이터 선언 long text  html등~
	private String content;
	@ColumnDefault("0")  // 이건 숫자(int) 임 홑따옴표로 감싸면 string
	private int count;
	
	//연관관계 만들기
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId") // 컬럼명을 직접 지정 
	private User userId;
	
	// 테이블생성 xx object를 다룰 때 가지고와달라고 명시 (mappedBy)
	//연관관계의 주인이 아님 select 할 때 가지고와야하는 데이터이다 reply 클래스에 해당  변수명 )
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	@JsonIgnoreProperties({"board", "content"}) // Reply 안에 있는 board getter 를 무시해라(호출이 안됨) 
	@OrderBy("id DESC")
	private List<Reply> reply;
	
	@CreationTimestamp 
	private Timestamp createDate;
	
	
}
