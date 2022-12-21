package com.tencoding.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.FileDto;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.service.StorySerivce;

@Controller
@RequestMapping("/story") 
public class StroyController {

 
	
	//http://localhost:9090/story/home
	@GetMapping("/home")
	public String storyHome(Model model, @PageableDefault( sort = "id", direction = Direction.DESC)Pageable pageable ) {
		Page<Image> imagePage =  storySerivce.getImageList(pageable);
		model.addAttribute("imagePage", imagePage);
		
		return "story/home";
	}

	//http://localhost:9090/story/upload
	@GetMapping("/upload")
	public String storyUpload() {
		return "story/upload";
	}
	
	//여기서는 데이터를 전달 받고 처리 해야한다.
	
	
	@Autowired
	private StorySerivce storySerivce;
	
	@PostMapping("image/upload")
	public String storyImageUpload(FileDto fileDto, @AuthenticationPrincipal PrincipalDetail principalDetail,
			Model model, @PageableDefault( sort = "id", direction = Direction.DESC)Pageable pageable ) {
		
		Page<Image> imagePage =  storySerivce.getImageList(pageable);
		model.addAttribute("imagePage", imagePage);
		
		storySerivce.imageUpload(fileDto, principalDetail);
		
		return "story/home";
		
	}
	
	
}
