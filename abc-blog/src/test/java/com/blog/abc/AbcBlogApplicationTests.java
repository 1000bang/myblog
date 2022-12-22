package com.blog.abc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.abc.service.BoardService;

@SpringBootTest
class AbcBlogApplicationTests {

	@Test
	void contextLoads() {
		BoardService boardService = new BoardService();
		
		boardService.deleteById(1);
	}

}
