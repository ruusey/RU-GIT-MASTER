package finalproj;

import java.awt.Rectangle;

import processing.core.PVector;

public class Tile {
	PVector pos;
	public int width;
	public int height;
	public float color;
	public boolean takesCol;
	public Rectangle colBox;
	public Tile(int x, int y, int width,int height, float color, boolean takesCol){
		this.pos=new PVector(x,y);
		this.height=height;
		this.width=width;
		this.color = color;
		this.takesCol=takesCol;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
	}
	public boolean coordsIn(float x , float y){
		return colBox.contains(x, y);
		//return (x>=pos.x && x<=pos.x+width) && (y>=pos.y && y<=pos.y+height);
	}
}
