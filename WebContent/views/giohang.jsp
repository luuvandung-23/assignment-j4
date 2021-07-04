<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="row mb-4">
	<div class="col">
	<h3>${message}</h3>
	<h3>Giỏ hàng</h3>
	<table class="table table-stripe">
	<tr>
	<th>Product ID</th>
	<th>Image</th>
	<th>Product's name</th>
	<th>Price</th>
	<th>Amount</th>
	<th>Tổng tiền</th>
	<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${cart}">
	<tr>
		<td>${item.id}</td>
		<td><img alt="" width="200px" height="150px" src="${item.image}"></td>
		<td>${item.name}</td>
		<td>${item.price}</td>
		<td>${item.amount}</td>
		<td>${item.soluong}</td>
		<td>${item.price*item.amount}</td>
		<td>
		<a href="">Delete</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
	<th>Thành tiền :</th>
	<th>
	${thanhtien}
	</th>
	</tr>
	</table>
	<form method="post">
	<button class="btn btn-danger" formaction="<%=request.getContextPath()%>/Call/dathang">Đặt hàng</button>
	</form>
	</div>
	</div>
</body>
</html>