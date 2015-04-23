<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<title>
			ImprovOnline
		</title>
		<link rel="stylesheet" type="text/css" href="css/header.css">
		<link rel="stylesheet" type="text/css" href="css/global.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		<link rel="stylesheet" type="text/css" href="css/textbox.css">
		
		
		<script>
			function userlogin(){
				document.getElementsByName("usertype")[0].value = "registered";
			}
			
			function userplayasguest(){
				document.getElementsByName("usertype")[0].value = "guest";
			}
		</script>
	</head>
	<body>
		<div style="text-align:center">
		<div id="horizontalbar">
			<div class="float-left">
				<a href="index">ImprovOnline</a>
			</div>
			<div class="float-right">
				<a href="registration.jsp">Sign Up</a>
			</div>
		</div>
		
		
		<div id="logo">
					<h1>ImprovOnline</h1>
					<form action="login" method="post">
						<p> <font color = "red"> ${error } </font> </p>
						<p> Username: <input type = "text" name="username" class="textbox"> </p>
						<p> Password: <input type = "password" name="password" class="textbox"> </p>
						<p> <a href="forgotpassword.jsp" id="forgotpass">Forgot Password?</a></p>
						<input type = "hidden" name = "usertype">
						<input type="submit" class="btn" value="Login" onclick="userlogin();">
						<input type="submit" class="btn" value="Play as Guest" onclick="userplayasguest();">
					</form>
						<br>
						<p> <font color = "red"> ${error2 } </font> </p>
				</div>
		
		
		
		</div>
		<p align="right" id="footer"> Created by: Kate Lacsamana, MG Malana, Albert Rivera &copy; 2015</p>
	</body>
</html>