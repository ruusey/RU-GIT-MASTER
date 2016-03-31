package packets;

import java.io.Serializable;

public class HELLO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String UUID;
	public HELLO(String UUID){
		this.UUID=UUID;
	}
	public HELLO(){
		
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
}
