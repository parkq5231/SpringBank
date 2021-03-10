<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="auth">사용자 인증</a>
	<a href="callback?code=">토큰발급</a>
	<a href="userInfo?access_token=${access_token}">사용자 정보</a>
	<div>access_token:${access_token}</div>
</body>
</html>