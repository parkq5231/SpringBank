<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>게시판 목록</div>
	<form action="getSearchBoard" id="searchFrm" name="searchFrm">
		<input type="hidden" name="page" value="1"> 번호: <input
			name="seq" value="${board.seq}">
		<button>검색</button>
	</form>

	<c:forEach items="${list}" var="board">
		<br>
		${board.seq}<br>
		${board.title}<br>
		${board.writer}<br>
		${board.fileName}<br>
		${board.uploadFile}<br>
		<hr>
	</c:forEach>
	<my:paging paging="${paging}" jsFunc="goPage" />
	<script>
		function goPage(p) {
			searchFrm.page.value = p;
			searchFrm.submit();
		}
	</script>
</body>
</html>