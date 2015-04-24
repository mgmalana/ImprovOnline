<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ImprovOnline | Register</title>
	<link rel="stylesheet" type="text/css" href="css/global.css">
	<link rel="stylesheet" type="text/css" href="css/button.css">
	<link rel="stylesheet" type="text/css" href="css/textbox.css">
	<link rel="stylesheet" type="text/css" href="css/errortext.css">
   	<link rel="stylesheet" type="text/css" href="css/header.css">		
	
	<script src="js/jquery-2.1.1.js"></script>
	<style type="text/css">
		input{
			width: 100%;
		}
	</style>
	<script type="text/javascript">
		function checkOthers(){
			var txtbox = $("#securitycustom")[0];
			var len = $('#selectQuestions option').size() - 1; 
			var sel = $('#selectQuestions')[0].selectedIndex;
			if(len==sel){
				txtbox.type = "text";
				txtbox.required = true;
			}else{
				txtbox.type = "hidden";
			}
		}
		
		function checkFormEntries(){
			var flag = true;
			var password1 = document.getElementsByName("password1")[0];
			var password2 = document.getElementsByName("password2")[0];

			password1.id = "noerror";
			password2.id = "noerror";

			if(password1.value!=password2.value){
				password1.id = "error";
				password2.id = "error";
				flag = false;
			}
			return flag;
		}
	</script>
</head>
<body>
<center>
	<div id="logo">
			<div id="horizontalbar">
				<div class="float-left">
					<a href="index">ImprovOnline</a>
				</div>
				
			</div>
	
		<h1>ImprovOnline</h1>
		<form action="registration" method="POST" onSubmit = "return checkFormEntries()">
		<p> <font color = "red"> ${error } </font> </p>
		<table>
				<tr> <td>Username: </td> <td> <input type="text" name="username" class="textbox" required> </td></tr>
				<tr> <td>Password: </td> <td> <input type="password" name="password1" class="textbox" required> </td></tr>
				<tr> <td>Confirm Password: </td> <td> <input type="password" name="password2" class="textbox" required> </td></tr>
				<tr> <td>Security Question: </td>
				<td>
					<select onChange="checkOthers()" id="selectQuestions" name ="question">  
					<option> What was the name of your first pet?</option>
					<option> What was your mother's maiden name?</option>
					<option> What is the favorite color of your favorite professor? </option>
					<option> Others </option>
				</select><br>
					<input type="hidden" id="securitycustom" class="textbox" name="question">
				</td>
				</tr>
				<tr><td> Answer: </td><td><input type="text" name="answer" class="textbox" required></td></tr>
				<tr><td colspan="2"><br><input type="submit" class="btn" value="Submit Answer"></td></tr>
		</table>
		</form>
	</div>
</center>
</body>
</html>