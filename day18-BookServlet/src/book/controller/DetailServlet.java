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
 * list.jsp에서 detail.jsp 페이지로  
 * 이동해주는 요청을 처리하는 서블릿
 *
 * @author CHO
 *
 */
@WebServlet({ "/detail", "/main/detail" })
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * list.jsp 에서 특정 책을 클릭했을 때 해당 책의 detail.jsp를 요청을 처리해주는 메소드
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
			view = "/detailJsp";
			
		} catch (NotFoundException e) {
			// (4) 조회 실패 메세지
			message = e.getMessage();
			
			// (5) message 속성에 추가
			request.setAttribute("message", message);
			
			// (6) 삭제 메시지 뷰 선택
			view = "/messageJsp";
			
			// (7) next list.jsp 로 초기화
			next = "main/list";
			request.setAttribute("next", next);
			
			e.printStackTrace();
		}
		
		// 3. 뷰 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);

		reqd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
