package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * login.jsp 에서 userid 와 password 파라미터 값을 받아서
 * 로그인 기능을 수행하여 menu.jsp 로 이동해주는 서블릿 클래스
 * 
 * @author CHO
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * login.jsp 로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 뷰 초기화
		String view = "/loginJsp";

		// 페이지 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);

		reqd.forward(request, response);
	}

	/**
	 * login.jsp 에서 <form> 태그의 
	 * 로그인 정보를 가져와서 로그인 수행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 처리 : EncodingFilter.java 에서 처리함.
		
		// login.jsp 에서 전달된 파라미터 값 추출
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// 콘솔 확인
		System.out.println("userid = " + userid);
		System.out.println("password = " + password);
		
		String message = null;
		String view = null;
		String next = null;
		
		// view 초기화
		view = "/messageJsp";
		
		if ("cho".equals(userid) && "java".equals(password)) {
			// 로그인 성공 시 세션 생성
			HttpSession session = request.getSession();
			
			// session 객체에 로그인한 아이디 속성 추가
			session.setAttribute("userid", userid);
			
			// 콘솔 확인
			System.out.println("userid = " + userid);
			
			// 로그인 성공 시 message 초기화
			message = userid  + "님, Welcome to Java World!";
			
			// 로그인 message.jsp 이후 보여질 next 페이지 초기화 
			next = "main/menu";
			
		} else {
			// 로그인 실패 시 message 초기화
			message = "로그인에 실패했습니다.";
			
			// 로그인 message.jsp 이후 보여질 next 페이지 초기화 
			next = "loginJsp";
		}
		
		// message, next 속성 추가
		request.setAttribute("message", message);
		request.setAttribute("next", next);
		
		// 페이지 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);

		reqd.forward(request, response);
	}

}
