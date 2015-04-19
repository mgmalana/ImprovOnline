package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import models.*;

public class DBService {
	String dbUsername = "root";
	String dbPassword = "";
	
	public boolean registerUser(User user){
		System.out.println("Enter registerUser");
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = conn.createStatement();
			String dbQuery = "insert into users(username, password, question, answer) values ('" 
					+ user.getUsername() + "','" + user.getPassword() + "','" 
					+ user.getQuestion() + "','" + user.getAnswer() + "')";
			int i = statement.executeUpdate(dbQuery);
			conn.close();
			if(i>0){ //registration successful
				System.out.println("Registration successful!");
				return true;
			}
			else{ //registration failed
				System.out.println("Registration failed.");
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Registration error: " + e.getMessage());
		}
		
		return false;
	}
	
	public String registerGuest(){
		System.out.println("Enter registerGuest");

		Random rand = new Random();
		int id = rand.nextInt(999999) + 1;
		String username = "Guest" + id;
		boolean registered = false;
		while(!registered){
			try{
				String url="jdbc:mysql://localhost:3306/improvonline";
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
				Statement statement = conn.createStatement();
				String dbQuery = "insert into guests (username) "
						+ "values ('" + username + "')";
				System.out.println(dbQuery);
				int i = statement.executeUpdate(dbQuery);
				conn.close();
				if(i>0){
					System.out.println("Registration successful!");
					registered = true;
					return username;
				}
				else{
					System.out.println("Registration failed.");
					return null;
				}
			}
			catch(Exception e){
				System.out.println("Registration error: " + e.getMessage());
				if(!(e.getMessage().contains("Duplicate entry") && e.getMessage().contains("key 'username_UNIQUE'"))){
					registered = true; //if error is not duplicate entry don't try again
				}
			}
		}
		return null;
		
	}

	public boolean validateUser(String username, String password){
		System.out.println("Enter validateUser");

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = conn.createStatement();
			String dbQuery = "select * from users where username like " + "'" + username + "'" + " and password like " + "'" + password + "'";
			System.out.println("validateuser: " + dbQuery);
			ResultSet set = statement.executeQuery(dbQuery);
			if(set.next()){
				System.out.println("Log-in successful!");
				conn.close();
				return true;
			}
			else{
				System.out.println("Log-in failed.");
				conn.close();
				return false;				
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<ChatRoom> getRooms(){
		System.out.println("Enter getRooms");

		ArrayList<ChatRoom> chatRooms = new ArrayList<>();
		try{
			
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
		    String sql = "SELECT *"
		    		+ " FROM chatrooms";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String sql1 = "SELECT COUNT(DISTINCT username)"
			    		+ " FROM playing where idChatroom = " + (int)rs.getObject("idChatrooms");
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				ResultSet rs1 = pstmt1.executeQuery();
				int numPlayer = 0;
				if(rs1.next())
					numPlayer = (int)(long)rs1.getObject(1);
				
				String sql2 = "SELECT COUNT(DISTINCT username)"
			    		+ " FROM spectating where idChatroom = " + (int)rs.getObject("idChatrooms");
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = pstmt2.executeQuery();
				int numSpectator = 0;
				if(rs2.next())
					numSpectator = (int)(long)rs2.getObject(1);
				
				chatRooms.add(new ChatRoom((int)rs.getObject("idChatrooms"), (String)rs.getObject("name"), (String)rs.getObject("description"), numPlayer, numSpectator, (int)rs.getObject("maxPlayers"), (int)rs.getObject("maxSpectators"), (boolean)rs.getObject("hasGameStarted"),  (String)rs.getObject("CurrentGame")));
				
			}
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		} 
		
		return chatRooms;
	}
	
	public ChatRoom getRoom(int id){

		ChatRoom chatRoom = null;
		System.out.println("Enters getRoom");
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
		    String sql = "SELECT *"
		    		+ " FROM chatrooms"
		    		+ " WHERE idChatrooms = " + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				String sql1 = "SELECT COUNT(DISTINCT username)"
			    		+ " FROM playing where idChatroom = " + id;
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				ResultSet rs1 = pstmt1.executeQuery();
				int numPlayer = 0;
				if(rs1.next())
					numPlayer = (int)(long)rs1.getObject(1);
				
				String sql2 = "SELECT COUNT(DISTINCT username)"
			    		+ " FROM spectating where idChatroom = " + id;
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = pstmt2.executeQuery();
				int numSpectator = 0;
				if(rs2.next())
					numSpectator = (int)(long)rs2.getObject(1);
				
				return new ChatRoom((int)rs.getObject("idChatrooms"), (String)rs.getObject("name"), (String)rs.getObject("description"), numPlayer, numSpectator, (int)rs.getObject("maxPlayers"), (int)rs.getObject("maxSpectators"), (boolean)rs.getObject("hasGameStarted"),  (String)rs.getObject("CurrentGame"));
				
			} else{
				System.out.println("Error: Chatroom not found");
			}
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		} 
		
