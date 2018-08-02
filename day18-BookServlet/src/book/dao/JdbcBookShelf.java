package book.dao;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public class JdbcBookShelf implements BookShelf{
	
	private static final String URL = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";
	private static final String DRIVER = "oracle.jdbc.OracleDriver";

	// 싱글턴 2. 이 클래스 타입의 static 변수 instance 를 선언
	public static JdbcBookShelf instance;
	
	// 싱글턴 3. 이 클래스 타입을 리턴하는 static 메소드 선언
	public static JdbcBookShelf getInstance() {
		
		if (instance == null) {
			
			instance = new JdbcBookShelf();
			
		}
		
		return instance;
	}
	
	private JdbcBookShelf() { 
		// 드라이버 로드는 실행할 때 최초 1번만 수행하면 되므로
		// 한번 실행되는 생성자로 이동
		
		// 1. 드라이버 로드
		try {
			Class.forName(DRIVER);
			
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 오류!!");
			e.printStackTrace();
			
		}
	}

	@Override
	public int insert(Book book) throws DuplicateException {
		// 추가하려는 제품이 이미 존재하는지 검사
		if (isExists(book)) {
			throw new DuplicateException("추가", book);
		}
		
		// 0. 필요 객체 선언
		int addCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "INSERT INTO book(BOOKID, TITLE, AUTHOR, PRICE, ISBN, PUBLISH) "
					   + "VALUES (?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookId());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getIsbn());
			pstmt.setString(6, book.getPublish());
			
			// 4. 쿼리 실행
			addCnt = pstmt.executeUpdate();
			
			// 5. 결과 처리
			//   ==> 쿼리 실행 전 중복 여부 검사하므로 특별한
			//       결과 처리가 필요 없음
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return addCnt;
	}

	@Override
	public int update(Book book) throws NotFoundException {
		// 추가하려는 제품이 이미 존재하는지 검사
		if (!isExists(book)) {
			throw new NotFoundException("수정", book);
		}
		
		// 0. 필요 객체 선언
		int upCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "UPDATE book b SET b.TITLE = ?, b.AUTHOR = ?, b.PRICE = ?, b.ISBN = ?"
					   + "                , b.PUBLISH = ?, b.moddate = sysdate "
					   + "WHERE b.BOOKID = ?";

			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getPrice());
			pstmt.setString(4, book.getIsbn());
			pstmt.setString(5, book.getPublish());
			pstmt.setString(6, book.getBookId());
			
			// 4. 쿼리 실행
			upCnt = pstmt.executeUpdate();
			
			// 5. 결과 처리
			//   ==> 쿼리 실행 전 중복 여부 검사하므로 특별한
			//       결과 처리가 필요 없음
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return upCnt;
	}

	@Override
	public int delete(Book book) throws NotFoundException {
		// 추가하려는 제품이 이미 존재하는지 검사
		if (!isExists(book)) {
			throw new NotFoundException("삭제", book);
		}
		
		// 0. 필요 객체 선언
		int delCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "DELETE FROM book b "
					   + " WHERE b.BOOKID = ?";

			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, book.getBookId());
			
			// 4. 쿼리 실행
			delCnt = pstmt.executeUpdate();
			
			// 5. 결과 처리
			//   ==> 쿼리 실행 전 중복 여부 검사하므로 특별한
			//       결과 처리가 필요 없음
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(null, pstmt, conn);
		}
		
		return delCnt;
	}

	@Override
	public Book select(Book book) throws NotFoundException {
		// 조회하려는 제품 존재여부 검사 : isExists()
				if (!isExists(book)) {
					throw new NotFoundException("조회", book);
				}
				
				// 0. 필요 객체 선언
				Book found = null;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet result = null;
				
				try {
					// 2. 커넥션 맺기
					conn = getConnection(URL, USER, PASSWORD);
					
					// 3. 쿼리 준비
					String sql = "SELECT b.BOOKID "
							   + ",		 b.TITLE" 
							   + ", 	 b.AUTHOR" 
							   + ",	 	 b.PRICE"
							   + ",	  	 b.ISBN"
							   + ",		 b.PUBLISH"
							   + "  FROM book b "
							   + " WHERE b.BOOKID = ?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, book.getBookId());
					
					// 4. 쿼리 실행
					result = pstmt.executeQuery();
					
					// 5. 결과 처리
					while (result.next()) {
						String bookId = result.getString(1);
						String title = result.getString(2);
						String author = result.getString(3);
						int price = result.getInt(4);
						String isbn = result.getString(5);
						String publish = result.getString(6);
								
						found = new Book(bookId, title, author, price, isbn, publish);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					
				} finally {
					// 6. 자원 해제
					closeResources(result, pstmt, conn);
				}
				
				return found;
			}

	@Override
	public List<Book> select() {
		// 0. 필요 객체 선언
		List<Book> products = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "SELECT b.BOOKID "
					   + ",		 b.TITLE" 
					   + ", 	 b.AUTHOR" 
					   + ",	 	 b.PRICE"
					   + ",	  	 b.ISBN"
					   + ",		 b.PUBLISH "
					   + "  FROM book b";
			
			pstmt = conn.prepareStatement(sql);
			
			// 4. 쿼리 실행
			result = pstmt.executeQuery();
			
			// 5. 결과 처리
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				Book book = new Book(bookId, title, author, price, isbn, publish);
				
				products.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(result, pstmt, conn);
		}
		
		return products;
	}

	@Override
	public List<Book> select(int low, int high) {
		// 0. 필요 객체 선언
		List<Book> products = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "SELECT b.BOOKID "
					   + ",		 b.TITLE" 
					   + ", 	 b.AUTHOR" 
					   + ",	 	 b.PRICE"
					   + ",	  	 b.ISBN"
					   + ",		 b.PUBLISH "
					   + "  FROM book b "
					   + " WHERE b.PRICE BETWEEN '" + low + "' AND '" + high + "'";
			
			pstmt = conn.prepareStatement(sql);
			
			// 4. 쿼리 실행
			result = pstmt.executeQuery();
			
			// 5. 결과 처리
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				Book book = new Book(bookId, title, author, price, isbn, publish);
				
				products.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(result, pstmt, conn);
		}
		
		return products;
	}

	@Override
	public List<Book> select(String keyword) {
		// 0. 필요 객체 선언
		List<Book> products = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			// 2. 커넥션 맺기
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "SELECT b.BOOKID "
					   + ",		 b.TITLE" 
					   + ", 	 b.AUTHOR" 
					   + ",	 	 b.PRICE"
					   + ",	  	 b.ISBN"
					   + ",		 b.PUBLISH "
					   + "  FROM book b "
					   + " WHERE b.TITLE LIKE '%'" + keyword + "' % '";
			
			pstmt = conn.prepareStatement(sql);
			
			// 4. 쿼리 실행
			result = pstmt.executeQuery();
			
			// 5. 결과 처리
			while (result.next()) {
				String bookId = result.getString(1);
				String title = result.getString(2);
				String author = result.getString(3);
				int price = result.getInt(4);
				String isbn = result.getString(5);
				String publish = result.getString(6);
				
				Book book = new Book(bookId, title, author, price, isbn, publish);
				
				products.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(result, pstmt, conn);
		}
		
		return products;
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 지원 메소드 : 등록, 수정, 삭제, 조회할 대상 제품의
	//               존재여부를 확인하는 메소드
	private boolean isExists(Book book) {
		boolean isExist = false;
		
		// 0. 필요객체 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;		

		try {
			// 2. 커넥션
			conn = getConnection(URL, USER, PASSWORD);
			
			// 3. 쿼리 준비
			String sql = "SELECT b.BOOKID" 
			           + "  FROM book b" 
					   + " WHERE b.BOOKID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookId());
			
			// 4. 쿼리 실행
			result = pstmt.executeQuery();
			
			// 5. 결과 처리
			while (result.next()) {
				// 조회 결과가 있다는 뜻은 동일 제품 코드가 등록되었음
				isExist = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. 자원 해제
			closeResources(result, pstmt, conn);
		}
		
		return isExist;
	}

	/**
	 * 반복되는 자원 해제코드를 수행하는 지원 메소드
	 * @param result
	 * @param stmt
	 * @param conn
	 */
	private void closeResources(ResultSet result, Statement stmt, Connection conn) {
		try {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();					
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			System.err.println("자원 반납 오류!");
			e.printStackTrace();
		}
		
	}
	

}
