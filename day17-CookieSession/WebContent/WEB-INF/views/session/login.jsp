<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Session 을 사용한 로그인</title>
<style type="text/css">
	table, tr, th, td {
		border : 1px solid black;
	}
</style>
</head>
<body>
<h3>로그인 하세요(Session)</h3>
<form action="login" method="post">
<!-- table>(tr>(th+td>input))*3 -->
<table>
	<tr>
		<th>유저 아이디</th>
		<td>
			<input type="text" name="userId" required="required"/>
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>
			<input type="password" name="password" required="required" />
		</td>
	</tr>
	<tr>
		<td colspan ="2">
			<input type="reset" value="초기화" />
			<input type="submit" value="로그인" />
		</td>
	</tr>
</table>


</form>
</body>
</html>