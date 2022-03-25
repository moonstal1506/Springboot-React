package com.dadok.book.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * 통합테스트:모든 bean들을 똑같이 ioc올리고 테스트 하는 것 느림
 * WebEnvironment.MOCK 실제 톰켓아니라 다른톰켓으로 테스트
 * WebEnvironment.RANDOM_PORT 실제 톰켓으로 테스트
 * @AutoConfigureMockMvc MockMvc를 Ioc에 등록
 * @Transactional 각 테스트가 종료될 때마다 트랜잭션 롤백
 */

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) 
public class BookControllerIntegereTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void test1() {
		
	}
	
	@Test
	public void test2() {
		
	}
}
