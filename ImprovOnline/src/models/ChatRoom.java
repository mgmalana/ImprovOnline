package models;

public class ChatRoom {
	int id;
	String name;
	String description;
	int numPlayers;
	int numSpectators;
	int maxPlayers;
	int maxSpectators;
	boolean hasGameStarted;
	String game;
	
	
	
	public ChatRoom(int id, String name, String description, int numPlayers, int numSpectators, int maxPlayers,
			int maxSpectators, boolean hasGameStarted, String game) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.numPlayers = numPlayers;
		this.numSpectators = numSpectators;
		this.maxPlayers = maxPlayers;
		this.maxSpectators = maxSpectators;
		this.hasGameStarted = hasGameStarted;
		this.game = game;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getNumPlayers() {
		return numPlayers;
	}
	public int getNumSpectators() {
		return numSpectators;
	}
	public boolean isHasGameStarted() {
		return hasGameStarted;
	}
	public String getGame() {
		return game;
	}
	public int getMaxPlayers(){
		return maxPlayers;
	}
	public int getMaxSpectators(){
		return maxSpectators;
	}
	
	public void setNumPlayers(int numPlayers){
		this.numPlayers = numPlayers;
	}
	public void setNumSpectators(int numSpectators){
		this.numSpectators = numSpectators;
	}
	

	
}
