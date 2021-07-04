<%@page import="model.User_"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<style type="text/css">
	.nav-link:hover{
	text-decoration: underline;
	}
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<%
			User_ u = (User_) request.getSession().getAttribute("user");
			if (u == null) {
			%>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/H">Home</a></li>
			</ul>
			<ul class="navbar-nav inline my-2 my-lg-0">
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/login">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/register">Register</a></li>
			</ul>
			<%
			} else {
			if (u.getAdmiN()==true) {
			%>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/H">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/UserServlet/home">Users
						Manager</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/QLSP/home">Product
						Manager</a></li>
				<li class="nav-item"><a class="nav-link" href="#">bill
						Manager</a></li>
			</ul>
			<ul class="navbar-nav inline my-2 my-lg-0">
				<li class="nav-item"><a class="nav-link">Xin Chào <%=u.getFullname()%></a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/logout">Logout</a></li>
			</ul>
			<%
			} else {
			%>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/H">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/giohang">Giỏ hàng</a></li>
				<li class="nav-item"><a class="nav-link"href="<%=request.getContextPath()%>/Call/changeUser">Change
						Info</a></li>
			</ul>
			<ul class="navbar-nav inline my-2 my-lg-0">
				<li class="nav-item"><a class="nav-link">Xin Chào <%=u.getFullname()%></a></li>
				<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/logout">Logout</a></li>
			</ul>

			<%
			}
			}
			%>
		</div>
	</nav>
</body>
</html>