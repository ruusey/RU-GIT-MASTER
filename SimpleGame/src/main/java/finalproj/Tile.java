package finalproj;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import XMLObjects.GameObject;
import XMLObjects.WallObject;
import processing.core.PApplet;
import processing.core.PVector;

public class Tile extends Entity{

	public float color;
	public boolean takesCol;
	boolean isWall;
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Tile(GameObject go,int x, int y, int width,int height, float color, boolean takesCol,PApplet parent){
		super(go.name,go.id,go.tex,x,y,width,height, parent);
		this.color = color;
		this.takesCol=takesCol;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
		isWall=false;
	}
	public Tile(WallObject wall,int x, int y, int width,int height, float color, boolean takesCol,PApplet parent){
		super(wall.name,wall.id,wall.tex,x,y,width,height, parent);
		this.color = color;
		this.takesCol=takesCol;
		colBox= new Rectangle((int)pos.x,(int)pos.y,width,height);
		isWall=true;
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
