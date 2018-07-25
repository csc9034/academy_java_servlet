<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="shop.vo.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table>
			<tr>
				<th>제품 코드</th>
				<td><input name="prodCode" type="text" required="required" value ="${product.prodCode }"/>
				</td>
			</tr>
			<tr>
				<th>제품 이름</th>
				<td><input name="prodName" type="text" value ="${product.prodName }"/></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input name="price" type="number" value ="${product.price }"/></td>
			</tr>
			<tr>
				<th>재고</th>
				<td><input name="quantity" type="number" value ="${product.quantity }"/></td>
			</tr>
		</table>
</body>
</html>