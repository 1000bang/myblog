package com.tencoding.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	//di
	@Autowired
	private BoardService boardService;
	
	//?page = 2
	@GetMapping({"","/"})
	public String index(Model model, 
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		final int SHOWBLOCKS = 2;
		Page<Board> boards = boardService.getBoardList(pageable);
		
		int nowPage = boards.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - SHOWBLOCKS , 1 );
		int endPage = Math.min(nowPage + SHOWBLOCKS , boards.getTotalPages());
		
		ArrayList<Integer> pageList = new ArrayList<>();
		for (int i = startPage; i <= endPage; i++) {
			pageList.add(i);
		}
		
		
		model.addAttribute("boards", boards);  // -> jsp파일에서 model 추상화객체를 이용해 컨트롤러에서 내려준 데이터 접근할 수 있다  
		model.addAttribute("pageList", pageList);  // -> jsp파일에서 model 추상화객체를 이용해 컨트롤러에서 내려준 데이터 접근할 수 있다  
		model.addAttribute("nowPage", nowPage);  // -> jsp파일에서 model 추상화객체를 이용해 컨트롤러에서 내려준 데이터 접근할 수 있다  
		
		
		return "index";
	}
	
	
	@GetMapping("/board/save_form")
	public String save() {
		
		
		return "/board/save_form";
	}
	
	
	@GetMapping("/board/{id}")
	public String showDetail(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable(name = "id") int boardId, Model model) {
		
		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/update_form";
	}
	
	
}
