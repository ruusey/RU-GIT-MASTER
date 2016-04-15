package finalproj;

import processing.core.PApplet;

public class Lootbag extends Entity{
	public Item[] contents = new Item[8];
	public Lootbag(String name, int id,Texture t,float x, float y, int width, int height, PApplet parent) {
		super(name,id,t,x, y, width, height, parent);
		tile=Client.getTile(pos);
		
	}

	
}
