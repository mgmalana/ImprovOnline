package controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Message;
import service.DBService;

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
		/////get messages
		for(Message message: messages){
			text += message.getUsername()+ ": " + message.getMessage() + "\n";
		}
		/////get users
		for (Entry<String, Boolean> entry : db.getUsers(chatid).entrySet()) {
			String username = entry.getKey();
			boolean isPlayer = entry.getValue();
			if(isPlayer)
				players += username + " ,";
			else
				spectators += username + " ,";
		}
		
		response.setContentType("text/xml");
		response.getWriter().println("<rooms>");
		response.getWriter().println("<room id = \"+ chatid +\">");
		response.getWriter().println("<msg>" + text + "</msg>");
		response.getWriter().println("<players>" + text + "</players>");
		response.getWriter().println("<spectators>" + text + "</spectators>");
		response.getWriter().println("</room>");
		response.getWriter().println("<rooms>");

	}

}
