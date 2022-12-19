package com.tencoding.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

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

	@Transactional(readOnly = true) // select 만 하는 녀석이라는걸 명시
	public Page<Board> getBoardList(Pageable pageable) {
		Page<Board> page = boardRepository.findAll(pageable);
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

	@Transactional
	public int modifyBoard(int boardId, Board board) {
		// 영속화 되었다.
		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없어요.");
		});

		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		// 해당 함수 종료 시점에 트랜직션이 종료되고 더티체킹해서 자동 커밋
		return 1;
	}

	@Autowired
	private ReplyRepository replyRepository;
	@Transactional
	public void writeReply(int boardId, Reply reqReply, User user) {
		
		//영속화 되었다 ...? ..!
		Board board = boardRepository.findById(boardId).orElseThrow(() ->{
				return new IllegalArgumentException("댓글 쓰기 실패 게시글이 존재하지 않습니다.");
		});
		
		reqReply.setUser(user);
		reqReply.setBoard(board);
		replyRepository.save(reqReply);
		
	}
	@Transactional
	public void deleteReplyById(int replyId, int requestId) {
		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(()->{
			return new IllegalArgumentException("해당글을 찾을 수 없.");
		});
		
		try {
			int dbWrite = replyEntity.getUser().getId();
			if(dbWrite == requestId) {
				replyRepository.deleteById(replyId);
			}else {
				throw new IllegalArgumentException("해당글을 찾을 수 없.");
			}
		} catch (Exception e) {
			
		}
		
		
		
	}
	

	
	

}
