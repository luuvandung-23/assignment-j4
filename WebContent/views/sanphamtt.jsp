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
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
</head>
<body>
	<section class="row">
		<div class="col-9">
			<div class="row p-2">
				<c:forEach var="item" items="${listsp}">
					<div class="col-3 mt-2">
						<div class="card text-center">
							<div class="card-body">
								<img src="${item.anh}" width="200px" height="200px" alt="" class="img-fluid">
								<div class="row border-top mt-2">
									<b>${item.tensp}</b>
									<p>${item.gia}</p>
								</div>
							</div>
							<div class="card-footer">
								<a href="<%=request.getContextPath()%>/Call/ttsp?msp=${item.idSp}" class="btn btn-success">Mua</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/Call/H?stt=pre">Previous</a></li>
							<c:forEach var="item" items="${sotrang}">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/Call/H?st=${item-1}">${item}</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/Call/H?stt=next">Next</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="row mt-3 mb-3">
				<div class="col">
					<div class="card">
					<div class="card-header">
					<i class="far fa-thumbs-up">Danh mục</i>
					</div>
					<ul class="list-group list-group-flush">
					<li class="list-group-item"><a href="<%=request.getContextPath()%>/Call/H?idDm=">Tất cả</a></li>
						<c:forEach var="item" items="${listdm}">
						<li class="list-group-item"><a href="<%=request.getContextPath()%>/Call/H?idDm=${item.idDm}">${item.tendm}</a></li>
						</c:forEach>
					</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>