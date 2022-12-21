package com.tencoding.blog.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDto {
//  파일을 여러개 받고싶을 때 !
//	private MultipartFile[] file;
	private MultipartFile file;
	private String uuid ; //고유한 파일 이름(파일이름 + createDate)을 만들기 위한 변수
	private String storyText;

	public Image toEntity(String postImageUrl, User user){
		return Image.builder()
				.storyText(storyText)
				.postImageUrl(postImageUrl)
				.originFileName(file.getOriginalFilename())
				.user(user)
				.build();
	}

}

