package controllers;


import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ChatRoomPromptAndTime;
import models.Message;
import service.DBService;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/chatroomRefresh")
public class ChatRoomRefreshController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoomRefreshController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Refresh!");
		
		String text = "";
		String players = "";
		String spectators = "";
		String gameHasStarted ="";
		DBService db = new DBService();
		int chatid = Integer.parseInt(request.getParameter("idchat"));
		int lastmessage = Integer.parseInt(request.getParameter("lastmessage"));
		boolean ispressed = Boolean.parseBoolean((request.getParameter("ispressed")));
		
		// for timer and prompt
		if(ispressed){
			db.startGame(chatid);
		} 
		
		ChatRoomPromptAndTime chat = db.getPromptAndTime(chatid);
		if(chat.hasStarted()){
			if(chat.getTimeRemaining().contains("-")){
				db.stopGame(chatid);
				gameHasStarted = ", \"gameHasStarted\":[  {\"prompt\": \"\", \"timeleft\": \"\", \"usernameturn\": \"\", \"currentLetter\": \"\"} ]";
			}
			else
			gameHasStarted = ", \"gameHasStarted\":[  {\"prompt\": \""+ chat.getPrompt()+"\", \"timeleft\": \""+ chat.getTimeRemaining()+"\", \"usernameturn\": \""+ chat.getTurn()+"\", \"currentLetter\": \""+ chat.getCurrentLetter()+"\"} ]";
		}
		
		List<Message> messages = db.getAllMessage(chatid);
		
		if(messages.size() > 0){
			if(lastmessage == 0)
				lastmessage = messages.get(messages.size()-1).getId();
			for(int i = messages.size()-1; i >= 0; i--) ///load the new messages lang
				if(messages.get(i).getId() <= lastmessage){
					messages = messages.subList(i + 1, messages.size());
					break;
				} else
					lastmessage = messages.get(i).getId();
			
			System.out.println(messages.size());
			if(messages.size() > 0){
				text += "{\"username\":\""+ messages.get(0).getUsername() +"\", \"message\":\""
							+ messages.get(0).getMessage() +"\"}";
				for(Message message: messages.subList(1, messages.size())){
						text += ",\n {\"username\":\""+ message.getUsername() +"\", \"message\":\""
								+ StringEscapeUtils.escapeJson(message.getMessage()) +"\"}";
				}
			}
		}
						
		for (Entry<String, Boolean> entry : db.getUsers(chatid).entrySet()) {
			String username = entry.getKey();
			boolean isPlayer = entry.getValue();
			
			if(isPlayer)
				players += ",\n{\"username\":\""+ username +"\"}";
			else
				spectators += ",\n{\"username\":\""+ username +"\"}";
		}
		
		if(players.length()>1) // if may players
			players = players.substring(2);
		if(spectators.length()>1) // if may players
			spectators = spectators.substring(2);

		//text = StringEscapeUtils.escapeJson(text);
		System.out.println("{\"messages\":[ " + text + "], \"players\":[ " + players + "], \"spectators\":[ " + spectators + "], \"lastmessage\":[  {\"message\": "+ lastmessage+"} ]"
				+ ", \"ispressed\":[  {\"ispressed\": "+ ispressed+"} ]"+ gameHasStarted +"}");
		response.getWriter().println("{\"messages\":[ " + text + "], \"players\":[ " + players + "], \"spectators\":[ " + spectators + "], \"lastmessage\":[  {\"message\": "+ lastmessage+"} ]"
				+ ", \"ispressed\":[  {\"ispressed\": "+ ispressed+"} ]"+ gameHasStarted +"}");
		
		response.getWriter().flush();
	}

}
