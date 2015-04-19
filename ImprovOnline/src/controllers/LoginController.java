package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		DBService db = new DBService();
		System.out.println("usertype = " + usertype);
		if(usertype.equals("registered")){
			if(db.validateUser(username, password)){
				
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				session.setAttribute("usertype", usertype);
				response.sendRedirect("index");	
				
			} else {
				System.out.println("Invalid user.");
				request.setAttribute("error", "Invalid log-in details!");
				request.setAttribute("error2", "Not yet registered? <a href='register'> Click here! </a>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", db.registerGuest());
			session.setAttribute("usertype", usertype);
			response.sendRedirect("index");	
		}
		
	}
	

}
