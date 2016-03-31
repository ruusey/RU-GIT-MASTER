package packets;

import java.io.Serializable;

import processing.core.PVector;

public class UPDATE implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PVector pos;
	public String playerID;
	public UPDATE(PVector pos, String playerID) {
		this.pos = pos;
		this.playerID = playerID;
	}
	public UPDATE(){
		
	}
	public PVector getPos() {
		return pos;
	}
	public void setPos(PVector pos) {
		this.pos = pos;
	}
	public String getPlayerID() {
		return playerID;
	}
	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	
	
}
