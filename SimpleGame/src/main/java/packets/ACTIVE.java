package packets;

import java.io.Serializable;

public class ACTIVE implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean active;
	public ACTIVE(boolean active){
		this.active=active;
	}
	
}
