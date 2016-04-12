package finalproj;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class GUI {
	public int health;
	public int mana;
	public PVector pos;
	public PApplet parent;
	public int width;
	public int height;
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public ArrayList<Item> equipped = new ArrayList<Item>();
	public GUI(int health, int mana, PVector pos, int width, int height,PApplet parent) {
		this.health = health;
		this.mana = mana;
		this.pos = pos;
		this.parent = parent;
		this.width=width;
		this.height=height;
	}
	public void update(){
		parent.fill(204);
		parent.rect(pos.x, pos.y, width, height);
		parent.fill(105,105,105);
		parent.rect(pos.x, (float) (pos.y+parent.height*0.75), width, height/4,20);
		parent.rect(pos.x, (float) (pos.y+parent.height*0.5), width, height/4,20);
		parent.rect(pos.x, (float) (pos.y+parent.height*0.4), width, height/10,20);
	}
	
}
