package com.tencoding.blog.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public void write(Board board, User user) {
		// 가독성을 위해 한번더 지정
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true) //select 만 하는 녀석이라는걸 명시  
	public Page<Board>  getBoardList(Pageable pageable) {
		Page<Board> page =  boardRepository.findAll(pageable);
		System.out.println(page.getSize());
		
		return boardRepository.findAll(pageable);
	}

	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void deletebyId(int id) {
		boardRepository.deleteById(id);
		
	}
	
	
}
