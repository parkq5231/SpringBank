<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${list}
	<c:forEach items="${list}" var="board">
		<br>
		${board.seq}<br>
		${board.title}<br>
		${board.writer}<br>
		${board.fileName}<br>
		${board.uploadFile}<br>
		<hr>
	</c:forEach>
</body>
</html>