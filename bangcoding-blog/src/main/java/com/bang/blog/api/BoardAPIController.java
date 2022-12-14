package com.bang.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bang.blog.auth.PrincipalDetail;
import com.bang.blog.dto.Board;
import com.bang.blog.dto.ResponseDto;
import com.bang.blog.dto.User;
import com.bang.blog.service.BoardService;

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
	
	
}
