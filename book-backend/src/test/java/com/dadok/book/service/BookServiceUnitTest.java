package com.dadok.book.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dadok.book.domain.BookRepository;

/**
 * 단위 테스트 service와 관련된 애들만 메모리에 띄우면됨
 * BookRepository=>가짜객체로 만들 수 있음
 * @InjectMocks BookService객체 만들어질때 해당파일에 @Mock로 등록된 모든 애들 주입받는다.
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {
	
	@InjectMocks
	private BookService bookService;
	
	
	@Mock
	private BookRepository bookRepository;
}
