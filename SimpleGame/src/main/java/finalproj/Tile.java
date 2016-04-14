package finalproj;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Tile extends Entity{

	public float color;
	public boolean takesCol;
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Tile(int x, int y, int width,int height, float color, boolean takesCol, BufferedImage sprite,PApplet parent){
		super(x,y,width,height, sprite,parent);
		this.color = color;
		this.takesCol=takesCol;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
	}
	public boolean posIn(PVector pos){
		return colBox.contains(pos.x, pos.y);
	}
	public void addEntity(Entity e){
		entities.add(e);
	}
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
}
