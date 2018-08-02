package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.exception.NotFoundException;
import book.vo.Book;

/**
 * 제품 1건 수정 요청 처리하는 서블릿
 * --------------------------------------------------
 * GET
 * 1. detail.jsp 에서 [수정] 링크 통해 들어온 요청
 *    ==> 현재 상세보기 하고 있던 제품을 조회를 하여
 *    ==> 수정할 수 있는 화면인 update.jsp로 전달
 *    ==> 수정을 위한 화면 이동(이미 있는 화면 요청)
 *    ==> 그래서 GET 요청으로 처리함
 *    
 * --------------------------------------------------
 *    
 * POST
 * 2. update.jsp 에서 수정된 내용을 [저장] 버튼을 통해
 *    들어온 요청을 처리
 *    ==> 변경된 입력 내용을 실제 update 쿼리를 수행하여
 *    	  DB 에 영구 반영
 *    ==> 수정 성공 / 실패를 알리는 메시지 밠생
 *    ==> 메시지를 처리할 뷰 선택
 *    ==> 2차 뷰를 선택
 *    
 * @author CHO
 *
 */
@WebServlet({ "/update", "/main/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * detail.jsp 에서 update?prodCode=xxx 으로 발생한
	 * GET 요청을 처리
	 * 1. 전달된 요청 파라미터(prodCode)를 추출
	 * 2. 해당 책 정보 조회
	 * 3. 조회된 정보를 request 추가
	 * 4. 수정 가능한 뷰를 선택 후 화면 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 모델 생성
				// (1) 파라미터 추출
				String bookId = request.getParameter("bookId");
				
				// (2) 객체 포장
				Book book = new Book(bookId);
				
				// (3) DB 객체 얻기
				BookShelf bookshelf;
				bookshelf = (BookShelf) getServletContext().getAttribute("bookshelf");
				
				// 2. 뷰 생성
				String message = null;
				String view = null;
				String next = null;
				
				try {
					// (1) 특정 책 조회
					Book found = bookshelf.select(book);
					
					// (2) 조회 된 상세 정보 속성에 추가
					request.setAttribute("book", found);
					
					// (3) view 를 detail.jsp 로 초기화
					view = "/updateJsp";
					
				} catch (NotFoundException e) {
					// (4) 조회 실패 메세지
					message = e.getMessage();
					
					// (5) message 속성에 추가
					request.setAttribute("message", message);
					
					// (6) 삭제 메시지 뷰 선택
					view = "/messageJsp";
					
					// (7) next list.jsp 로 초기화
					next = "list";
					request.setAttribute("next", next);
					
					e.printStackTrace();
				}
				
				// 3. 뷰 이동
				RequestDispatcher reqd;
				reqd = request.getRequestDispatcher(view);

				reqd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 모델 생성
		// (1) 파라미터 추출
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String isbn = request.getParameter("isbn");
		String publish = request.getParameter("publish");
		
		// (2) 객체 포장
		Book book = new Book(bookId, title, author, price, isbn, publish);
		
		// (3) DB 얻기
		BookShelf bookshelf;
		bookshelf = (BookShelf) getServletContext().getAttribute("bookshelf");
		
		// 2. view 생성
		String view = null;
		String message = null;
		String next = null;
		
		try {
			// 2.(1) book 정보 삽입
			bookshelf.update(book);
			
			// 2.(2) 삽입 성공 시 메세지
			message = String.format("제품 정보[%s]추가에 수정하였습니다.", book.getBookId()); 
			
			// 2.(2) view 선택
			next = "main/detail?bookId=" + bookId;
			
		
		} catch (NotFoundException e) {
			// (5) 수정 실패 메시지 발생
			message = e.getMessage();
			
			// 3.(3) 수정 실패시 2차 뷰 : 목록으로 진입
			next = "main/list";
			e.printStackTrace();
		
		}
		
		// (6) 메시지 request 에 속성으로 추가
		request.setAttribute("message", message);
		
		// 3. view 선택
		// (2) 수정에 실패 / 성공 모두 messageJsp 전송
		view = "/messageJsp";
		
		// (4) 2차 뷰 request 에 속성 추가
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
		
	}

}
