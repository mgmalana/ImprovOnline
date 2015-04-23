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
import models.*;

@WebServlet("/registration")
public class registrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String password = req.getParameter("password1");
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		
		User user = new User(username, password, question, answer);
		
		DBService db = new DBService();
		if(db.registerUser(user)){
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("login.jsp");	
		}
		else{
			req.setAttribute("error", "Registration failed!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
			dispatcher.forward(req, resp);
		}
	}
	

}
