package com.dadok.book.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

//단위 테스트 DB에 관련된 Bean이 ioc에 등록되면 됨
@Transactional
@AutoConfigureTestDatabase(replace=Replace.ANY)//Replace.ANY가짜디비로 테스트/Replace.NONE 실제 DB로 테스트
@DataJpaTest//레포지토리 ioc등록해줌
public class BookRepositoryUnitTest {
	
	@Autowired
	private BookRepository bookRepository;
}
