<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>책 수정하기</title>
<style type="text/css">
table, tr, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3>책 정보 수정</h3>
	<hr>
	<form action="${contextPath }/main/update" method="post">
		<table>
			<tr>
				<th>책 코드</th>
				<td>
				<input name="bookId" type="text" required="required" value ="${book.bookId }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
				<input name="title" type="text" value ="${book.title }" />
				</td>
			</tr>
			<tr>
				<th>저자</th>
				<td>
				<input name="author" type="text" value ="${book.author }" />
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
				<input name="price" type="number" value ="${book.price }" />
				</td>
			</tr>
			<tr>
				<th>isbn</th>
				<td>
				<input name="isbn" type="text" value ="${book.isbn }" />
				</td>
			</tr>
			<tr>
				<th>publish</th>
				<td>
				<input name="publish" type="text" value ="${book.publish }" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
				<a href="${contextPath }/main/list">목록보기</a>
				<a href="${contextPath }/main/detail?bookId=${book.bookId }">수정 취소</a>
				<input type="submit" value="저장">
				<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>