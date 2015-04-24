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
			$(document).ready(function hideSettings(){
				$('#settingspopup').hide();
			})		
			
			function hideInstructions(){
				$('.popup').hide();
			}
			
			function showInstructions(){
				$('.popup').show();
			}
			
			
			function hideButtonSettings(){
				$('#settings').hide();
			}
			
			function hideButtonStart(){
				$('#startbutton').hide();
				
			}
			
			function showButtonStart(){
				$('#startbutton').show();
				
			}
			
			function showButtonSettings(){
				$('#settings').show();
			}
			
			function hideSettings(){
				$('#settingspopup').hide();
				updateSettings();
			}
			
			function showSettings(){
				$('#settingspopup').show();
			}
			
			
			function handle(e){
				if(e.keyCode === 13){
					sendToServer();
				}
			}
			
			
			
		</script>
	</head>
	<body onload="start()" onbeforeunload="exituser()">
	<div id="headwrapper">
		<div style="text-align:center">
			<div id="horizontalbar">
				<div class="float-left">
					<a href="index">ImprovOnline</a>
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
		<div id="spacer">
		</div>
		
		<input type = "hidden" id = "hiddenusertype" value = "<c:out value="${param['gametype']}"></c:out>">
								
		
		
		
		<div id="divbody" style="margin-top: 1%">
			<div id="lefty">
				<br>
				<span id="inlinedapat"><h5 id="roomTitle" name="roomTitle"> Room: <c:out value="${chatRoom.name}"></c:out></h5></span>
				<div id="chatboxdiv">
					<textarea readonly="readonly" name="chatbox" id="chatbox"></textarea>
				</div>
			
				<input type="text" name="chatinput" id="chatinput" onkeypress="handle(event)" maxlength="60">
				<input type="button" class="btn" id="sendbutton" value="Send" onclick="sendToServer()">
			
			</div>
			<div id="righty">
				<div id="upper-right" style="text-align: center">
					
						<p> Time Remaining </p>
						<h2 id ="timeleft" style="display:inline"></h2>
				</div>
				
				<div id="lower-right">
					<div style="text-align:center; margin: 15px">
						<p style="font-size: 20px; display:inline; text-decoration:underline "><span id = "currentlyplaying"><c:out value="${chatRoom.game}"></c:out></span></p><br>
						<p style="font-size: 12px; display:inline">Current Game</p>
						
					</div>
					<div id="mid-right" style="text-align: center">
						<c:if test="${(param['gametype']) == 'play'}" >
							<input type="button" class="btn" id="startbutton" value="Start" onclick="startgame()">
							<input type="button" class='btn' id="settings" value="Settings" onclick="showSettings()">
						</c:if>
						<c:if test="${(param['gametype']) == 'spectate'}" >
							<input type="button" class="btn" id="suggestbutton" value="Suggest Prompt" onclick="suggestPrompt()">
						</c:if>
						<input type="button" class="btn" id="Instructionsbutton" value="Instructions" onclick="showInstructions()">
					</div>
					<hr>
					
						<p style="display:inline"><b>Players</b> (<p id="numplayers" style="display:inline"></p><p style="display:inline">):</p> </p>
						<p id = "players" style="display:inline"></p>
					
					<hr>
						<p><b>Spectators</b> (<span id ="numspectators"></span>): </p>
						<p id = "spectators" style="display:inline"></p>
					<hr>
						<span id="prompty">Prompt: <span id="gameprompt"></span></span>
						<div id= "userturn"></div>
						<div id = "currenty">Current Letter: <span id="currentLetter"></span></div>					
						
				</div>
				
			</div>
		</div>
		<div id="popup" class="popup">
			<br><br>
			<table class="popuptable" border="0">
			<tr>
				<td colspan='2' style="text-align: center">
					<textarea id="instructions" readonly='readonly'>
						<c:out value="${param['roomGame']}"></c:out> 
						<c:out value="${requestScope.instructions}"></c:out> 
					</textarea>
					<br>
					<input type="button" class="btn" value="Continue" id="play" onclick="hideInstructions()">
				</td>
			</tr>

			</table>
			
		</div>
		<div id="settingspopup" style="text-align: center">
			
			<br><br>
			<h2> Settings </h2>
			<br><br>
			Game Time: 
			<select id = "changegametime">
				<option>1:00</option>
				<option>2:00</option>
				<option>3:00</option>
				<option>4:00</option>
			</select>
			<br><br><br>
			Game Type: 
			<select id = "changegametype">
				<option>Word Association</option>
				<option>Yes, And</option>
				<option>Alphabet Game</option>
				<option>Cards</option>
				
			</select>
			<br><br><br>
			<input type="button" class="btn" value="Ok" id="okSettings" onclick="hideSettings()">
			
		</div>
		</div>
		<div id="footwrapper">
			<p align="right" id="footer"> Created by: Kate Lacsamana, MG Malana, Albert Rivera &copy; 2015</p>
		</div>
	</body>
	<script>
		var xmlObject;
		var xmlObjectRefresh;
		var exitObject;
		var changeGame;
		var myVar;
		var myVar1;
		var lastmessage = 0;
		var isStartButtonPressed = false;
		var currentGame = '<c:out value="${param['roomGame']}"></c:out>';
		function suggestPrompt(){
			var suggestion = prompt("Please enter a prompt suggestion: ", "");
			var request = "GET";
			var url = "suggestPrompt?prompt="+suggestion;
			var isAsynchronous = true;
			

			
			
			changeGame.open(request, url, isAsynchronous);  ///reuse this object :)))
			changeGame.send(null);
		}
		
		
		function updateCSS(){
			if(currentGame == 'Word Association')
				document.getElementById("prompty").style.display = "none";
			else
				document.getElementById("prompty").style.display = "block";
			if(currentGame != 'Alphabet Game')
				document.getElementById("currenty").style.display = "none";
			else
				document.getElementById("currenty").style.display = "block";
			
			document.getElementById("currentlyplaying").innerHTML = currentGame;
			
		}
		
		
		function start(){
			if(window.XMLHttpRequest){
				xmlObject = new XMLHttpRequest();
				xmlObjectRefresh = new XMLHttpRequest();
				exitObject = new XMLHttpRequest();
				changeGame = new XMLHttpRequest();
			}
			else if(window.ActiveXObject){
				xmlObject = new ActiveXObject("MICROSOFT.XMLHTTP");
				xmlObjectRefresh = new ActiveXObject("MICROSOFT.XMLHTTP");
				exitObject = new ActiveXObject("MICROSOFT.XMLHTTP");
				changeGame = new ActiveXObject("MICROSOFT.XMLHTTP");
			}
			refresh();
			updateCSS();
		}
		
		function startgame(){
			isStartButtonPressed = true;
			refreshPrompt();
			
		}
		
		
		function sendToServer(){
			if(document.getElementById("chatinput").value.length > 0){
				var request = "GET";
				var url = "chatroom?text=" + document.getElementById("chatinput").value + "&idchat=" + '<c:out value="${idchat}"></c:out>';
				var isAsynchronous = true;
				
				xmlObject.open(request, url, isAsynchronous);
				xmlObject.onreadystatechange = receiveFromServer;
				xmlObject.send(null);
			}
		}
		
		
		function receiveFromServer(){
			switch(xmlObject.readyState){
				case 4:
					if(xmlObject.status == 200){
						if(lastmessage == 0){
							chatbox = document.getElementById("chatbox");
							var temp = "";
							if(document.getElementById("hiddenusertype").value == "spectate")
								temp += "[spectator] ";
							temp += "<c:out value="${sessionScope.user}"></c:out>";
							temp += ": " + document.getElementById("chatinput").value;
							chatbox.innerHTML = temp;
						}
						if(currentGame == 'Yes, And')
							document.getElementById("chatinput").value = "Yes, And";
						else
							document.getElementById("chatinput").value = "";
						chatbox.scrollTop = chatbox.scrollHeight;
						
					}
				break;
			}
		}

		function refresh(){
			myVar = setInterval(sendToServerRefresh, 1000);
		}
		
		function refreshPrompt(){
			if(currentGame == 'Cards')
				myVar1 = setInterval(changePrompt, 10000);
		}
		
		
		function sendToServerRefresh(){
			var request = "GET";
			var url = "chatroomRefresh?idchat=" + '<c:out value="${idchat}"></c:out>' + "&lastmessage=" + lastmessage + "&ispressed=" + isStartButtonPressed;
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
					for(i = 0; i < obj.messages.length; i++){
						
						//this long if is for the spectatorss
						var playertemplist = "" + document.getElementById("players").innerHTML;
						var temp = "";
						if(document.getElementById("numplayers").innerHTML > 1){
							if(playertemplist.indexOf(obj.messages[i].username + ",") == -1 &&
									(playertemplist.indexOf(", " + obj.messages[i].username)) == -1) 
								temp = "[spectator] ";
						} else {
							if(playertemplist.indexOf(obj.messages[i].username) == -1)
								temp = "[spectator] ";
						}
						out += temp + obj.messages[i].username +": "+ obj.messages[i].message+"\n";
					}
					//// you can edit the style or something if you want	
					chatbox = document.getElementById("chatbox");
					chatbox.innerHTML = chatbox.value + out;

					////the users
					out = "";
					for(i = 0; i < obj.players.length; i++)
						out += obj.players[i].username +", ";
					if(out.length > 2)
						out = out.substring(0, out.length - 2);
					document.getElementById("players").innerHTML = out;
					document.getElementById("numplayers").innerHTML = obj.players.length;

					out = "";
					for(i = 0; i < obj.spectators.length; i++)
						out += obj.spectators[i].username +", ";	
					document.getElementById("spectators").innerHTML = out;
					document.getElementById("numspectators").innerHTML = obj.spectators.length;
					
					//change the lastmessage id
					lastmessage = obj.lastmessage[0].message;
					
					isStartButtonPressed = false;
					
					if(obj.hasOwnProperty("gameHasStarted")){
						hideButtonSettings();
						hideButtonStart();
						document.getElementById("gameprompt").innerHTML = obj.gameHasStarted[0].prompt;
						document.getElementById("timeleft").innerHTML = obj.gameHasStarted[0].timeleft;
						if(currentGame != 'Cards'){
							if(obj.gameHasStarted[0].usernameturn.length < 2){
								document.getElementById("userturn").innerHTML = "";
							}
							else{
								if("<c:out value="${sessionScope.user}"></c:out>" == obj.gameHasStarted[0].usernameturn)
									document.getElementById("userturn").innerHTML = "<b>Your turn!</b>";
								else
									document.getElementById("userturn").innerHTML = "<b>"+obj.gameHasStarted[0].usernameturn+"'s turn</b>";
	

							}
						} 
						if(currentGame == 'Alphabet Game'){
							document.getElementById("currentLetter").innerHTML = obj.gameHasStarted[0].currentLetter;
						}
					}else{
						showButtonSettings();
						showButtonStart();
					}
					
					currentGame = obj.gametype;
					updateCSS();
				}
			break;
		}
			
		}		
		
		function exituser(){
			var request = "GET";
			var url = "exituser?idchat=" + '<c:out value="${idchat}"></c:out>';
			var isAsynchronous = false;
			
			exitObject.open(request, url, isAsynchronous);
			exitObject.send(null);
		}
		
		function changePrompt(){
			var request = "GET";
			var url = "changePrompt?idchat=" + '<c:out value="${idchat}"></c:out>';
			var isAsynchronous = true;
			
			exitObject.open(request, url, isAsynchronous);
			exitObject.send(null);
		}
	
		function updateSettings(){
			var request = "GET";
			var getSelected = $('#changegametime').find(":selected").text();
			
			var getgametype = $('#changegametype').find(":selected").text();
			var url = "updateSettings?idchat=" + '<c:out value="${idchat}"></c:out>'+"&newtime=" + getSelected+"&newgametype=" + getgametype;
			var isAsynchronous = true;
			
			xmlObject.open(request, url, isAsynchronous);
			xmlObject.send(null);
		}
	</script>
	
</html>