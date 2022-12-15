package com.bang.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//여기는 페이지만 간다

@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}
	
}
