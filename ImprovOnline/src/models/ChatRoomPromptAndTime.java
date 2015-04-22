package models;

import java.sql.Timestamp;

public class ChatRoomPromptAndTime {
	
	boolean hasStarted;
	String prompt;
	double gametime;
	Timestamp timestarted;
	
	
	public ChatRoomPromptAndTime(boolean hasStarted, String prompt,
			double gametime, Timestamp timestarted) {
		super();
		this.hasStarted = hasStarted;
		this.prompt = prompt;
		this.gametime = gametime;
		this.timestarted = timestarted;
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
	
	public String getTimeRemaining(){
		double time	= timestarted.getTime() + gametime * 60000 - (new java.util.Date()).getTime();
		return ((int)time / (60 * 1000)) +":"+(int)((time/1000)%60);
	} 
	
	
	
}
