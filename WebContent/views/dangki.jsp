<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style type="text/css">
	body {
  margin: 0;
  padding: 0;
  height: 100vh;
}
#login .container #login-row #login-column #login-box {
  margin-top: 40px;
  max-width: 600px;
  height: 670px;
  border: 1px solid #9C9C9C;
  background-color: #EAEAEA;
}
#login .container #login-row #login-column #login-box #login-form {
  padding: 20px;
}
#login .container #login-row #login-column #login-box #login-form #register-link {
  margin-top: -85px;
}
</style>
</head>
<body>
    <div id="login">
        <div class="container">
         <c:if test="${not empty message}">
	<div class="alert alert-success">${message}</div>
	</c:if>
		<c:if test="${not empty error}">
	<div class="alert alert-danger">${error}</div>
	</c:if>
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="Assignment/Call" method="post">
                            <h3 class="text-center text-info">Registration</h3>
                            <div class="form-group">
                                <label  class="text-info">Username:</label><br>
                                <input type="text" name="idUser" id="idUser" class="form-control">
                            </div>
                            <div class="form-group">
                                <label  class="text-info">Password:</label><br>
                                <input type="password" name="passworD" id="passworD" class="form-control">
                            </div>
                             <div class="form-group">
                                <label class="text-info">Nhập lại Password:</label><br>
                                <input type="password" name="nlpassword" id="nlpassword" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="text-info">Fullname:</label><br>
                                <input type="text" name="fullname" id="fullname" class="form-control">
                            </div>
                            <div class="form-group">
                                <label  class="text-info">Email:</label><br>
                                <input type="text" name="email" id="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info">Địa chỉ:</label><br>
                                <input type="text" name="diachi" id="diachi" class="form-control">
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" formaction="<%=request.getContextPath()%>/Call/register" value="Create">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>