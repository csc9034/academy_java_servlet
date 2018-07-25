<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 신규 등록</title>
<style type="text/css">
table, tr, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3>제품 신규 등록</h3>
	<hr>
	<form action="insert" method="post">
		<!-- table>tr>th[colspan=2]+(tr>(th+td)*4) -->
		<table>
			<tr>
				<th colspan="2">제품 등록하기</th>
			</tr>
			<tr>
				<th>제품 코드</th>
				<td><input name="prodCode" type="text" required="required" />
				</td>
			</tr>
			<tr>
				<th>제품 이름</th>
				<td><input name="prodName" type="text" /></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input name="price" type="number" /></td>
			</tr>
			<tr>
				<th>재고</th>
				<td><input name="quantity" type="number" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="등록하기"> <input
					type="reset" value="초기화"></td>
			</tr>
		</table>
	</form>
</body>
</html>