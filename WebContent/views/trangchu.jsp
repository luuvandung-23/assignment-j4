
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Hello, world!</title>
</head>
<header class="row">
		<body>
	<main class="container">
			<div class="col-9 pt-5">
				<h1>Online Entertainment</h1>
			</div>
			<div class="col-3 text-right">
				<img
					src="https://gudlogo.com/wp-content/uploads/2019/04/logo-hinh-vien-kim-cuong-35.png"
					alt="" class="mr-4" width="300px" height="200px">
			</div>
		</header>
		<jsp:include page="navbar.jsp" />
		<jsp:include page="${trang}" >
		<jsp:param name="listsp" value="${listsp}"/>
		</jsp:include>
		<footer class="row">
</footer>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
</body>
</html>