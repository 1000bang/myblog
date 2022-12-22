package com.blog.abc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.abc.auth.PrincipalDetail;
import com.blog.abc.dto.Board;
import com.blog.abc.dto.Reply;
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
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId,  
			@RequestBody Reply reqReply, @AuthenticationPrincipal PrincipalDetail detail) {
		
		boardService.writeReply(boardId, reqReply, detail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int boardId,  
			@PathVariable int replyId, @AuthenticationPrincipal PrincipalDetail detail) {
		//확인 절차 ( 현재 삭제 요청자, db저장된 사용자에 id 역시 비교해서 처리를 해줘야함)
		try {
			boardService.deleteReplyById(replyId, detail.getUser().getId());
		} catch (Exception e) {
		}
		
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@PutMapping("/api/board/{boardId}/reply/{replyIds}")
	public ResponseDto<Integer> replyUpdate(@PathVariable int boardId,  
			@PathVariable int replyIds, @RequestBody Reply content, @AuthenticationPrincipal PrincipalDetail detail) {
		System.out.println(">>>>>>>>" +replyIds +">>>>>>>>" + content.getContent());
		boardService.updateReplyById(replyIds, boardId, content);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	
}
