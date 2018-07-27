package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 현재 요청(request)에 연결된 세션을 얻어냄
		HttpSession session = request.getSession(false);
		
		System.out.println("session:" + session);
		
		// 2. 유효한 세션에 추가되었을 userid 속성 추출
		String userid = (String)session.getAttribute("userid");
		
		if (userid != null) {
			// 3. 얻어진 세션을 invalidate()
			session.invalidate();
			
			// 4. 로그 아웃 메시지를 request 에 속성 추가 
			request.setAttribute("message", userid + "님, 로그아웃 되었습니다");
			
			// 5. 로그아웃 메시지 처리 후 다시 로그인
			//    화면으로 이동시킬 next 값 추구
			request.setAttribute("next", "login");
			
			
			// 6. 페이지 이동
			request.getRequestDispatcher("/messageJsp").forward(request, response);
			
			
		}
	}

}
