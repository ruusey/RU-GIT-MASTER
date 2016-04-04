package finalproj;

import java.awt.Rectangle;

import processing.core.PVector;

public class Tile extends Movement{

	public float color;
	public boolean takesCol;
	
	public Tile(int x, int y, int width,int height, float color, boolean takesCol){
		super(x,y,width,height);
		this.color = color;
		this.takesCol=takesCol;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
	}
	
}
