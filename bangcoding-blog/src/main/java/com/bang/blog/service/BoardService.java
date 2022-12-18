package com.bang.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bang.blog.dto.Board;
import com.bang.blog.dto.User;
import com.bang.blog.repository.BoardRepository;

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

	public Board boardDetail(int id) {
		
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});
	}
	
	
	
}
