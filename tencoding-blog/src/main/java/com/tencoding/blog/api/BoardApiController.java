package com.tencoding.blog.api;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
//	@PostMapping("/api/board")
//	public ResponseDto<Integer> save(Board board, @AuthenticationPrincipal PrincipalDetail detail) {
//	
//		//board service 
//		//저장, 직접 만들면 됨 
//		System.out.println(board);
//		boardService.write(board, detail.getUser());
//		return new ResponseDto<Integer>(HttpStatus.OK, 1);
//	}
//	@PostMapping("/api/board")
//	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) {
//		
//		//board service 
//		//저장, 직접 만들면 됨 
//		System.out.println(board);
//		boardService.write(board, detail.getUser());
//		return new ResponseDto<Integer>(HttpStatus.OK, 1);
//	}
	
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		// 서비스 한테 요청 
		
		boardService.deletebyId(id);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@PutMapping("/api/board/{boardId}")
	public ResponseDto<Integer>update(@PathVariable int boardId, @RequestBody Board board){
		
		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Reply> replySave(@PathVariable int boardId,  
			@RequestBody Reply reqReply, @AuthenticationPrincipal PrincipalDetail detail) {
		
		 Reply replyEntity = boardService.writeReply(boardId, reqReply, detail.getUser());
		
		
		return new ResponseDto<Reply>(HttpStatus.OK, replyEntity);
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
	
}
