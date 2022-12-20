package com.blog.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.abc.dto.Board;
import com.blog.abc.dto.User;
import com.blog.abc.repository.BoardRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public void write(Board board, User user) {
		// 가독성을 위해 한번더 지정
		board.setCount(0);
		board.setUserId(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public List<Board> getBoardList() {
		
		return boardRepository.findAll();
	}

	@Transactional
	public Board boardDetail(int id) {
		
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});
	}

	public int modifyBoard(int boardId, Board board) {
		
		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없어요.");
		});

		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		// 해당 함수 종료 시점에 트랜직션이 종료되고 더티체킹해서 자동 커밋
		return 1;
	}
	
	
	
}
