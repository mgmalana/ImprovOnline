package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import models.*;

public class DBService {
	String dbUsername = "root";
	String dbPassword = "";
	public boolean registerUser(User user){
		System.out.println("Enter registerUser");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
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
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	}
	
	public String registerGuest(){
		System.out.println("Enter registerGuest");
		Connection conn = null;


		Random rand = new Random();
		int id = rand.nextInt(999999) + 1;
		String username = "Guest" + id;
		boolean registered = false;
		while(!registered){
			try{
				String url="jdbc:mysql://localhost:3306/improvonline";
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, dbUsername, dbPassword);
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
			}finally{
				 if(conn!=null){
					 try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
		}
		return null;
		
	}

	public boolean validateUser(String username, String password){
		System.out.println("Enter validateUser");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
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
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		return false;
	}
	
	public ArrayList<ChatRoom> getRooms(){
		System.out.println("Enter getRooms");
		Connection conn = null;

		ArrayList<ChatRoom> chatRooms = new ArrayList<>();
		try{
			
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
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
		} finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return chatRooms;
	}
	
	public ChatRoom getRoom(int id){
		Connection conn = null;

		ChatRoom chatRoom = null;
		System.out.println("Enters getRoom");
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
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
		} finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return chatRoom;
	}
	
	public boolean postMessage(String username, String message, String datetime, int idchat){
		System.out.println("enter post message");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);

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
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
			return false;
	} 
	
	public ArrayList<Message> getAllMessage(int chatid){
		System.out.println("Enters getAllMessage");
		Connection conn = null;

		ArrayList<Message> messages = new ArrayList<>();
		
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
		    String sql = "SELECT *"
		    		+ " FROM message "
		    		+ " WHERE chat_id = " + chatid
		    		+ " ORDER BY chat_id ASC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				messages.add(new Message((String)rs.getObject("user_name"), (String)rs.getObject("message"), ((Timestamp)rs.getObject("post_time")).toString(), (int)rs.getObject("message_id")));
			}
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return messages;
	}
	
	public boolean addPlayerToRoom(String username, int chatId){
		System.out.println("Enters addPlayerToRoom");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
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
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	}

	public int getRoomPlayerCount(int chatid){
		System.out.println("Enters room player count");
		Connection conn = null;

		int numPlayer = 0;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
			String sql1 = "SELECT COUNT(DISTINCT username)"
		    		+ " FROM playing where idChatroom = " + chatid;
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next())
				numPlayer = (int)(long)rs1.getObject(1);
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return numPlayer;
	}
	
	public HashMap <String, Boolean> getUsers(int id){ // string username, boolean isplayer
		System.out.println("Enters getUsers");
		Connection conn = null;

		HashMap <String, Boolean> chatUsers = new HashMap<>();
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
			
			
			String sql1 = "SELECT DISTINCT username"
		    		+ " FROM playing where idChatroom = " + id;
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			
			while(rs1.next())
				chatUsers.put((String)rs1.getObject(1), true);
			
			String sql2 = "SELECT DISTINCT username"
		    		+ " FROM spectating where idChatroom = " + id;
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			
			while(rs2.next())
				chatUsers.put((String)rs2.getObject(1), false);

			conn.close();
			
			return chatUsers;
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		} finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return chatUsers;
	}
	
	public boolean removePlayerToRoom(String username, int chatId){
		System.out.println("Enters removePlayerToRoom");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);

			String sql = "DELETE FROM playing where username = '" +username +"' AND idChatroom = " + chatId;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
			conn.close();
			System.out.println("Remove player from room successful");
			return true;
		}
		catch(Exception e){
			System.out.println("removePlayerToRoom error: " + e.getMessage());
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	} 
	
	public boolean startGame(int chatId){
		System.out.println("Enters startGame");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			String prompt = "";
			String usernamewithturn ="";
			
			//PROMPT
			String sql1 = "SELECT prompt\n" + 
		    		"  FROM prompts AS r1 JOIN\n" + 
		    		"       (SELECT CEIL(RAND() *\n" + 
		    		"                     (SELECT MAX(idPrompts)\n" + 
		    		"                        FROM prompts)) AS idPrompts)\n" + 
		    		"        AS r2\n" + 
		    		" WHERE r1.idPrompts >= r2.idPrompts\n" + 
		    		" ORDER BY r1.idPrompts ASC\n" + 
		    		" LIMIT 1";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next())
				prompt = (String)rs1.getObject(1);
			
			String sql2 = "SELECT DISTINCT username"
		    		+ " FROM playing where idChatroom = " + chatId;
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			
			if(rs2.next())
				usernamewithturn = rs2.getString(1);
			
			
			
			//DATE
			java.util.Date dt = new java.util.Date();

			java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(dt);
			
			String sql = "UPDATE chatrooms"
					+ " SET hasGameStarted = ?, prompt = ?, starttime = ?, usernamewithturn = ?, currentLetter = ?"
					+ " WHERE idchatrooms = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, prompt);
			pstmt.setString(3, currentTime);
			pstmt.setString(4, usernamewithturn);
			pstmt.setString(5, "A");
			pstmt.setInt(6, chatId);

			pstmt.executeUpdate();
			
			
			conn.close();
			System.out.println("startGame successful");
			return true;
		}
		catch(Exception e){
			System.out.println("startGame error: " + e.getMessage());
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	}
	
	public ChatRoomPromptAndTime getPromptAndTime(int chatid){
		System.out.println("Enters getPromptAndTime");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
			String sql = "SELECT prompt, hasgamestarted, starttime, gametime, usernamewithturn, currentLetter"
		    		+ " FROM chatrooms where idChatrooms = " + chatid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return new ChatRoomPromptAndTime(rs.getBoolean("hasgamestarted"),
						rs.getString("prompt"), rs.getDouble("gametime"), rs.getTimestamp("starttime"), rs.getString("usernamewithturn"), rs.getString("currentLetter"));
			
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return new ChatRoomPromptAndTime(false);
	}	
	
	public boolean updateUserTurn(int chatid, String username){
		System.out.println("Enters updateUserTurn");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
			String sql = "SELECT usernamewithturn, currentLetter"
		    		+ " FROM chatrooms where idChatrooms = " + chatid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				if(username.equals(rs.getString(1))){
					Character lastletter =' ';
					lastletter = rs.getString(2).charAt(0);
					if(lastletter == 'Z')
						lastletter = 'A';
					else
						lastletter++;
					ArrayList<String> usernames = new ArrayList<>();
					String sql2 = "SELECT DISTINCT username"
				    		+ " FROM playing where idChatroom = " + chatid;
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					ResultSet rs2 = pstmt2.executeQuery();
					while(rs2.next()){
						usernames.add(rs2.getString(1));
					}
					if(usernames.size()>0){
						String nextUsername = usernames.get((usernames.indexOf(username)+1) % usernames.size());
					
					
						String sql1 = "UPDATE chatrooms"
								+ " SET usernamewithturn = ?, currentletter = ?"
								+ " WHERE idchatrooms = ?";
						
						PreparedStatement pstmt1 = conn.prepareStatement(sql1);
						pstmt1.setString(1, nextUsername);
						pstmt1.setString(2, lastletter + "");
						pstmt1.setInt(3, chatid);
						pstmt1.executeUpdate();
					}
				}
			}
			conn.close();
			return true;
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		return false;
	}
	
	public boolean stopGame(int chatId){
		System.out.println("Enters stopGame");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			
			String sql1 = "UPDATE chatrooms"
					+ " SET hasgamestarted = ?"
					+ " WHERE idchatrooms = ?";
			
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setBoolean(1, false);
			pstmt1.setInt(2, chatId);
			pstmt1.executeUpdate();
			
			System.out.println("clearing messages");
			String sql3 = "DELETE FROM message where chat_id = " + chatId;
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.executeUpdate();
			
			
		} catch (Exception e) {
		        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		return false;	
	}
	
	public boolean changePrompt(int chatId){
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			String prompt = "";
			
			//PROMPT
			String sql1 = "SELECT prompt\n" + 
		    		"  FROM prompts AS r1 JOIN\n" + 
		    		"       (SELECT CEIL(RAND() *\n" + 
		    		"                     (SELECT MAX(idPrompts)\n" + 
		    		"                        FROM prompts)) AS idPrompts)\n" + 
		    		"        AS r2\n" + 
		    		" WHERE r1.idPrompts >= r2.idPrompts\n" + 
		    		" ORDER BY r1.idPrompts ASC\n" + 
		    		" LIMIT 1";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next())
				prompt = (String)rs1.getObject(1);
			
			String sql = "UPDATE chatrooms"
					+ " SET prompt = ?"
					+ " WHERE idchatrooms = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prompt);
			pstmt.setInt(2, chatId);

			pstmt.executeUpdate();
			conn.close();
			System.out.println("changePrompt successful");
			return true;
		}
		catch(Exception e){
			System.out.println("changePrompt error: " + e.getMessage());
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	}

	public boolean updateSettings(int chatId, int time, String gametype){
		System.out.println("Enters updateSettings");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			
			String sql1 = "UPDATE chatrooms"
					+ " SET gametime = ?, currentgame = ?"
					+ " WHERE idchatrooms = ?";
			
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, time);
			pstmt1.setString(2, gametype);
			pstmt1.setInt(3, chatId);
			pstmt1.executeUpdate();
			
		} catch (Exception e) {
		        System.out.println("NEW ERROR!:::Error message: "+ e); 
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		return false;	
	}
	
	public String getGameType(int id){
		Connection conn = null;

		System.out.println("Enters getRoom");
		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);		
		    String sql = "SELECT currentGame"
		    		+ " FROM chatrooms"
		    		+ " WHERE idChatrooms = " + id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getString(1);
				
			conn.close();
	    } catch (Exception e) {
	        System.out.println("NEW ERROR!:::Error message: "+ e); 
		} finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return "";
	}
	
	public boolean addSpectatorToRoom(String username, int chatId){
		System.out.println("Enters SpectatorToRoom");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			Statement statement = conn.createStatement();
			String dbQuery = "insert into spectating(idChatroom, username) values ('" 
					+ chatId + "','" + username + "')";
			int i = statement.executeUpdate(dbQuery);
			conn.close();
			if(i>0){ //spectating successful
				System.out.println("Spectating successful!");
				return true;
			}
			else{ //spectating failed
				System.out.println("Spectating failed.");
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Playing error: " + e.getMessage());
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	}

	public boolean removeSpectatorToRoom(String username, int chatId){
		System.out.println("Enters removeSpectator");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);

			String sql = "DELETE FROM spectating where username = '" +username +"' AND idChatroom = " + chatId;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
			conn.close();
			System.out.println("Remove spectator from room successful");
			return true;
		}
		catch(Exception e){
			System.out.println("removeSpectatorFromRoom error: " + e.getMessage());
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	} 
	
	public boolean addPrompt(String prompt){
		System.out.println("Enters addPrompt");
		Connection conn = null;

		try{
			String url="jdbc:mysql://localhost:3306/improvonline";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			String sql = "insert into prompts(prompt) values (?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prompt);
			pstmt.executeUpdate();
		}
		catch(Exception e){
			System.out.println("addPrompt error: " + e.getMessage());
		}finally{
			 if(conn!=null){
				 try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		}
		
		return false;
	}
	
}
