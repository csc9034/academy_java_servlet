package book.controller;

import java.util.ArrayList;
import java.util.List;

import book.dao.BookShelf;
import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.view.BookView;
import book.view.ErrorView;
import book.view.MessageView;
import book.view.SingleView;
import book.vo.Book;
import book.view.ListView;

public class BookManager {

	// 1. 멤버 변수 선언
	private BookShelf bookshelf;
	private BookView view;

	// 2. 생성자 선언
	// (1) 기본 생성자
	public BookManager() {

	}

	// (2) 매개변수가 있는 생성자
	public BookManager(BookShelf bookshelf) {
		this.bookshelf = bookshelf;
	}

	// 3. setter getter 선언
	public BookShelf getBookshelf() {
		return bookshelf;
	}

	public void setBookshelf(BookShelf bookshelf) {
		this.bookshelf = bookshelf;
	}

	public BookView getView() {
		return view;
	}

	public void setView(BookView view) {
		this.view = view;
	}

	// 3. 메소드
	public void insert(Book book) {
		String message = null;
		try {
			bookshelf.insert(book);

			message = String.format("책 정보 : [%s] 추가에 성공하였습니다.", book.getBookId());
			view = new MessageView();

		} catch (DuplicateException e) {

			message = String.format("책 정보 : [%s] 추가에 실패하였습니다.", book.getBookId());
			view = new ErrorView();

			e.printStackTrace();

		} catch (Exception e) {
			message = e.getMessage();
			message += String.format("\t 책 정보 : [%s] , 예상치 못한 오류입니다.", book.getBookId());
			view = new ErrorView();
			e.printStackTrace();

		} finally {
			view.display(message);
		}
	}

	public void update(Book book) {
		String message = null;

		try {
			bookshelf.update(book);

			message = String.format("책 정보 : [%s] 수정에 성공 하였습니다.", book.getBookId());
			view = new MessageView();

		} catch (NotFoundException e) {
			message = String.format("책 정보 : [%s] 수정을 실패하였습니다.", book.getBookId());
			view = new ErrorView();

			e.printStackTrace();

		} catch (Exception e) {
			message = e.getMessage();
			message += String.format("\t 책 정보 : [%s] , 예상치 못한 오류입니다.", book.getBookId());
			view = new ErrorView();
			e.printStackTrace();

		} finally {
			view.display(message);
		}
	}

	public void delete(Book book) {
		String message = null;

		try {
			bookshelf.delete(book);

			message = String.format("책 정보 : [%s] 삭제에 성공 하였습니다.", book.getBookId());
			view = new MessageView();

		} catch (NotFoundException e) {
			message = String.format("책 정보 : [%s] 삭제를 실패하였습니다.", book.getBookId());
			view = new ErrorView();

			e.printStackTrace();

		} catch (Exception e) {
			message = e.getMessage();
			message += String.format("\t 책 정보 : [%s] , 예상치 못한 오류입니다.", book.getBookId());
			view = new ErrorView();
			e.printStackTrace();

		} finally {
			view.display(message);
		}
	}

	public void select(Book book) {

		Object obj = null;

		try {
			obj = bookshelf.select(book);

			view = new SingleView();

		} catch (NotFoundException e) {

			obj = String.format("책 정보 : [%s] 조회를 실패하였습니다.", book.getBookId());
			view = new ErrorView();

			e.printStackTrace();

		} catch (Exception e) {

			obj = e.getMessage();
			obj += String.format("\t 책 정보 : [%s] , 예상치 못한 오류입니다.", book.getBookId());
			view = new ErrorView();
			e.printStackTrace();

		} finally {
			view.display(obj);
		}
	}
	
	public void select(int low, int high) {
		
		List<Book> books = null;
		
		try {
			books = bookshelf.select(low, high);
			
			view = new ListView();
			
		} finally {
			view.display(books);
		}
	}
	
	public void select(String keyword) {
		
		List<Book> books = new ArrayList<>();
		
		try {
			books = bookshelf.select(keyword);
			
			view = new ListView();
			
		} finally {
			view.display(books);
		}
	}
	
	
	
	public void selectTotalCnt() {
		
		view = new MessageView();
	
		int cnt = bookshelf.totalCount();

		view.display("현재 책의 개수는 " + cnt + "개 입니다." );
	}

	
	
	public void select() {
		List<Book> books = bookshelf.select();

		view = new ListView();
		view.display(books);
	}
}
