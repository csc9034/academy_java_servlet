package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.exception.DuplicateException;
import book.vo.Book;

/**
 * 북을 등록 요청을 처리해주는 서블릿
 * 
 * @author CHO
 *
 */
@WebServlet({ "/insert", "/main/insert" })
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * menu.jsp 에서 북 등록을 클릭했을 때 insert.jsp 로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 뷰를 결정
		String view = "/insertJsp";
		
		// RequestDispatcher 로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}


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
			bookshelf.insert(book);
			
			// 2.(2) 삽입 성공 시 메세지
			message = String.format("제품 정보[%s]추가에 성공하였습니다.", book.getBookId()); 
			
			// 2.(2) view 선택
			next = "main/list";
			
		
		} catch (DuplicateException e) {
			message = String.format (e.getMessage());
			
			// 실패시 다시 입력하는 화면으로 자동 이동
			next = "main/insert";
			
			e.printStackTrace();
		
		}
		
		// (5) 추가 성공 / 실패에 따른 발생 메시지를
		//     request 에 속성으로 추가
		request.setAttribute("message", message);
		// 3. view 결정
		// (1) 추가 성공 / 실패 메시지를 출력할 1차 뷰
		view = "/messageJsp";
		
		// (2) 2차 뷰 선택된 내용을 request 에 속성으로 추가
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}

}
