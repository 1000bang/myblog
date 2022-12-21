package com.blog.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.abc.dto.Board;
import com.blog.abc.dto.Reply;
import com.blog.abc.dto.User;
import com.blog.abc.repository.BoardRepository;
import com.blog.abc.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;

	public void write(Board board, User user) {
		// 가독성을 위해 한번더 지정
		board.setCount(0);
		board.setUserId(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pagalbe) {

		return boardRepository.findAll(pagalbe);
	}

	@Transactional
	public Board boardDetail(int id) {

		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});
	}

	@Transactional
	public int modifyBoard(int boardId, Board board) {

		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없어요.");
		});

		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		// 해당 함수 종료 시점에 트랜직션이 종료되고 더티체킹해서 자동 커밋
		return 1;
	}

	public void deleteById(int boardId) {
		boardRepository.deleteById(boardId);

	}

	@Transactional
	public void writeReply(int boardId, Reply reqReply, User user) {

		// 게시글이 아직 존재하는지 확인하기
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 쓰기 실패 게시글이 존재하지 않습니다.");
		});

		reqReply.setUser(user);
		reqReply.setBoard(board);
		replyRepository.save(reqReply);

	}

	public void deleteReplyById(int replyId, int id) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void updateReplyById(int replyId, int id, Reply content) {

		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 댓글을 찾을 수 없습니다. ");
		});

		if (replyEntity.getBoard().getId() == id) {
			replyEntity.setContent(content.getContent());

		}

	}

}
