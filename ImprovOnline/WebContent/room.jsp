<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import = "models.ChatRoom"%>
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
		<link rel="stylesheet" type="text/css" href="css/room.css">
		
		<style type="text/css">
			textarea, input{
				border: 1px solid;
			}
			body, divbody{
				overflow:hidden;
			}
		</style>
		<script src="js/jquery-2.1.1.js"></script>
		<script type="text/javascript">
			function hide(){
				$('.popup').hide();
				sendToServer();
			}
			
			function spectate(){
				$('.popup').hide();
				$('#settings').hide();
			}
			
			function hideSettings(){
				$('#settingspopup').hide();
			}
			
			function showSettings(){
				$('#settingspopup').show();
			}
			
			$(document).ready(function hideSettings(){
				$('#settingspopup').hide();
			})
			
		
		</script>
	</head>
	<body onload="start()">
		<div style="text-align:center">
			<div id="horizontalbar">
				<div class="float-left">
					ImprovOnline
				</div>
				<div class="float-right">
					<c:out value="${sessionScope.user}"></c:out>
					<c:if test="${sessionScope.usertype == 'registered'}" >
					<a href="logout">Log Out</a>
					</c:if>
					<c:if test="${sessionScope.usertype == 'guest'}" >
					<a href="registration.jsp">Sign Up</a>
					<a href="login.jsp">Log in</a>
					</c:if>
				</div>
			</div>
		</div>
		
		<div id="divbody">
			<div id="lefty">
				<br>
				<span id="inlinedapat"><h5 id="roomTitle" name="roomTitle"> <c:out value="${chatRoom.name}"></c:out></h5><input type="button" class='btn' id="settings" value="Settings" onclick="showSettings()"></span>
				<div id="chatboxdiv">
					<textarea readonly="readonly" name="chatbox" id="chatbox"></textarea>
				</div>
				<input type="text" name="chatinput" id="chatinput">
				<input type="button" class="btn" id="sendbutton" value="Send" onclick="sendToServer()">
			</div>
			<div id="righty">
				<div id="upper-right">
					<center>
						<p> Time Remaining:</p>
						<h2> 1:00 </h2>
					</center>	
				</div>
				<div id="lower-right">
					<p>
						Current Game: <c:out value="${chatRoom.game}"></c:out><br>
					</p>
					<hr>
					<p>
						Players: (3)<br><br>
						fallfaeryx,	notgracehelbig,	frozenflames
					</p>
					<hr>
						<p>Spectators: (0)</p><br><br>
					
						Prompt: <c:out value="${prompt}"></c:out>
				</div>
				
			</div>
		</div>
		<span id="popup" class="popup"><center>
			<br><br><br>
			<table>
			<tr>
				<td colspan='2'><textarea id="instructions" readonly='readonly'>
		<c:out value="${param['roomGame']}"></c:out> 
		<c:out value="${requestScope.instructions}"></c:out> 
			</textarea></td>
			</tr>
			<tr>
				<td><center><input type="button" class="btn" value="Play" id="play" onclick="hide()"></center></td>
				<td><center><input type="button" class="btn" value="Spectate" id="spectate" onclick="spectate()"></center></td>
			</tr>
			</table>
			</center>
		</span>
		<span id="settingspopup">
			<center>
			<br><br>
			<h2> Settings </h2>
			<br><br>
			Game Time: 
			<select>
				<option>1:00</option>
				<option>2:00</option>
				<option>3:00</option>
				<option>4:00</option>
			</select>
			<br><br><br>
			Game Type: 
			<select>
				<option>Word Association</option>
				<option>Yes, And...</option>
				<option>Alphabet Game</option>
				<option>Cards</option>
				
			</select>
			<br><br><br>
			<input type="button" class="btn" value="Ok" id="okSettings" onclick="hideSettings()">
			</center>
		</span>
		
		<p align="right" id="footer"> Created by: Kate Lacsamana, MG Malana, Albert Rivera &copy; 2015</p>
	</body>
	<script>
		var xmlObject;
		var xmlObjectRefresh;
		var myVar;
		
		
		
		function start(){
			if(window.XMLHttpRequest){
				xmlObject = new XMLHttpRequest();
				xmlObjectRefresh = new XMLHttpRequest();
			}
			else if(window.ActiveXObject){
				xmlObject = new ActiveXObject("MICROSOFT.XMLHTTP");
				xmlObjectRefresh = new ActiveXObject("MICROSOFT.XMLHTTP");
			}
			refresh();
		}
		
		
		function sendToServer(){
			var request = "GET";
			var url = "chatroom?text=" + document.getElementById("chatinput").value + "&idchat=" + '<c:out value="${idchat}"></c:out>';;
			var isAsynchronous = true;
			
			xmlObject.open(request, url, isAsynchronous);
			xmlObject.onreadystatechange = receiveFromServer;
			xmlObject.send(null);
		}
		
		function receiveFromServer(){
			switch(xmlObject.readyState){
				case 4:
					if(xmlObject.status == 200){
						document.getElementById("chatinput").value = "Yes, And";
					}
				break;
			}
		}
		
		function refresh(){
			myVar = setInterval(sendToServerRefresh, 1000);
		}
		
		function sendToServerRefresh(){
			var request = "GET";
			var url = "chatroomRefresh?idchat=" + '<c:out value="${idchat}"></c:out>';
			var isAsynchronous = true;
			xmlObjectRefresh.open(request, url, isAsynchronous);
			xmlObjectRefresh.onreadystatechange = receiveFromServerRefresh;
			xmlObjectRefresh.send(null);
		}
		
		function receiveFromServerRefresh(){
			switch(xmlObjectRefresh.readyState){
			case 4:
				if(xmlObjectRefresh.status == 200){
					var out = "";
					var obj = JSON.parse(xmlObjectRefresh.responseText);
					for(i = 0; i < obj.messages.length; i++)
						out += obj.messages[i].username +": "+ obj.messages[i].message+"\n";
					//// you can edit the style or something if you want	
					document.getElementById("chatbox").innerText = out;

					
				}
			break;
		}
			
		}		
	
	</script>
	
</html>