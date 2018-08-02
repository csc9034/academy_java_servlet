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
 * delete.jsp 에서 데이터를 
 * 삭제 요청하는 서블릿 (delete?bookCode=??) => 이런 형식
 * 
 * @author CHO
 *
 */
@WebServlet({ "/delete", "/main/delete" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 책 상세조회 페이지에서 책 삭제를 링크를 통해 삭제하는 요청을 처리
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 모델 생성
		// (1) 요청 파라미터 추출
		String bookId = request.getParameter("bookId");

		// (2) 객체 포장
		Book book = new Book(bookId);

		// (3) DB 객체 얻기
		BookShelf bookshelf;
		bookshelf = (BookShelf) getServletContext().getAttribute("bookshelf");

		// 2. View 선택
		String view = null;
		String message = null;
		String next = null;

		try {
			// (1) delete 수행
			bookshelf.delete(book);

			// (2) 삭제 성공 메시지
			message = String.format("책 정보[%s] 삭제에 성공하였습니다.", book.getBookId());

		} catch (NotFoundException e) {
			
			// (3) 삭제 실패 메시지
			message = e.getMessage();

			e.printStackTrace();

		}
		// (4) 메시지를 속성에 추가
		request.setAttribute("message", message);
		
		// 3. (1).1차 뷰 선택
		view = "/messageJsp";
		
		// (2) 2차 뷰 선택
		next = "main/list";
		request.setAttribute("next", next);
		
		// 4. 결정된 뷰로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
