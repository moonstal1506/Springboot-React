package com.dadok.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.dadok.book.domain.Book;
import com.dadok.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
//단위테스트
//@Slf4j
@WebMvcTest // controller, filter,controllerAdvice
public class BookControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean // ioc환경에 bean등록됨
	private BookService bookService;

	@Test
	public void save_테스트() throws Exception {
//		log.info("save_테스트");
//		Book book= bookService.저장하기(new Book(null,"제목","작성자"));
//		System.out.println("book:"+book);

		// BDDMockito 패턴 given when then
		// given(테스트를 하기 위한 준비)
		Book book = new Book(null, "제목", "저자");
		String content = new ObjectMapper().writeValueAsString(book); // 오브젝트를 json으로 바꿔줌
		when(bookService.저장하기(book)).thenReturn(new Book(1L, "제목", "저자"));

		// when(테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content).accept(MediaType.APPLICATION_JSON_UTF8));

		// then(검증)
		resultAction.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("제목"))// $전체
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void findAll_테스트() throws Exception {

		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "제목1", "저자1"));
		books.add(new Book(2L, "제목2", "저자2"));
		when(bookService.모두가져오기()).thenReturn(books);

		// when(테스트 실행)
		ResultActions resultAction = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.[0].title").value("제목1" ))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findById_테스트() throws Exception{
		//given
		Long id=1L;
		when(bookService.한건가져오기(id)).thenReturn(new Book(1L,"제목1","저자1"));
		
		//when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}",id)
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title").value("제목1" ))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception{
		//given
		Long id=1L;
		Book book = new Book(null, "제목3", "저자3");
		String content = new ObjectMapper().writeValueAsString(book); // 오브젝트를 json으로 바꿔줌
		when(bookService.수정하기(id,book)).thenReturn(new Book(1L, "제목3", "저자3"));
		
		//when
		ResultActions resultAction = mockMvc.perform(put("/book/{id}",id).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content).accept(MediaType.APPLICATION_JSON_UTF8));
		
		//then
		resultAction
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title").value("제목3" ))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void delete_테스트() throws Exception{
		//given
		Long id=1L;

		when(bookService.삭제하기(id)).thenReturn("ok");
		
		//when
		ResultActions resultAction = mockMvc.perform(delete("/book/{id}",id)
				.accept(MediaType.TEXT_PLAIN));
		
		//then
		resultAction
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		MvcResult requestResult =resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		
		assertEquals("ok", result);
	}
	
	
}
