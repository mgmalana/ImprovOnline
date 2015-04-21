package controllers;


import java.io.IOException;
import java.util.ArrayList;

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
		DBService db = new DBService();
		int chatid = Integer.parseInt(request.getParameter("idchat"));

		ArrayList<Message> messages = db.getAllMessage(chatid);
		
		for(Message message: messages){
			text += message.getUsername()+ ": " + message.getMessage() + "\n";
		}
		//text = ": lalla\n";
		text = StringEscapeUtils.escapeJson(text);
		System.out.println("{\"messages\": \"" + text + "\" "+ "}");
		response.getWriter().println("{\"messages\": \"" + text + "\" "+ "}");
		response.getWriter().flush();
	}

}
