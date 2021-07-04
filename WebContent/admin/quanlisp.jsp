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
	<c:if test="${not empty message_qlsp}">
	<div class="alert alert-success">${message_qlsp}</div>
	</c:if>
		<c:if test="${not empty error_qltk}">
	<div class="alert alert-danger">${error_qlsp}</div>
	</c:if>
	</div>
	</div>
	<div class="row">
		<div class="col">
		<form action="Assigment/QLSP" method="post">
		<div class="form-group">
		<label for="userid">PRODUCT ID:</label>
		<input type="text" name="idSp" id="idSp" class="form-control" value="${sp.idSp}">
		</div>
		<div class="form-group">
		<lable for="category">Category:</lable>
		<select id="idDmSelect" name="idDmSelect">
		<c:forEach var="item" items="${category}">
			<option value="${item.idDm}" <c:if test="${item.idDm==dm}">selected</c:if> >${item.tendm}</option>
		</c:forEach>
		</select>
		</div>
		<div class="form-group">
		<label for="tensp">Product's name:</label>
		<input type="text" name="tensp" id="tensp" class="form-control" value="${sp.tensp}">
		</div>
		<div class="form-group">
		<label for="gia">Price:</label>
		<input type="text" name="gia" id="gia" class="form-control" value="${sp.gia}">
		</div>
		<div class="form-group">
		<label for="anh">Image:</label>
		<input type="text" name="anh" id="anh" class="form-control" value="${sp.anh}">
		</div>
		<div class="form-group">
		<label for="kg">Weight:</label>
		<input type="text" name="kg" id="kg" class="form-control" value="${sp.kg}">
		</div>
		<div class="form-group">
		<label for="sl">Amount:</label>
		<input type="text" name="soluong" id="soluong" class="form-control" value="${sp.soluong}">
		</div>
		<div class="form-group">
		<label for="sl">Note:</label>
		<textarea name="mieuta" id="mieuta" class="form-control" rows="4" cols="50" >${sp.mieuta}</textarea>
		</div>
		<div class="form-group">
		<button class="btn btn-primary" formaction="<%=request.getContextPath()%>/QLSP/create">Create</button>
		<button class="btn btn-warning" formaction="<%=request.getContextPath()%>/QLSP/update">Update</button>
		<button class="btn btn-info" formaction="<%=request.getContextPath()%>/QLSP/reset">Reset</button>
		</div>
		</form>
		</div>
	</div>
	<div class="row">
	<div class="col">
	<table class="table table-stripe">
	<tr>
	<th>Product ID</th>
	<th>Image</th>
	<th>Category</th>
	<th>Product's name</th>
	<th>Price</th>
	<th>Note</th>
	<th>Weight</th>
	<th>Amount</th>
	<th>Status</th>
	<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${products}">
	<tr>
		<td>${item.idSp}</td>
		<td><img alt="" width="200px" height="150px" src="${item.anh}"></td>
		<td>${item.danhmuc.idDm}</td>
		<td>${item.tensp}</td>
		<td>${item.gia}</td>
		<td>${item.mieuta}</td>
		<td>${item.kg}</td>
		<td>${item.soluong}</td>
		<td>${item.trangthai?'Đang bán':'Ngừng bán'}</td>
		<td>
		<a href="<%=request.getContextPath()%>/QLSP/edit?idSp=${item.idSp}">Edit</a>
		<c:if test="${item.trangthai==true}">
		<a href="<%=request.getContextPath()%>/QLSP/disable?idSp=${item.idSp}">Disable</a>
		</c:if>
		<c:if test="${item.trangthai==false}">
		<a href="<%=request.getContextPath()%>/QLSP/disable?idSp=${item.idSp}">Active</a>
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