package finalproj;

import java.awt.Rectangle;
import processing.core.PApplet;
import processing.core.PVector;

public class Entity extends Object{
	public PVector pos;
	public PVector vel;
	public Rectangle colBox;
	public int width;
	public int height;
	public PApplet parent;
	public Tile tile;
	public Entity(String name, int id,Texture t, float x, float y, int width, int height,  PApplet parent){
		super(name,id,t);
		pos = new PVector(x,y);
		vel = new PVector(0,0);
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
		this.width=width;
		this.height=height;
		this.parent = parent;
	
	}
	public Entity(String name, int id,Texture t, float x, float y, PVector vel,int width, int height,PApplet parent){
		super(name,id,t);
		pos = new PVector(x,y);
		this.vel = vel;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
		this.width=width;
		this.height=height;
		this.parent = parent;
		tile=Client.getTile(pos);

	}
	//if its a circlular object we must adjust
	public Entity(String name, int id,Texture t, float x, float y, PVector vel,int diameter, PApplet parent){
		super(name,id,t);
		pos = new PVector(x,y);
		this.vel = vel;
		colBox= new Rectangle((int)pos.x-diameter/2,(int)pos.y-diameter/2,diameter,diameter);
		this.width=diameter;
		this.height=diameter;
		this.parent = parent;
		tile=Client.getTile(pos);

	}
	
}
