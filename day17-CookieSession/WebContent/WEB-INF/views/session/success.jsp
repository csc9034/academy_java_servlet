<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 성공 (Session)</title>
</head>
<body>
<h3>로그인 성공 (Session)</h3>
<hr>
안녕하세요, ${sessionScope.userId }님, 로그인 되었습니다. <br/>
안녕하세요, ${userId }님, 로그인 되었습니다. <br/>
<hr>
<a href="${pageContext.request.contextPath}/session/logout">로그 아웃</a>
</body>
</html>