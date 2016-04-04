package finalproj;

import java.awt.Rectangle;
import java.sql.Time;

import processing.core.PApplet;
import processing.core.PVector;

public class Projectile extends Movement{

	int dmg;
	PVector source;
    public float frequency = 0.5f;  
    public float magnitude = 8.5f; 
    int updates;
	public Projectile(float x, float y, float sx, float sy,PVector vel, int diameter, int dmg){
		super(x,y,vel,diameter);
		source = new PVector(sx,sy);
		this.dmg=dmg;
	}
	public void update(){
		
		pos.add(vel);
		pos.x+=Math.cos (updates * frequency) * magnitude;
		pos.y+=Math.cos (updates * frequency) * magnitude;
		colBox.x=(int) pos.x-colBox.width/2;
		colBox.y=(int) pos.y-colBox.height/2;
		updates++;
	}
}
