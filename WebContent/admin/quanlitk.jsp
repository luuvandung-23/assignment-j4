<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <title>Hello, world!</title>
  </head>	
  <body>
	<main class="container">
	<div class="row">
	<div class="col">
	<c:if test="${not empty message_qltk}">
	<div class="alert alert-success">${message_qltk}</div>
	</c:if>
		<c:if test="${not empty error_qltk}">
	<div class="alert alert-danger">${error_qltk}</div>
	</c:if>
	</div>
	</div>
	<div class="row">
		<div class="col">
		<form action="Assigment/UserServlet" method="post">
		<div class="form-group">
		<label for="userid">User ID:</label>
		<input type="text" name="idUser" id="idUser" class="form-control" value="${user_qltk.idUser}">
		</div>
		<div class="form-group">
		<lable for="password">Password:</lable>
		<input type="password" class="form-control" name="passworD" id="passworD" value="${user_qltk.passworD}">
		</div>
		<div class="form-group">
		<label for="fullname">Fullname:</label>
		<input type="text" name="fullname" id="fullname" class="form-control" value="${user_qltk.fullname}">
		</div>
		<div class="form-group">
		<label for="fullname">Địa chỉ:</label>
		<input type="text" name="diachi" id="diachi" class="form-control" value="${user_qltk.diachi}">
		</div>
		<div class="form-group">
		<label for="email">Email Address:</label>
		<input type="text" name="email" id="email" class="form-control" value="${user_qltk.email}">
		</div>
		<div class="form-check">
		<label>Role:&ensp;</label>
		<input type="radio"  id="admiN" name="admiN" value="true" ${user_qltk.admiN?'checked':'' }>Admin
		<input type="radio" id="user" name="admiN" value="false"  ${!user_qltk.admiN?'checked':'' }>User
		</div>
		<div class="form-group">
		<button class="btn btn-primary" formaction="<%=request.getContextPath()%>/UserServlet/create">Create</button>
		<button class="btn btn-warning" formaction="<%=request.getContextPath()%>/UserServlet/update">Update</button>
		<button class="btn btn-info" formaction="<%=request.getContextPath()%>/UserServlet/reset">Reset</button>
		</div>
		</form>
		</div>
	</div>
	<div class="row">
	<div class="col">
	<table class="table table-stripe">
	<tr>
	<th>User ID</th>
	<th>Fullname</th>
	<th>Email</th>
	<th>Role</th>
	<th>Địa chỉ</th>
	<th>Trạng thái</th>
	<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${users}">
	<tr>
		<td>${item.idUser}</td>
		<td>${item.fullname}</td>
		<td>${item.email}</td>
		<td>${item.admiN?'Admin':'User'}</td>
		<td>${item.diachi}</td>
		<td>${item.trangthai?'Hoạt động':'Tạm ngừng'}</td>
		<td>
		<a href="<%=request.getContextPath()%>/UserServlet/edit?idUser=${item.idUser}">Edit</a>
		<c:if test="${item.trangthai==true}">
		<a href="<%=request.getContextPath()%>/UserServlet/disable?idUser=${item.idUser}">Disable</a>
		</c:if>
		<c:if test="${item.trangthai==false}">
		<a href="<%=request.getContextPath()%>/UserServlet/disable?idUser=${item.idUser}">Active</a>
		</c:if>	
		</td>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	</main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
  </body>
</html>