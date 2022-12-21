package com.tencoding.blog.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.FileDto;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.repository.ImageRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class StorySerivce {

	
	private final ImageRepository imageRepository;

	@Value("${file.path}")
	private String uploadFolder;
	
//	이 생성자와 @requiredArgsConstructor 와 같
//	public StorySerivce(ImageRepository imageRepository) {
//		this.imageRepository = imageRepository;
//	}
	
	public void imageUpload(FileDto fileDto, PrincipalDetail principalDetail) {
	
		//UUID 사용하기
		UUID uuid = UUID.randomUUID();
		
		//우리 서버 컴퓨터에 파일을 저장 시 이름 만들기 
		String imageFileName = uuid+"_"+fileDto.getFile().getOriginalFilename();
		System.out.println("imageFileName : "+imageFileName);
		
		//실제로 이미지를 저장할 경로를 가지고 와야한다. 
		// 하드코딩 할 수 있지만 초기 파라미터 방식으로 만들어보자  
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		System.out.println("imageFilePath : " + imageFilePath);
	
		try {
			Files.write(imageFilePath, fileDto.getFile().getBytes());
			
			//DB 저장 로직 추가 
			//image 생성 
			Image image = fileDto.toEntity(imageFileName, principalDetail.getUser());
			imageRepository.save(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Transactional
	public Page<Image> getImageList(Pageable pageable) {
		
		return imageRepository.findAll(pageable);
	}

	
	
}
