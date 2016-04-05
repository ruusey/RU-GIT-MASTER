package finalproj;

import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PVector;

public class Tile extends Movement{

	public float color;
	public boolean takesCol;
	
	public Tile(int x, int y, int width,int height, float color, boolean takesCol, PApplet parent){
		super(x,y,width,height, parent);
		this.color = color;
		this.takesCol=takesCol;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
	}
	
}
