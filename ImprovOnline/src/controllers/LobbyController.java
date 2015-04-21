package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ChatRoom;
import service.DBService;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/index")
public class LobbyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LobbyController() {
        super();
		System.out.println("Enter lobbyController Constructor");

        // TODO Auto-generated constructor stub
    }	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Enter lobbyController doGet");
		DBService db = new DBService();
		ArrayList<ChatRoom> chatRooms = db.getRooms();
		if(chatRooms.size() > 0){
			request.setAttribute("chatRooms", chatRooms);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("No created rooms");
			response.sendRedirect("login.jsp");	
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
