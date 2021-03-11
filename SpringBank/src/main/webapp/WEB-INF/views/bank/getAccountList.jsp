<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getAccountList.jsp</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
				<%-- <td><a href="getBalance?fintech_use_num=${bank.fintech_use_num}">${bank.fintech_use_num}</a></td> --%>
				<td><a onclick="getBalance('${bank.fintech_use_num}')">${bank.fintech_use_num}</a></td>
				<td><a onclick="getBalance2('${bank.fintech_use_num}')">${bank.fintech_use_num}</a></td>
				<td>${bank.bank_name}</td>
				<td>${bank.account_num_masked}</td>
				<td>${bank.account_holder_name}</td>
			</tr>
		</c:forEach>
	</table>
	<div id="result"></div>
	<script>
		function getBalance(p) {
			//ajax
			$.ajax({
				url : "getBalance",
				data: {fintech_use_num:p},
				success : function(response) {
					$("#result").html(response);
				}
			})//ajax
		}//end of function
		function getBalance2(p) {
			//ajax
			$.ajax({
				url : "ajaxGetBalance",
				data: {fintech_use_num:p},
				dataType : 'json',
				success : function(response) {
				$("#result").empty();
					$("#result").append("은행: " + response.bank_name + "<br>")
					.append("계좌명: " + response.product_name + "<br>")
					.append("잔액: " + response.balance_amt + "<br>")
					.append("available_amt: "+ response.available_amt + "<hr>");
				}
			})//ajax
		}//end of function
	</script>
</body>
</html>