package finalproj;

import processing.core.PVector;

public class Projectile {
	PVector pos;
	PVector source;
	PVector vel;
	int dmg;
	float angle;
	public Projectile(float x, float y, PVector vel, int dmg){
		pos = new PVector(x,y);
		source = new PVector(x,y);
		this.vel=vel;
		this.dmg=dmg;
	}
	public void update(){
		pos.add(vel);
	}
}
