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
<body>
<section class="row">
		<div class="col-4 mt-3" >
			<div class="row p-2">
			<img alt="" src="${sp.anh}" >
</div>
</div>
<div class="col-6 ml-4">
		<div class="row mt-3 ">
		 <form class="form" action="Assignment/Call" method="post">
                            <h3 class="text-center text-info">Product information</h3>
                            <div class="form-group">
		<label for="userid">PRODUCT ID:</label>
		<input type="text" name="idSp" id="idSp" class="form-control" disabled="disabled" value="${sp.idSp}">
		</div>
		<div class="form-group">
		<label for="tensp">Product's name:</label>
		<input type="text" name="tensp" id="tensp" class="form-control" disabled="disabled" value="${sp.tensp}">
		</div>
		<div class="form-group">
		<label for="gia">Price:</label>
		<input type="text" name="gia" id="gia" class="form-control" disabled="disabled" value="${sp.gia}">
		</div>
		<div class="form-group">
		<label for="kg">Weight:</label>
		<input type="text" name="kg" id="kg" class="form-control" disabled="disabled" value="${sp.kg}">
		</div>
		<div class="form-group">
		<label for="sl">Remaining:</label>
		<input type="text" name="soluong" id="soluong" class="form-control" disabled="disabled" value="${sp.soluong}">
		</div>
		<div class="form-group">
		<label >Số lượng:</label><br>
         <input type="number" name="so" min="1" max="${sp.soluong}" value=1>
         </div>
		<div class="form-group">
		<label for="sl">Note:</label>
		<textarea name="mieuta" id="mieuta" class="form-control" rows="4" cols="50" disabled="disabled" >${sp.mieuta}</textarea>
		</div>
		<div class="form-group">
        <input type="submit" name="submit" class="btn btn-danger btn-md" formaction="<%=request.getContextPath()%>/Call/giohang" value="Thêm vào giỏ">
        </div>
        </form>
		</div>
		</div>
</section>
</body>
</html>