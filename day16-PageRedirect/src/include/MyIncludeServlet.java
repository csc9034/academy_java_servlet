package include;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 페이지 이동 방식 중 <br/>
 * Incldue 방식을 사용하여 페이지 이동을 <br/>
 * 테스트하는 클래스 <br/>
 * ------------------------------------------- <br/>
 * Incldue 방식으로 페이지를 이동시키면 <br/>
 * 최초의 request, response 가 유지되며 이동한다. <br/>
 * 
 * 이동 된 두 번째 서블릿의 실행결과를 
 * 이 서블릿이 다시 받아서
 * 포함하여 여기서 응답을 일으킨다.
 * 
 * @author CHO
 *
 */
@WebServlet("/include")
public class MyIncludeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 응답 객체에 한글 설정
		response.setContentType("text/html;charset=utf-8");
		
		// 1. request 객체에 name 속성을 추가
		request.setAttribute("name", "조성철");
		
		// 2. include 로 next 서블릿 이동을 위하여
		//    RequestDispatcher 를 얻어냄
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher("next");
		
		// (2) 얻어진 dispatcher 객체 reqd 에 include() 사용
		//	   이 때, 최초의 request, response 를 전달
		reqd.include(request, response);
	}
}
