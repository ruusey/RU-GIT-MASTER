package GameServer.SimpleGame;

import java.io.Serializable;

public class Packet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int PID;
	public byte[] data;
	
	
	public Packet(int id){
		PID = id;
		data = null;
	}
	public Packet(int id,byte[] data){
		PID = id;
		this.data = data;
	}
	public Packet(){
		
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
}
