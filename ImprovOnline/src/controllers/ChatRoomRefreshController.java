package controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		DBService db = new DBService();
		int chatid = Integer.parseInt(request.getParameter("idchat"));
		
		ArrayList<Message> messages = db.getAllMessage(chatid);
		
		if(messages.size() > 0);
			text += "{\"username\":\""+ messages.get(0).getUsername() +"\", \"message\":\""
					+ messages.get(0).getMessage() +"\"}";
		for(Message message: messages.subList(1, messages.size())){
				text += ",\n {\"username\":\""+ message.getUsername() +"\", \"message\":\""
						+ StringEscapeUtils.escapeJson(message.getMessage()) +"\"}";
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
		//System.out.println("{\"messages\":[ " + text + "], \"players\":[ " + players + "], \"spectators\":[ " + spectators + "]}");
		response.getWriter().println("{\"messages\":[ " + text + "], \"players\":[ " + players + "], \"spectators\":[ " + spectators + "]}");
		response.getWriter().flush();
	}

}
