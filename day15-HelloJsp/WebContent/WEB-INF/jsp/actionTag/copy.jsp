<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
   메인화면 하단에 삽입되어 저작권 표기 
   copy right를 나타내는 푸터페이지
   
   main.jsp에 포함(<jsp:include>)되어 화면에 나타남
   
   main.jsp에서 이 페이지 요철할 때,<jsp:param>으로 
   전달한 파라미터를 추출하여 화면에 적용
 --%>
 <%
 	// JSP의 내장객체인 request로 부터 main.jsp 에서 설정된
 	// 요청 파라미터를 추출할 수 있다.
 	String color = request.getParameter("color");
 %>
 	<font color="<%=color %>">
 	copyright &copy; gildong2.com
 	</font>
 
 
</body>
</html>