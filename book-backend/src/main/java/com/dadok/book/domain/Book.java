package com.dadok.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity//서버실행시 object Relation mapping됨 테이블생성
public class Book {
	@Id //pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)//해당 데이터베이스 번호증가 전략을 따라감
	private Long id;
	
	private String title;
	private String author;
	
//	public static setBook(Dto dto) {
//		title=dto.getTitle();
//		author=dto.getAuthor();
//	}
}
