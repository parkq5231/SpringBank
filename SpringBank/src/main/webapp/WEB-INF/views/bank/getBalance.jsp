<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>잔액조회</h2>
	<hr> 은행: ${balance.bank_name}
	<br> 계좌명: ${balance.product_name}
	<br> 잔액: ${balance.balance_amt}
	<br> 계좌종류: ${balance.account_type}
	<br> 계좌개설일: ${balance.account_issue_date}
	<br> 만기일: ${balance.maturity_date}
	<br> 최종거래일: ${balance.last_tran_date}
	<br>${balance}
</body>
</html>