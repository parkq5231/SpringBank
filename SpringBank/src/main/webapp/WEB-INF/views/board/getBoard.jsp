<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getBoard</title>
</head>
<body>
	<h3>게시글 상세보기</h3>
		번호:${board.seq}<br>
		제목:${board.title}<br>
		작성자:${board.writer}<br>
		첨부파일:<a href="fileDown?seq=${board.seq}">${board.fileName}</a><br>
</body>
</html>
