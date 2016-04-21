package finalproj;

import java.util.ArrayList;

import XMLObjects.PlayerObject;
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
	public int statLeftPadding = 5;
	public int statLineHeight = 15;
	public GUI(int health, int mana, PVector pos, int width, int height,PApplet parent) {
		this.health = health;
		this.mana = mana;
		this.pos = pos;
		this.parent = parent;
		this.width=width;
		this.height=height;
	}
	public void update(Player po){
		parent.fill(204);
		parent.rect(pos.x, pos.y, width, height);
		parent.fill(105,105,105);
		
		parent.rect(pos.x, (float) (pos.y+parent.height*0.75), width, height/4,20);
		parent.rect(pos.x, (float) (pos.y+parent.height*0.5), width, height/4,20);
		parent.rect(pos.x, (float) (pos.y+parent.height*0.4), width, height/10,20);
		parent.textSize(12);
		parent.fill(255,0,0);
		parent.text("Att: "+ po.p.att, pos.x+statLeftPadding, (float) (pos.y+parent.height*0.4+statLineHeight));
		parent.text("Def: "+ po.p.def, pos.x+statLeftPadding, (float) (pos.y+parent.height*0.4+statLineHeight*2));
		parent.text("Spd: "+ po.p.spd, pos.x+statLeftPadding, (float) (pos.y+parent.height*0.4+statLineHeight*3));
		parent.text("Dex: "+ po.p.dex, pos.x+statLeftPadding, (float) (pos.y+parent.height*0.4+statLineHeight*4));
		parent.text("Vit: "+ po.p.vit, pos.x+statLeftPadding*12, (float) (pos.y+parent.height*0.4+statLineHeight));
		parent.text("HP: "+ po.hp.actualHealth+"/"+po.hp.maxHealth, pos.x+statLeftPadding*12, (float) (pos.y+parent.height*0.4+statLineHeight*2));
		
		
	}
	
}
