package book.dao;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class SetBookShelf implements BookShelf {
	// 1. Set<Book> 타입의 books 멤버 변수 선언
	Set<Book> books;

	// 2. 생성자 선언부
	// (1) 기본 생성자
	public SetBookShelf() {
		// 멤버변수 HashSet<Book>으로 초기화
		books = new HashSet<Book>();
	}

	// 매개 변수가 있는 생성자
	public SetBookShelf(Set<Book> books) {
		this.books = books;
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		int addCnt = 0;

		// 같은 객체가 있는지 검사
		if (!isExists(book)) {
			books.add(book);
			addCnt++;

		} else {
			throw new DuplicateException("add", book);
		}

		return addCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		int result = -1;

		if (books.contains(book)) {
			books.remove(book);
			books.add(book);
			result = 1;
		} else {
			throw new NotFoundException("set", book);
		}

		return result;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		int rmIdx = -1;

		if (books.contains(book)) {
			books.remove(book);
			rmIdx = 1;
		} else {
			throw new NotFoundException("remove", book);
		}

		return rmIdx;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		Book found = null;

		if (books.contains(book)) {
			// 찾아올 제품이 존재
			for (Book buk : books) {
				found = buk;
			}
		} else {
			throw new NotFoundException("get", book);
		}

		return found;
	}

	@Override
	public List<Book> select() {
		List<Book> buks = new ArrayList<Book>();

		for (Book buk : books) {
			buks.add(buk);
		}

		return buks;
	}

	// Book 리스트에 매개변수 book 있는지 검사하는 메소드
	private boolean isExists(Book book) {
		return books.contains(book);
	}

	@Override
	public List<Book> select(int low, int high) {
		List<Book> books = new ArrayList<Book>();

		for (Book buk : this.books) {

			if (buk.getPrice() >= low && buk.getPrice() <= high) {

				books.add(buk);
			}

		}
		return books;
	}

	@Override
	public List<Book> select(String keyword) {
		List<Book> books = new ArrayList<Book>();

		// foreach 사용
		for (Book buk : this.books) {

			if (buk.getTitle().contains(keyword)) {

				books.add(buk);
			}
		}
		return books;
	}

	@Override
	public int totalCount() {
		int totalCnt = books.size();
		
		return totalCnt;
	}

}