		return chatRoom;
	}
	
	public String getRandomPrompt(){

		try{
			System.out.println("Enters getRandomPrompts");

			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
		    String sql = "SELECT prompt\n" + 
		    		"  FROM prompts AS r1 JOIN\n" + 
		    		"       (SELECT CEIL(RAND() *\n" + 
		    		"                     (SELECT MAX(idPrompts)\n" + 
		    		"                        FROM prompts)) AS idPrompts)\n" + 
		    		"        AS r2\n" + 
		    		" WHERE r1.idPrompts >= r2.idPrompts\n" + 
		    		" ORDER BY r1.idPrompts ASC\n" + 
		    		" LIMIT 1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return (String)rs.getObject(1);
			} else{
				System.out.println("Error: Prompt not found");
			}
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		} 
		
		return null;
	}
	public boolean postMessage(String username, String message, String datetime, int idchat){
		System.out.println("enter post message");
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

			String sql = "INSERT INTO message (chat_id, user_name, message, post_time) \n"
					+ "VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idchat);
			pstmt.setString(2, username);
			pstmt.setString(3, message);
			pstmt.setString(4, datetime);

			pstmt.executeUpdate();
			conn.close();
			
			return true;
		}
		catch(Exception e){
			System.out.println("posting message error: " + e.getMessage());
		}
			return false;
	} 
	
	public ArrayList<Message> getAllMessage(int chatid){
		System.out.println("Enters getAllMessage");

		ArrayList<Message> messages = new ArrayList<>();
		
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
		    String sql = "SELECT *"
		    		+ " FROM message "
		    		+ " WHERE chat_id = " + chatid
		    		+ " ORDER BY chat_id ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				messages.add(new Message((String)rs.getObject("user_name"), (String)rs.getObject("message"), ((Timestamp)rs.getObject("post_time")).toString()));
			}
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}
		
		return messages;
	}
	
	public boolean addPlayerToRoom(String username, int chatId){
		System.out.println("Enters addPlayerToRoom");

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = conn.createStatement();
			String dbQuery = "insert into playing(idChatroom, username) values ('" 
					+ chatId + "','" + username + "')";
			int i = statement.executeUpdate(dbQuery);
			conn.close();
			if(i>0){ //playing successful
				System.out.println("Playing successful!");
				return true;
			}
			else{ //playing failed
				System.out.println("Playing failed.");
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Playing error: " + e.getMessage());
		}
		
		return false;
	}

	public int getRoomPlayerCount(int chatid){
		System.out.println("Enters room player count");

		int numPlayer = 0;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
			String sql1 = "SELECT COUNT(DISTINCT username)"
		    		+ " FROM playing where idChatroom = " + chatid;
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next())
				numPlayer = (int)(long)rs1.getObject(1);
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}
		
		return numPlayer;
	}
}
