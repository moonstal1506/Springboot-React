package com.dadok.book.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dadok.book.domain.Book;
import com.dadok.book.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//단위테스트
@Slf4j
@WebMvcTest//controller, filter,controllerAdvice
public class BookControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean //ioc환경에 bean등록됨
	private BookService bookService;
	
	@Test
	public void save_테스트() throws Exception {
//		log.info("save_테스트");
//		Book book= bookService.저장하기(new Book(null,"제목","작성자"));
//		System.out.println("book:"+book);
		
		//BDDMockito 패턴 given when then
		//given(테스트를 하기 위한 준비)
		Book book = new Book(null,"제목","저자");
		String content = new ObjectMapper().writeValueAsString(book); //오브젝트를 json으로 바꿔줌
		when(bookService.저장하기(book)).thenReturn(new Book(1L,"제목","저자"));
		
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
