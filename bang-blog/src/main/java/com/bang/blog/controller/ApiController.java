package com.bang.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class ApiController {

	

	@GetMapping("/html")
	public String tempjsp2() {
		// prefix : main --> /WEB-INF/views/
		// /test
		//subfix : .jsp 
		// /WEB-INF/views/test.jsp
		return "/index.html";
	}
	
	@GetMapping("/test")
	public String tempjsp() {
		// prefix : main --> /WEB-INF/views/
		// /test
		//subfix : .jsp 
		// /WEB-INF/views/test.jsp
		return "test";
	}
	
}
