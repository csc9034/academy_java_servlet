package book.exception;

import book.vo.Book;

public class DuplicateException extends Exception{
	// 기본 생성자 선언
	public DuplicateException() {
		super("도서 정보가 중복 되었습니다");
	}

	public DuplicateException(String type, Book book) {
		super(String.format("%s : [%s, %s] 가 중복 되었습니다.", type, book.getBookId(), book.getTitle()));
	}
	

}
