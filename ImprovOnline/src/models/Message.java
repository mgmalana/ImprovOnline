package models;

public class Message {

	private String username = null; //name of sender
	private String message = null; //message
	private String timeStamp;
	private int id;
	
	public Message(String username, String message, String timeStamp, int id) {
		super();
		this.username = username;
		this.message = message;
		this.timeStamp = timeStamp;
		this.id = id;
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
	public int getId(){
		return id;
	}
	
	
	
	
	
}
