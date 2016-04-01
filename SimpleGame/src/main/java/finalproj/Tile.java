package finalproj;

import processing.core.PVector;

public class Tile {
	PVector pos;
	public int width;
	public int height;
	public float color;
	public Tile(int x, int y, int width,int height, float color){
		this.pos=new PVector(x,y);
		this.height=height;
		this.width=width;
		this.color = color;
	}
	public boolean coordsIn(int x , int y){
		return (x>=pos.x && x<=pos.x+width) && (y>=pos.y && y<=pos.y+height);
	}
}
