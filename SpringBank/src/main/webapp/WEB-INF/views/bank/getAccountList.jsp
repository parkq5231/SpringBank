<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getAccountList.jsp</title>

</head>
<body>
	<!-- 하위 태그 클릭시 상위태그 포함하도록 script -->
	<table border="1">
		<tr>
			<th>핀테크번호</th>
			<th>계좌명</th>
			<th>계좌번호</th>
			<th>사용자</th>
		</tr>
		<c:forEach items="${list.res_list}" var="bank">
			<tr>
				<td><a href="getBalance?fintech_use_num=${bank.fintech_use_num}">${bank.fintech_use_num}</a></td>
				<td>${bank.bank_name}</td>
				<td>${bank.account_num_masked}</td>
				<td>${bank.account_holder_name}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- <script>
		function search(p) {
			location.href = "getBalance?fintech_user_num=" + p;
		}
	</script> -->
</body>
</html>