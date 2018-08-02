<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>책 상세보기</title>
<style type="text/css">
table, tr, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3>책 상세 조회</h3>
	<hr>
		<table>
			<tr>
				<th>책 코드</th>
				<td>${book.bookId }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${book.title }</td>
			</tr>
			<tr>
				<th>저자</th>
				<td>${book.author }</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>${book.price }</td>
			</tr>
			<tr>
				<th>isbn</th>
				<td>${book.isbn }</td>
			</tr>
			<tr>
				<th>publish</th>
				<td>${book.publish }</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
				<a href="list">목록보기</a>
				<a href="${contextPath }/main/update?bookId=${book.bookId }">수정</a>
				<a href="${contextPath }/main/delete?bookId=${book.bookId }">삭제</a>
		</table>
</body>
</html>