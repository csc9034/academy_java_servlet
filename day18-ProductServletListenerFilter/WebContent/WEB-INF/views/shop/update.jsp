<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 수정하기</title>
<style type="text/css">
table, tr, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h3>제품 정보 수정</h3>
	<hr>
	<form action="${contextPath }/main/update" method="post">
		<table>
			<tr>
				<th>제품 코드</th>
				<td>
				<input name="prodCode" type="text" required="required" value ="${product.prodCode }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>제품 이름</th>
				<td>
				<input name="prodName" type="text" value ="${product.prodName }" />
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
				<input name="price" type="number" value ="${product.price }" />
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>
				<input name="quantity" type="number" value ="${product.quantity }" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
				<a href="${contextPath }/main/list">목록보기</a>
				<a href="${contextPath }/main/detail?prodCode=${product.prodCode }">수정 취소</a>
				<input type="submit" value="저장">
				<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>