<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 관리 메뉴</title>
</head>
<body>
<!-- ul>(li>a)*2 -->
<h3>제품 관리 페이지</h3>
<hr>
<ul>
	<li><a href="${contextPath }/main/list">전체 제품 목록</a></li>
	<li><a href="${contextPath }/main/insert">제품 신규 등록</a></li>
	<li><a href="${contextPath }/logout">로그아웃</a></li>
</ul> 
</body>
</html>