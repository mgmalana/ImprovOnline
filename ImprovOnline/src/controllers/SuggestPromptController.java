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
 * Servlet implementation class loginController
 */
@WebServlet("/suggestPrompt")
public class SuggestPromptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuggestPromptController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Enters suggest prompt servlet");
		
		DBService db = new DBService();
		String prompt = request.getParameter("prompt");
		
		db.addPrompt(prompt);
	}
	

}
