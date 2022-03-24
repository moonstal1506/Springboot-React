package com.dadok.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;


//@Repository적어야 스프링이 ioc에 빈으로 등록하는데
//JpaRepository를 extends하면 생략가능
//JpaRepository crud함수들고있음
public interface BookRepository extends JpaRepository<Book, Long>{

}
