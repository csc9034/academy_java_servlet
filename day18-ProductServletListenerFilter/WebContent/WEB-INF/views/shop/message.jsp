<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 2초 후에 명시 된 url로 자동 이동하는 meta 태그 사용 -->
<meta http-equiv="refresh" content="2;url=${contextPath }/${next}">
<title>시스템 메시지</title>
</head>
<body>
<%-- 이 페이지로 이동이 되었을 때는
	 request 객체에 message 라는 이름의 속성이 설정 된 상태
	 message 속성을 EL 을 이용하여 출력만 하는 페이지
--%>
${message }
<hr>
${contextPath }/${next}
</body>
</html>