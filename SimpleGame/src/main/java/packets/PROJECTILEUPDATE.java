package packets;

import game.Player;
import game.Projectile;

import java.io.Serializable;

public class PROJECTILEUPDATE implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Projectile shot;
	public PROJECTILEUPDATE(Projectile shot){
		this.shot=shot;
		
		
	}
	public PROJECTILEUPDATE(){
		
	}
}
