package KRYO;

import processing.core.PVector;

public class Character {
	public String UUID;
	public PVector pos;
	public PVector vel;
	public PVector dest;
	public Character(String UUID, int x,int y, int vx, int vy) {
		this.UUID=UUID;
		this.pos = new PVector(x,y);
		this.vel = new PVector(vx,vy);
	}
	public void update(){
		pos.add(vel);
	}
	public boolean tryToMoveTo(){
		if(dest==null) return false;
		pos.add(dest);
		dest=null;
		return true;
		
	}
	public Character(){
		
	}
	
}