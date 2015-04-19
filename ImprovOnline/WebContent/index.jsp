<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<title>
			ImprovOnline
		</title>
        <link type="text/css" rel="stylesheet" href="css/mainroom.css" />
        <link type="text/css" rel="stylesheet" href="css/global.css" />
        <link rel="stylesheet" href="carousel/csss_engine1/style.css">
    	<link rel="stylesheet" type="text/css" href="css/header.css">		
		
		
	</head>
	<body>
		<div style="text-align:center">
			<div id="horizontalbar">
				<div class="float-left">
					ImprovOnline
				</div>
				<div class="float-right">
					<c:out value="${sessionScope.user}"></c:out>
					<c:if test="${sessionScope.usertype == 'registered'}" >
					<a href="logout.html">Log Out</a>
					</c:if>
					<c:if test="${sessionScope.usertype == 'guest'}" >
					<a href="register.html">Sign Up</a>
					<a href="login.html">Log in</a>
					</c:if>
				</div>
			</div>
			
			<div id="left-container">
				<div class='csslider1 autoplay '>
					<input name="cs_anchor1" id='cs_slide1_0' type="radio" class='cs_anchor slide' >
					<input name="cs_anchor1" id='cs_slide1_1' type="radio" class='cs_anchor slide' >
					<input name="cs_anchor1" id='cs_slide1_2' type="radio" class='cs_anchor slide' >
					<input name="cs_anchor1" id='cs_slide1_3' type="radio" class='cs_anchor slide' >
					<input name="cs_anchor1" id='cs_play1' type="radio" class='cs_anchor' checked>
					<input name="cs_anchor1" id='cs_pause1_0' type="radio" class='cs_anchor pause'>
					<input name="cs_anchor1" id='cs_pause1_1' type="radio" class='cs_anchor pause'>
					<input name="cs_anchor1" id='cs_pause1_2' type="radio" class='cs_anchor pause'>
					<input name="cs_anchor1" id='cs_pause1_3' type="radio" class='cs_anchor pause'>
					<ul>
						<li class="cs_skeleton"><img src="carousel/csss_images1/001.jpg" style="width: 100%;"></li>
						<li class='num0 img slide'> <img src='carousel/csss_images1/001.jpg' alt='001' title='001' /></li>
						<li class='num1 img slide'> <img src='carousel/csss_images1/002.jpg' alt='002' title='002' /></li>
						<li class='num2 img slide'> <img src='carousel/csss_images1/003.png' alt='003' title='003' /></li>
						<li class='num3 img slide'> <img src='carousel/csss_images1/004.jpg' alt='004' title='004' /></li>
					</ul>
					<div class='cs_description'>
						<label class='num0'><span class="cs_title"><span class="cs_wrapper">001</span></span></label>
						<label class='num1'><span class="cs_title"><span class="cs_wrapper">002</span></span></label>
						<label class='num2'><span class="cs_title"><span class="cs_wrapper">003</span></span></label>
						<label class='num3'><span class="cs_title"><span class="cs_wrapper">004</span></span></label>
					</div>
					<div class='cs_play_pause'>
						<label class='cs_play' for='cs_play1'><span><i></i><b></b></span></label>
						<label class='cs_pause num0' for='cs_pause1_0'><span><i></i><b></b></span></label>
						<label class='cs_pause num1' for='cs_pause1_1'><span><i></i><b></b></span></label>
						<label class='cs_pause num2' for='cs_pause1_2'><span><i></i><b></b></span></label>
						<label class='cs_pause num3' for='cs_pause1_3'><span><i></i><b></b></span></label>
						</div>
					<div class='cs_arrowprev'>
						<label class='num0' for='cs_slide1_0'><span><i></i><b></b></span></label>
						<label class='num1' for='cs_slide1_1'><span><i></i><b></b></span></label>
						<label class='num2' for='cs_slide1_2'><span><i></i><b></b></span></label>
						<label class='num3' for='cs_slide1_3'><span><i></i><b></b></span></label>
					</div>
					<div class='cs_arrownext'>
						<label class='num0' for='cs_slide1_0'><span><i></i><b></b></span></label>
						<label class='num1' for='cs_slide1_1'><span><i></i><b></b></span></label>
						<label class='num2' for='cs_slide1_2'><span><i></i><b></b></span></label>
						<label class='num3' for='cs_slide1_3'><span><i></i><b></b></span></label>
					</div>
					<div class='cs_bullets'>
						<label class='num0' for='cs_slide1_0'> <span class='cs_point'></span>
							<span class='cs_thumb'><img src='carousel/csss_tooltips1/001.jpg' alt='001' title='001' /></span></label>
						<label class='num1' for='cs_slide1_1'> <span class='cs_point'></span>
							<span class='cs_thumb'><img src='carousel/csss_tooltips1/002.jpg' alt='002' title='002' /></span></label>
						<label class='num2' for='cs_slide1_2'> <span class='cs_point'></span>
							<span class='cs_thumb'><img src='carousel/csss_tooltips1/003.png' alt='003' title='003' /></span></label>
						<label class='num3' for='cs_slide1_3'> <span class='cs_point'></span>
							<span class='cs_thumb'><img src='carousel/csss_tooltips1/004.jpg' alt='004' title='004' /></span></label>
					</div>
				</div>
			</div>
			
			<div id="right-container">
				<div>
					<ul>
					<c:forEach items="${chatRooms}" var="curItem">
						<li> 
							<div class="room">
								
									<div class="roomDetails">
										<c:out value="${curItem.name}"></c:out> <br><c:out value="${curItem.game}"></c:out>
									</div>
									<div class="roomStatus">
										<c:choose>
											<c:when test="${curItem.hasGameStarted}">
											In-Progress
											</c:when>
											<c:otherwise>
											Not Started
											</c:otherwise>
										</c:choose>
									</div>
								
									<div class="roomCount">
										Players: <c:out value="${curItem.numPlayers}"></c:out>/<c:out value="${curItem.maxPlayers}"></c:out>
										Spectators: <c:out value="${curItem.numSpectators}"></c:out>/<c:out value="${curItem.maxSpectators}"></c:out>
									</div>
									<div class="joinRoom">
										<form action = "room" method = "get">
										<input type ="hidden" name = "idRoom" value = "<c:out value="${curItem.id}"></c:out>">
										<input type ="hidden" name = "roomGame" value = "<c:out value="${curItem.game}"></c:out>">
										<input type ="submit" value="JOIN ROOM">
										</form>
									</div>
									<div class="blah"></div>
							</div>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			
			
		</div>
		<br>
		
		<p align="right" id="footer"> Created by: Kate Lacsamana, MG Malana, Albert Rivera &copy; 2015</p>
	</body>
	
</html>