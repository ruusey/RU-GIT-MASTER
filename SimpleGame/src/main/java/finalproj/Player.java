package finalproj;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import XMLObjects.PlayerObject;
import XMLObjects.WeaponObject;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Player extends Entity {

	public boolean firing;
	ArrayList<Shot> shots = new ArrayList<Shot>();
	public HealthBar hp;
	public long lastCheck;
	public WeaponObject w;
	public PlayerObject p;
	public Player(PlayerObject p,int x, int y, int diameter, PApplet parent) {
		super(p.name,p.id,p.tex,x, y, diameter, diameter, parent);
		pos = new PVector(x, y);
		vel = new PVector(0, 0);
		firing = false;
		this.w = Client.loader.getWeapon("Coral Bow");
		
		final int x1 = (700 - diameter*2) / 2;
		final int y1 = (700) / 2;
		hp = new HealthBar((int)x1,(int)y1,200,diameter/2, p.hp);
		colBox = new Rectangle((int) pos.x - (diameter / 2), (int) pos.y
				- (diameter / 2), diameter, diameter);
		this.lastCheck=System.currentTimeMillis();
		
		this.p=p;
	}
	
	public void update() {

		pos.add(vel);
		hp.update();
		colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;
		tile = Client.getTile(pos);
		
		drawPlayer();
		drawProjectiles();
		

	}
	public void drawPlayer(){
		PVector screenLoc = Client.world2screen(new PVector(colBox.x, colBox.y));
		parent.noFill();
		parent.fill(0, 0, 255);
		BufferedImage image = Client.sl.getSprite(tex);
		PImage toDraw = new PImage(Client.scale(image,64,64));
		screenLoc = Client.world2screen(pos);
		parent.image(toDraw,screenLoc.x-toDraw.width/2, screenLoc.y-toDraw.height/2, 64, 64);
		
		parent.rect(parent.width / 2-100 - hp.green.width / 2, hp.green.y - 50, hp.green.width, hp.green.height/2);
	}
	public void drawProjectiles(){
		ArrayList<Shot> shotsToRemove = new ArrayList<Shot>();
		for (Shot pr : shots) {

			if (Client.checkSimpleCollision(pr.colBox)!=null) {

				shotsToRemove.add(pr);
			} else {
				PVector screenLoc = Client.world2screen(pr.pos);
				BufferedImage image = Client.sl.getSprite(pr.tex);
				PImage toDraw = new PImage(Client.scale(image,32,32));
				
				parent.pushMatrix();
				parent.translate(screenLoc.x, screenLoc.y);
				parent.rotate(pr.angle+PApplet.PI/4);
				parent.imageMode(PApplet.CENTER);
				parent.image(toDraw,0, 0, 32, 32);
				parent.imageMode(PApplet.CORNER);
				parent.popMatrix();
			}

		}
		shots.removeAll(shotsToRemove);
	}

	public void updateShots() {
		ArrayList<Shot> shotsToRemove = new ArrayList<Shot>();
		for (Shot p : shots) {
			if (PVector.dist(p.source, p.pos) > p.range
					|| Client.checkSimpleCollision(p.colBox) != null) {
				shotsToRemove.add(p);
			} else {
				p.update();
			}
		}
		shots.removeAll(shotsToRemove);
	}

	public void handleTerrainCollision(Rectangle col) {
		if (col.width > col.height && vel.y < 0) {
			pos.y += col.getHeight();

		}
		if (col.width > col.height && vel.y > 0) {
			pos.y -= col.getHeight();

		}
		if (col.height > col.width && vel.x < 0) {
			pos.x += col.getWidth();

		}
		if (col.height > col.width && vel.x > 0) {
			pos.x -= col.getWidth();

		}
	}
}
