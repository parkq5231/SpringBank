<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="frm" name="frm" action="getBiz" method="post">
		<input name="bizno">
		<button>조회</button>
	</form>
	<div id="result">${bizname}</div>
</body>
</html>