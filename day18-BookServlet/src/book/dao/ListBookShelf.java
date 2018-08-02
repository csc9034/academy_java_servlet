package book.dao;

import java.util.ArrayList;
import java.util.List;
import book.vo.Book;
import book.exception.DuplicateException;
import book.exception.NotFoundException;

public class ListBookShelf implements BookShelf {

	// 1. List<book> 타입의 멤버 변수 books 선언
	List<Book> books;

	// 2. 생성자 선언부
	// (1) 기본 생성자
	public ListBookShelf() {
		// 멤버 변수 books 를 ArrayList<Book> 으로 초기화
		books = new ArrayList<Book>();
	}

	// (2) 매개 변수가 있는 기본 생성자
	public ListBookShelf(List<Book> books) {
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
		int setIdx = findProductIdx(book);

		if (setIdx > -1) {
			books.set(setIdx, book);

		} else {

			throw new NotFoundException("set", book);

		}

		return setIdx;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		int rmIdx = findProductIdx(book);

		if (rmIdx > -1) {
			books.remove(rmIdx);

		} else {

			throw new NotFoundException("remove", book);

		}

		return rmIdx;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		int getIndex = findProductIdx(book);
		Book found = null;

		if (getIndex > -1) {

			// 찾아올 책이 존재
			found = books.get(getIndex);

		} else {

			throw new NotFoundException("get", book);

		}

		return found;
	}

	@Override
	public List<Book> select() {
		return this.books;
	}

	// 리스트 안에 찾으려는 Book의 인덱스를 구하는 지원 메소드
	private int findProductIdx(Book book) {
		int index = -1;

		for (int idx = 0; idx < books.size(); idx++) {
			if (books.get(idx).equals(book)) {
				index = idx;
				break;
			}
		}

		return index;
	}

	// Book 리스트에 매개변수 book 있는지 검사하는 메소드
	private boolean isExists(Book book) {
		return books.contains(book);
	}

	@Override
	public List<Book> select(int low, int high) {
		List<Book> books = new ArrayList<Book>();

		// 전통적인 for문 사용
		/*
		 * for (int idx = 0; idx < books.size(); idx++) {
		 * 
		 * if (buks.get(idx).getPrice() >= low && buks.get(idx).getPrice() <=
		 * high) {
		 * 
		 * Book book = books.get(idx); buks.add(book); }
		 * 
		 * }
		 */

		// foreach 사용
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
