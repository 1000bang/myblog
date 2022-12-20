package com.blog.abc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.abc.auth.PrincipalDetail;
import com.blog.abc.dto.Board;
import com.blog.abc.dto.ResponseDto;
import com.blog.abc.service.BoardService;



@RestController
public class BoardAPIController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,
			@AuthenticationPrincipal PrincipalDetail detail) {
		
		//board service 
		//저장, 직접 만들면 됨 
		System.out.println(board);
		boardService.write(board, detail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	@PutMapping("/api/board/{boardId}")
	public ResponseDto<Integer>update(@PathVariable int boardId, @RequestBody Board board){
		
		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	
}
