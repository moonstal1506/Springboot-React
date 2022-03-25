package com.dadok.book.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.dadok.book.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 통합테스트:모든 bean들을 똑같이 ioc올리고 테스트 하는 것 느림
 * WebEnvironment.MOCK 실제 톰켓아니라 다른톰켓으로 테스트
 * WebEnvironment.RANDOM_PORT 실제 톰켓으로 테스트
 * @AutoConfigureMockMvc MockMvc를 Ioc에 등록
 * @Transactional 각 테스트가 종료될 때마다 트랜잭션 롤백
 */

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) 
public class BookControllerIntegereTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void save_테스트() throws Exception {
//		log.info("save_테스트");
//		Book book= bookService.저장하기(new Book(null,"제목","작성자"));
//		System.out.println("book:"+book);
		
		//BDDMockito 패턴 given when then
		//given(테스트를 하기 위한 준비)
		Book book = new Book(null,"제목","저자");
		String content = new ObjectMapper().writeValueAsString(book); //오브젝트를 json으로 바꿔줌
//		when(bookService.저장하기(book)).thenReturn(new Book(1L,"제목","저자")); 통합에선 필요없음 실제 서비스 실행
		
		//when(테스트 실행)
		ResultActions resultAction=  mockMvc.perform(post("/book")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then(검증)
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("제목"))//$전체
			.andDo(MockMvcResultHandlers.print());
	}

}
