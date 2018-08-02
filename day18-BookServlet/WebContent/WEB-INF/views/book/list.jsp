<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>책 리스트 보기</title>
<style type="text/css">
	table, tr, th, td {
		border : 1px solid black;
	}
</style>
</head>
<body>
<h3>책 전체 목록</h3>
<hr>
	<table>
	<!-- tr>(th*4) -->
	<tr>
		<th>책 코드</th>
		<th>이름</th>
		<th>저자</th>
		<th>가격</th>
		<th>isbn</th>
		<th>출판사</th>
		<th></th>
	</tr>
	<c:if test="${not empty books}">
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.bookId }</td>
			<td> 
				<a href="detail?bookId=${book.bookId }">
				${book.title}
				</a>
			</td>
			<td>${book.author }</td>
			<td><fmt:formatNumber value="${book.price }" type="currency" currencySymbol="₩" currencyCode="KRW"/></td>
			<td>${book.isbn }</td>
			<td>${book.publish }</td>
			<td><a href="delete?bookId=${book.bookId }">삭제</a></td>
		</tr>
	
	
	</c:forEach>
	</c:if>
		<tr>
			<td colspan="7" style="text-align:right;"><a href="${contextPath }/main/insert">신규 목록 추가</a> 
							<a href="${contextPath }/main/menu">메뉴로 이동..</a></td>
			
		</tr>
	<c:if test="${empty books }">
	
	<tr>
		<td colspan="4"> 등록 된 책정보가 존재하지 않습니다. </td>
	</tr>
	</c:if>
	</table>
</body>
</html>