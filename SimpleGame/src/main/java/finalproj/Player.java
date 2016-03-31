package finalproj;

import processing.core.PVector;

public class Player {
	PVector pos;
	PVector vel;
	int dmg;
	public Player(int x, int y, int dmg){
		pos = new PVector(x,y);
		vel= new PVector(0,0);
		this.dmg=dmg;
	}
	public void update(){
		pos.add(vel);
	}
}
