package packets;

import game.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class NEWTICK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Player> objects = new ArrayList<Player>();
	public NEWTICK(ArrayList<Player> objects){
		this.objects=objects;
	}
}
