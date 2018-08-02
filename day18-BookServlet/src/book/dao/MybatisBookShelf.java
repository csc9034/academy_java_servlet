package book.dao;

import static book.mybatis.MybatisClient.getFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.mapper.BookMapper;
import book.vo.Book;

public class MybatisBookShelf implements BookShelf {

	private SqlSessionFactory factory;

	public MybatisBookShelf() {
		factory = getFactory();
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		// 등록 할 대상 제품이 있는지 선 조회
		if (isExists(book)) {
			throw new DuplicateException("등록", book);
		}

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int addCnt = 0;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);

		try {
			// 3. mapper를 통하여 등록 진행
			addCnt = mapper.insert(book);

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return addCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		// 수정 할 대상 제품이 있는지 선 조회
		if (!isExists(book)) {
			throw new NotFoundException("수정", book);
		}
		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int setCnt = 0;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			setCnt = mapper.update(book);
		
		} finally {
			
			if (session != null) {
				session.close();
			}
		}
		return setCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		// 삭제 할 대상 제품이 있는지 선 조회
		if (!isExists(book)) {
			throw new NotFoundException("삭제", book);
		}
		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int setCnt = 0;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		try {
			setCnt = mapper.delete(book);
		
		} finally {
			
			if (session != null) {
				session.close();
			}
		}
		return setCnt;		
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		// 조회 할 대상 책이 있는지 선 조회
		if (!isExists(book)) {
			throw new NotFoundException("조회", book);
		}

		// 1. SqlSession 얻기
		SqlSession session = factory.openSession(false);
		Book found = null;
		
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);

		try {
			// 3. mapper를 통하여 삭제 진행
			found = mapper.selectOne(book);

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return found;
	}

	@Override
	public List<Book> select() {
		SqlSession session = factory.openSession();

		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);

		List<Book> books = null;

		try {
			books = mapper.selectAll();

		} finally {

			if (session != null) {
				session.close();

			}
		}
		return books;
	}

	@Override
	public List<Book> select(int low, int high) {
		SqlSession session = factory.openSession();

		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		List<Book> books = new ArrayList<Book>();
		
		Map<String, Integer> price = new HashMap<String, Integer>();
		price.put("low", low);
		price.put("high", high);
		
		try {
			books = mapper.selectPrice(price);
			
		} finally {

			if (session != null) {
				session.close();

			}
		}
		return books;
	}

	@Override
	public List<Book> select(String keyword) {
		SqlSession session = factory.openSession();

		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		List<Book> books = new ArrayList<Book>();
		
		try {
			books = mapper.selectKeyword(keyword);
			
		} finally {

			if (session != null) {
				session.close();

			}
		}
		return books;
	}

	@Override
	public int totalCount() {
		SqlSession session = factory.openSession();

		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);
		
		int total = 0;
		
		try {
			total = mapper.selectTotalCnt();
			
		} finally {

			if (session != null) {
				session.close();

			}
		}
		return total;
	}

	// isExists 지원 메소드 작성
	private boolean isExists(Book book) {
		boolean isExists = false;

		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		BookMapper mapper;
		mapper = session.getMapper(BookMapper.class);

		// 3. mapper 객체에 메소드 호출로 쿼리 실행
		try {
			String prodCode = mapper.isExists(book);

			if (prodCode != null) {
				isExists = true;
			}

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return isExists;
	}

}
