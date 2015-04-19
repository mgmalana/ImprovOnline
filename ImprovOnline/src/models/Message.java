package models;

public class Message {

	private String username = null; //name of sender
	private String message = null; //message
	private String timeStamp;
	
	public Message(String username, String message, String timeStamp) {
		super();
		this.username = username;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	//return name of sender
	public String getUsername() {
		return username;
	}

	//return message sent
	public String getMessage() {
		return message;
	}

	//return time of message
	public String getTimeStamp() {
		return timeStamp;
	}
	
	
	
	
	
}
