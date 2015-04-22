package controllers;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.DBService;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/chatroom")
public class ChatRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoomController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		DBService db = new DBService();
		HttpSession session = request.getSession();

		int chatid = Integer.parseInt(request.getParameter("idchat"));
		
		java.util.Date dt = new java.util.Date();
		
		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("lalala");
		String currentTime = sdf.format(dt);
		if(!text.equals(""))
			db.postMessage((String)session.getAttribute("user"), text, currentTime, chatid);
		db.updateUserTurn(chatid, (String) session.getAttribute("user"));
	}

}
