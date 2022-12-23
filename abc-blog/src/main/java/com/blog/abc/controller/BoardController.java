package com.blog.abc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.abc.dto.Board;
import com.blog.abc.dto.ResponseDto;
import com.blog.abc.service.BoardService;

//여기는 페이지만 간다

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/", "index" })
	public String index(Model model,
			@PageableDefault(size = 6, sort = "id", direction = Direction.DESC) Pageable pageable) {

		// List<Board> boards = boardService.getBoardList();
		Page<Board> boards = boardService.getBoardList(pageable);
		int nowPage = boards.getPageable().getPageNumber() + 1;

		int startPageNumber = Math.max(nowPage - 2, 1);
		int endPageNumber = Math.min(nowPage + 2, boards.getTotalPages());
		
		
		int end = boards.getTotalPages() - 1;
		
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for (int i = startPageNumber; i <= endPageNumber; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", 0);
		model.addAttribute("endPage", end);
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

	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable(name = "id") int boardId, Model model) {

		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/update_form";
	}

	@GetMapping("/board/delete/{id}")
	public String delete(@PathVariable int id, Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		boardService.deleteById(id);
		Page<Board> boards = boardService.getBoardList(pageable);

		int nowPage = boards.getPageable().getPageNumber() + 1;

		int startPageNumber = Math.max(nowPage - 2, 1);
		int endPageNumber = Math.min(nowPage + 2, boards.getTotalPages());

		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for (int i = startPageNumber; i <= endPageNumber; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("startPage", startPageNumber);
		model.addAttribute("endPage", endPageNumber);
		model.addAttribute("boardData", boards);
		return "index";
	}

}
