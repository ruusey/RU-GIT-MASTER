package finalproj;

import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PVector;

public class Movement {
	public PVector pos;
	public PVector vel;
	public Rectangle colBox;
	public int width;
	public int height;
	public PApplet parent;
	
	public Movement(float x, float y, int width, int height, PApplet parent){
		pos = new PVector(x,y);
		vel = new PVector(0,0);
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
		this.width=width;
		this.height=height;
		this.parent = parent;
	}
	public Movement(float x, float y, PVector vel,int width, int height,PApplet parent){
		pos = new PVector(x,y);
		this.vel = vel;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
		this.width=width;
		this.height=height;
		this.parent = parent;
	}
	//if its a circlular object we must adjust
	public Movement(float x, float y, PVector vel,int diameter,PApplet parent){
		pos = new PVector(x,y);
		this.vel = vel;
		colBox= new Rectangle((int)pos.x-diameter/2,(int)pos.y-diameter/2,diameter,diameter);
		this.width=diameter;
		this.height=diameter;
		this.parent = parent;
	}
}
