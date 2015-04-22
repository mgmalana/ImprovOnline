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
@WebServlet("/room")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomController() {
        super();
		System.out.println("Enter lobbyController Constructor");

        // TODO Auto-generated constructor stub
    }	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("roomGame").equals("Alphabet Game")){
			request.setAttribute("instructions", "Each player takes turns in giving lines to create a scene. But here's the catch - the players must work together to spell the alphabet using the first letters of the first word of each turn!\n" + 
					"\n" + 
					"Example:\n" + 
					"Grace: A squirrel just stole my purse!\n" + 
					"Mamrie: But, that is highly unlikely. We're on a ship in the middle of the ocean.\n" + 
					"Hannah: Crazy kids, why did I get stuck on this cruise with these two?\n" + 
					"...and so on!\n" + 
					"");
		} else if(request.getParameter("roomGame").equals("Cards")){
			request.setAttribute("instructions", "Players are given an initial prompt to start the scene. But be ready to improvise as prompts will be given at random intervals! Be sure to incorporate the given prompts in your scene.\n" + 
					"\n" + 
					"Example:\n" + 
					"Prompt: There's a snake in my boot!\n" + 
					"Miranda: Get it out! I hate snakes.\n" + 
					"Colleen: Eww! Don't get it near me. Don't touch me!\n" + 
					"Joshua: You guys, it's so cute! Can I keep it?\n" + 
					"Prompt: A red jacket appears.\n" + 
					"Joshua: Why is the snake wearing a red jacket?\n" + 
					"Miranda: I don't know. Please get it out of my boot!\n" + 
					"And so on...");
		} else if(request.getParameter("roomGame").equals("Yes, And")){
			request.setAttribute("instructions", "Players are given an initial prompt. Each player takes turns to add to the scene. Here's the twist - you can only start your lines with \"Yes, and...\". Each player must accept what the previous player has stated and add on to the previous line!\n" + 
					"\n" + 
					"Example: \n" + 
					"Prompt: You are late for school.\n" + 
					"Rhett: I cannot believe I'm late for school again!\n" + 
					"Link: Yes, and we are so getting in trouble.\n" + 
					"Rhett: Yes, and I do not want to get stuck in detention.\n" + 
					"Link: Yes, and to be stuck with that weird girl from 5th period? No thank you!\n" + 
					"Rhett: Yes, but you have to admit - she's kinda cute right?\n" + 
					"And so on...");
		} else if(request.getParameter("roomGame").equals("Each player takes turns giving lines. But, the line should be associated to at least one word from the previous player's line! \n" + 
				"\n" + 
				"Example:\n" + 
				"Jenna: Is class over yet?\n" + 
				"Julien: No, but I'm still not over how Charles and Alli broke up. Can you believe it?\n" + 
				"Jenna: Uhm, yeah. Hey, can you check my computer? I think I broke it.\n" + 
				"Julien: Your computer is fine. But we should go check on Charles and Alli. Last I heard, they were fighting on Twitter.\n" + 
				"And so on...")){
			request.setAttribute("instructions", "*insert here instructions*");
		}
		
		DBService db = new DBService();
		HttpSession session = request.getSession();
		int chatRoomId = Integer.parseInt(request.getParameter("idRoom"));
		db.addPlayerToRoom((String)session.getAttribute("user"), chatRoomId);
		request.setAttribute("idchat", chatRoomId);
		System.out.println("session attribute idchat: " + session.getAttribute("idchat"));
		
		request.setAttribute("chatRoom", db.getRoom(Integer.parseInt(request.getParameter("idRoom"))));
		RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
		dispatcher.forward(request, response);

		
	}
}
