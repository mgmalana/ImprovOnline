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
@WebServlet("/getCurrentGame")
public class GetCurrentGameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentGameController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("enters getcurrentgame");
		DBService db = new DBService();
		int chatId = Integer.parseInt(request.getParameter("idchat"));
		
		response.getWriter().print(db.getGameType(chatId));
		
	}
	

}
