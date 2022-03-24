package com.dadok.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dadok.book.domain.Book;
import com.dadok.book.domain.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 기능정의, 트랜잭션 관리
public class BookService {
	// 함수=>송금()->레파지토리에 여러개의 함수 실행->commit or rollback

	private final BookRepository bookRepository;

	@Transactional//서비스함수 종료시 커밋할지 롤백할지
	public Book 저장하기(Book book) {
		return bookRepository.save(book);
	}
	
	//JPA변경감지안함, update시 정합성 유지, insert의 유령데이터 현상(팬텀현상) 못막음
	@Transactional(readOnly=true)
	public Book 한건가져오기(Long id) {// 못찾으면 exception발생
		return bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요"));
	}
	
	@Transactional(readOnly=true)
	public List<Book> 모두가져오기() {
		return bookRepository.findAll();
	}

	@Transactional
	public Book 수정하기(Long id, Book book) {//더티체킹 update
		Book bookEntity=bookRepository.findById(id)//영속화 book오브젝트->영속성 컨텍스트 보관
				.orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요"));
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		
		return bookEntity;
	}//함수종료=> 트랜잭션 종료=>영속화 되어있는 데이터 db갱신 flush->커밋->더티체킹

	public String 삭제하기(Long id) {
		 bookRepository.deleteById(id);
		 return "ok";
	}
}
