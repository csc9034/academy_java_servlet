<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL(4) Core Tag : choose</title>
</head>
<body>
<h4>&lt;choose&gt;</h4>
<pre>
&lt;choose&gt; &lt;c:if&gt; 와 유사한 조건 분기 기능
		if ~ else if ~ else 구문과 비슷하게 사용 가능
		내부에
		&lt;c:when&gt;, &lt;c:otherwise&gt; 를 배치해서 분기
</pre>
<hr/>
<% // 리스트에 1 ~ 10 까지 숫자를 저장 
	List<Integer> numbers = new ArrayList<>();
	for (int idx = 1; idx < 11; idx++) {
		numbers.add(idx);
	}
	
	// 리스트를 request에 추가
	request.setAttribute("numbers", numbers);
%>
<c:forEach items="${numbers }" var="num">

<c:choose>
	<c:when test="${num % 2 eq 0}">
		${num } 은(는) 짝수입니다. <br/>
	</c:when>
	<c:when test="${num % 2 eq 1}">
		${num } 은(는) 홀수입니다. <br/>
	</c:when>
	<c:otherwise>
		잘못 된 값입니다.
	</c:otherwise>
</c:choose>
</c:forEach>
	
</body>
</html>