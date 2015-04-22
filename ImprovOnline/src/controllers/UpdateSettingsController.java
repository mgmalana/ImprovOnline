package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DBService;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/updateSettings")
public class UpdateSettingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSettingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBService db = new DBService();
		int chatId = Integer.parseInt(request.getParameter("idchat"));
		int time = Integer.parseInt(request.getParameter("newtime").charAt(0) + "");
		
		
		db.updateSettings(chatId, time);
		
	}
	

}
