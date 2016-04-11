package finalproj;

import java.awt.List;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Player extends Movement {

	public int health;
	public boolean firing;
	ArrayList<Projectile> shots = new ArrayList<Projectile>();
	public HealthBar hp;
	public Player(int x, int y, int health, int diameter,BufferedImage sprite, PApplet parent) {
		super(x, y, diameter, diameter, sprite, parent);
		pos = new PVector(x, y);
		vel = new PVector(0, 0);
		this.health = health;
		firing = false;
		final int x1 = (700 - diameter*2) / 2;
		final int y1 = (700) / 2;
		hp = new HealthBar((int)x1,(int)y1,200,diameter/2, health);
		colBox = new Rectangle((int) pos.x - (diameter / 2), (int) pos.y
				- (diameter / 2), diameter, diameter);
	}

	public void update() {

		pos.add(vel);
		hp.update();
		colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;
		
		
		drawPlayer();
		drawProjectiles();
		

	}
	public void drawPlayer(){
		PVector screenLoc = Client.world2screen(new PVector(colBox.x, colBox.y));
		parent.noFill();
		parent.fill(0, 0, 255);
		
		PImage toDraw = new PImage(Client.scale(img,64,64));
		screenLoc = Client.world2screen(pos);
		parent.image(toDraw,screenLoc.x-toDraw.width/2, screenLoc.y-toDraw.height/2, 64, 64);
		
		parent.rect(parent.width / 2 - hp.green.width / 2, hp.green.y - 50, hp.green.width, hp.green.height/2);
	}
	public void drawProjectiles(){
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for (Projectile pr : shots) {

			if (Client.checkSimpleCollision(pr.colBox)!=null) {

				shotsToRemove.add(pr);
			} else {
				PVector screenLoc = Client.world2screen(pr.pos);
				PImage toDraw = new PImage(Client.scale(pr.img,32,32));
				
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

	public void updateX() {

		pos.x+=vel.x;
		colBox.x = (int) pos.x - colBox.width / 2;
		//colBox.y = (int) pos.y - colBox.height / 2;

	}

	public void updateY() {

		pos.y+=vel.y;
		//colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;

	}

	public void updateShots() {
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for (Projectile p : shots) {
			if (PVector.dist(p.source, p.pos) > p.range
					|| Client.checkSimpleCollision(p.colBox) != null) {
				shotsToRemove.add(p);
			} else {
				p.update();
			}
		}
		shots.removeAll(shotsToRemove);
	}
}
