package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

	@GetMapping("/temp/home")
	public String tempHome() {
		return "/home.html";
	}

	@GetMapping("/temp/image")
	public String tempImage() {
		return "/정마담.png";
	}
	
	@GetMapping("/temp/test")
	public String tempJsp() {
		//prefix : main --> /WEB-INF/views/
		//subfix : .jsp
		
		//url : /WEB-INF/views/test.jsp
		return "/test";
	}
	
}
