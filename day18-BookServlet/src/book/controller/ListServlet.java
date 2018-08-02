package book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.vo.Book;

/**
 * 책 정보를 조회 요청을 처리하는 서블릿
 * 
 * @author CHO
 *
 */
@WebServlet({ "/list", "/main/list" })
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 책 정보를 조회하고 list.jsp 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. DB 얻기
		BookShelf bookshelf;
		bookshelf = (BookShelf) getServletContext().getAttribute("bookshelf");
		
		// 2. 북 리스트 객체 생성
		List<Book> books = bookshelf.select();
		
		// (1) books를 속성에 추가
		request.setAttribute("books", books);
		
		// 3. 조회 결과가 추가된 request 를
		//	  적절한 목록 뷰 (list view) 로 전달 (페이지 이동)
		String view = "/listJsp";
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
