package com.bang.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bang.blog.dto.Board;
import com.bang.blog.service.BoardService;

//여기는 페이지만 간다

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@GetMapping({"", "/"})
	public String index(Model model) {
		
		List<Board> boards = boardService.getBoardList();
		model.addAttribute("boardData", boards);
		return "index";
	}
	
	@GetMapping("/board/save_form")
	public String save() {
		return "/board/save_form";
	}
	
	@GetMapping("/board/{id}")
	public String showDetail(@PathVariable int id, Model model) {
		model.addAttribute("boardData", boardService.boardDetail(id));
		return "/board/detail";
	}
	
}
