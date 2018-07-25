<%---
	1. JSP 주석 : JSP 가 Servlet 으로 변환될 때 무시됨
	2. <%@		: Directive Tag : 현재 페이지에 대한 설정을 지정할 때 사용
 --%>
<%@page import="java.sql.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='java.util.*'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 3. <% ... : Sciriptlet Tag : JSP 안에서 순수 자바코드를 쓸 수 있는 태그
		// 			   너무 많이 사용하면 가독성을 떨어뜨림
		//			   이 태그 안에는 완전한 자바 [문장]이 들어감
		//			   문장(statement) : ; 으로 종료되는 한 줄
		//					(1) 할당문 :  변수에 값 저장)
		//					(2) 메소드 호출문 : ojject.toString()
		//					(3) 제어구조 : if, while, for
		//					(4) 지역변수 선언
		//				문자이 아닌 것(메소드 선언, 클래스 선언) 등은 불가능
		
		int age = 55;
		List<Integer> ages = new ArrayList<>();
		ages.add(12);
		ages.add(20);
		ages.add(37);
		ages.add(45);
		ages.add(age);
		
		for (int age2 : ages) {
			System.out.println ("구성원의 나이 : " + age2);
		
	%>

	<!-- 여기에는 화면에 반복 출력할 HTML을 구성 -->
	<%-- 4. <%= : Expression Tag : 변수 값 하나를 출력할 때 사용
									 리턴이 있는 메소드 호출 구문 사용 가능 
									 ; 을 쓰지 않는다.
	--%>

	구성원의 나이 : <%=age2 %>
	<br/>
	<%
	} // 스크립트릿 안에서는 자바 주석 사용 가능
	  // for 구조가 스크립트릿 안에서 시작하므로
	  // 닫을 때도 스크립트릿 안에서 닫혀야 한다.
	
	for(int idx = 0; idx < 5; idx++) {
		
	%>
	안녕하세요 , JSP 반복 실행 <%=idx %>
	<br/>

	<% } %>
</body>
</html>