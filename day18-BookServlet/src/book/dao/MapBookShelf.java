package book.dao;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class MapBookShelf implements BookShelf {
	// 1. 멤버 변수 선언
	Map<String, Book> books;

	// 2. 생성자 선언
	// (1) 기본 생성자
	public MapBookShelf() {
		books = new HashMap<String, Book>();
	}

	// (2) 매개변수가 있는 생성자
	public MapBookShelf(Map<String, Book> books) {
		this.books = books;
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		int addCnt = 0;

		// 같은 객체가 있는지 검사
		if (!isExists(book)) {
			books.put(book.getBookId(), book);
			addCnt++;
		} else {
			throw new DuplicateException("add", book);
		}

		return addCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		int setCnt = 0;

		if (isExists(book)) {
			books.replace(book.getBookId(), book);
			setCnt++;
		} else {
			throw new NotFoundException("set", book);
		}

		return setCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		int rmCnt = 0;

		if (isExists(book)) {
			books.remove(book.getBookId());
			rmCnt++;
		} else {
			throw new NotFoundException("remove", book);
		}

		return rmCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		Book found = null;

		if (isExists(book)) {
			found = books.get(book.getBookId());
		} else {
			throw new NotFoundException("get", book);
		}

		return found;
	}

	@Override
	public List<Book> select() {
		Set<String> bookKeys = books.keySet();
		List<Book> buks = new ArrayList<Book>();

		for (String key : bookKeys) {
			Book book = books.get(key);
			buks.add(book);
		}
		return buks;
	}

	private boolean isExists(Book book) {
		return books.containsKey(book.getBookId());
	}

	@Override
	public List<Book> select(int low, int high) {
		Set<String> bookKeys = books.keySet();
		List<Book> books = new ArrayList<Book>();

		for (String key : bookKeys) {

			Book book = this.books.get(key);
			if (book.getPrice() >= low && book.getPrice() <= high) {

				books.add(book);
			}

		}
		return books;
	}

	@Override
	public List<Book> select(String keyword) {
		Set<String> bookKeys = books.keySet();
		List<Book> books = new ArrayList<Book>();

		// foreach 사용
		for (String key : bookKeys) {

			Book book = this.books.get(key);

			if (book.getTitle().contains(keyword)) {

				books.add(book);
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
