package com.tencoding.blog.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	//di
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/board")
	public String save(Board board, @AuthenticationPrincipal PrincipalDetail detail) {
	
		//board service 
		//저장, 직접 만들면 됨 
		System.out.println(board);
		boardService.write(board, detail.getUser());
		return "redirect:/";
	}
	
	
	
	//?page = 2
	@GetMapping({"","/", "/board/search"})
	public String index(Model model, @RequestParam(required = false) String q,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		// select * from 
	
		String searchTitle = q == null? "": q;
		Page<Board> boards = boardService.searchBoard(searchTitle, pageable);
		
		//Page<Board> boards = boardService.getBoardList(pageable);
		
		// 1. 현재 페이지 앞뒤로 2칸씩 보이기
		// 2. 현재페이지 active 처리하기
		// 3. 페이지 숫자를 눌렀을 경우 해당 페이지로 화면 이동하기 
		
		// 총 몇페이지가 나오는지 확인 
		System.out.println(">>>>>>화면에 보여줄 게시글 수  : " + boards.getSize());
		System.out.println(">>>>>>전체 페이지 크기 : " + boards.getTotalPages());
		System.out.println(">>>>>>현재 페이지 번호 : " + boards.getPageable().getPageNumber());
		
		int nowPage = boards.getPageable().getPageNumber() + 1;
		
		int startPageNumber = Math.max(nowPage -2, 1);
		int endPageNumber = Math.min(nowPage + 2, boards.getTotalPages());
	
		System.out.println(">>>>>>시작해야하는 번호 : " + startPageNumber);
		System.out.println(">>>>>>마지막  번호 : " + endPageNumber);

		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for (int i = startPageNumber; i < endPageNumber + 1; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("boards", boards);  // -> jsp파일에서 model 추상화객체를 이용해 컨트롤러에서 내려준 데이터 접근할 수 있다  
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPageNumber);
		model.addAttribute("endPage", endPageNumber);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("q", searchTitle);
	
		
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
