package finalproj;

import java.awt.image.BufferedImage;

import processing.core.PApplet;

public class Lootbag extends Entity{
	public Item[] contents = new Item[8];
	public Lootbag(float x, float y, int width, int height, BufferedImage sprite, PApplet parent) {
		super(x, y, width, height, sprite, parent);
		tile=Client.getTile(pos);
		
	}

	
}
