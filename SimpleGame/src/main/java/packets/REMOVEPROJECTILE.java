package packets;

import game.Projectile;

import java.io.Serializable;

public class REMOVEPROJECTILE implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Projectile shot;
	
	public REMOVEPROJECTILE(Projectile shot){
		this.shot=shot;
		
	}
	public REMOVEPROJECTILE(){
		
	}
}
