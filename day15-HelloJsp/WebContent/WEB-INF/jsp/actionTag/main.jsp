<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<!-- 요청 주소
	 -->

   
   <h1>길동이 닷 컴</h1>
   <h2>길동이 닷 컴에 오신것을 환영합니다.</h2>
   
   <!-- <hr> 화면에 수평을 그려주는 태그 -->
   <hr size = "4">
   
   <!-- 보통 메인화면 하단에 copy right를 표기하는
        화면 푸터를 액션태그를 사용하여 삽입 -->
        
        <jsp:include page="/copy">
        <jsp:param value="red" name="color"/>
        </jsp:include>
       
        <%--<jsp:include page=""> 는 다른 페이지 삽입해달라는 요청으로 해석
        끼워넣을 페에지에 전달할 값이 있다면
        요청 파라미터로 전달가능
        이 때<jsp:param>을 사용하여 다른 페이지로 값을 전달--%>
        
        
</body>
</html>
