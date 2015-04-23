package models;

import java.sql.Timestamp;

public class ChatRoomPromptAndTime {
	
	boolean hasStarted;
	String prompt;
	double gametime;
	Timestamp timestarted;
	String turn;
	String currentLetter;
	
	
	public ChatRoomPromptAndTime(boolean hasStarted, String prompt,
			double gametime, Timestamp timestarted, String turn, String currentLetter) {
		super();
		this.hasStarted = hasStarted;
		this.prompt = prompt;
		this.gametime = gametime;
		this.timestarted = timestarted;
		this.turn = turn;
		this.currentLetter = currentLetter;
	}
	public boolean hasStarted() {
		return hasStarted;
	}
	public String getPrompt() {
		return prompt;
	}
	public double getGametime() {
		return gametime;
	}
	public Timestamp getTimestarted() {
		return timestarted;
	}
	public String getTurn(){
		return turn;
	}
	public String getCurrentLetter(){
		return currentLetter;
	}
	
	public String getTimeRemaining(){
		double time	= timestarted.getTime() + gametime * 60000 - (new java.util.Date()).getTime();
		int mins = (int)((time/1000)%60);
		if(mins >= 10)
			return ((int)time / (60 * 1000)) +":"+ mins;
		else
			return ((int)time / (60 * 1000)) +":0"+ mins;
	} 
	
	
	
}
