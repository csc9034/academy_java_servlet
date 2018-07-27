<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 전체 목록</title>
<style type="text/css">
	table, tr, th, td {
		border : 1px solid black;
	}
</style>
</head>
<body>
<h3>제품 전체 목록</h3>
<hr>
	<table>
	<!-- tr>(th*4) -->
	<tr>
		<th>제품 코드</th>
		<th>제품 이름</th>
		<th>가격</th>
		<th>재고</th>
		<th></th>
	</tr>
	<c:if test="${not empty products}">
	<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.prodCode }</td>
			<td> 
				<a href="detail?prodCode=${product.prodCode }">
				${product.prodName}
				</a>
			</td>
			<td><fmt:formatNumber value="${product.price }" type="currency" currencySymbol="₩" currencyCode="KRW"/></td>
			<td>${product.quantity }</td>
			<td><a href="delete?prodCode=${product.prodCode }">삭제</a></td>
		</tr>
	
	
	</c:forEach>
	</c:if>
		<tr>
			<td colspan="5" style="text-align:right;"><a href="${contextPath }/main/insert">신규 목록 추가</a> 
							<a href="${contextPath }/main/menu">메뉴로 이동..</a></td>
			
		</tr>
	<c:if test="${empty products }">
	
	<tr>
		<td colspan="4"> 등록 된 제품정보가 존재하지 않습니다. </td>
	</tr>
	</c:if>
	</table>
</body>
</html>