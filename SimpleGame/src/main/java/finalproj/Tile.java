package finalproj;

import processing.core.PVector;

public class Tile {
	PVector pos;
	public int width;
	public int height;
	public Tile(int x, int y, int width,int height){
		this.pos=new PVector(x,y);
		this.height=height;
		this.width=width;
	}
}
