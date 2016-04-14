package finalproj;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemy extends Entity implements Lootable{
	public ArrayList<Projectile> shots = new ArrayList<Projectile>();
	public AttackPattern current;
	int frame;
	long lastFire;
	long lastPatternChange;
	int changes = 0;
	public HealthBar hp;
	public Enemy(float x, float y, PVector vel, int diameter, int health, BufferedImage sprite,PApplet parent) {
		super(x, y, vel, diameter, sprite,parent);
		attackPattern1();
		lastFire=System.currentTimeMillis();
		lastPatternChange=System.currentTimeMillis();
		hp = new HealthBar((int)pos.x,(int)pos.y,200,diameter/2, health);
	}

	public void attackPattern1() {
		int shotDelay = 100;
		boolean targetPlayer = true;
		int shotsPerFrame = 4;
		float angleBetweenShots = 0.1f;
		int shotDamage = 60;
		int shotDiameter = 40;
		float shotSpeed = 4;
		float range = 400.0f;
		current = new AttackPattern(shotDelay, targetPlayer,shotsPerFrame,angleBetweenShots,shotDamage,shotDiameter,shotSpeed,range);
		
	}

	public void attackPattern2() {
		int shotDelay = 80;
		boolean targetPlayer = false;
		int shotsPerFrame = 12;
		float angleBetweenShots = 0.5f;
		int shotDamage = 60;
		int shotDiameter = 40;
		float shotSpeed = 6;
		float range = 400.0f;
		current = new AttackPattern(shotDelay, targetPlayer,shotsPerFrame,angleBetweenShots,shotDamage,shotDiameter,shotSpeed,range);
	}

	public void attackPattern3() {

	}
	public void update(){
		drawPlayer();
		drawProjectiles();
		tile = Client.getTile(pos);
		
	}
	
	public void drawProjectiles(){
		for (Projectile pr : shots) {

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
	public void drawPlayer(){
		PVector screenLoc = Client.world2screen(new PVector(colBox.x, colBox.y));
		parent.noFill();
		parent.fill(255, 0, 0);
		PImage toDraw = new PImage(Client.scale(img,64,64));
		screenLoc = Client.world2screen(pos);
		parent.image(toDraw,screenLoc.x-toDraw.width/2, screenLoc.y-toDraw.height/2, 64, 64);

		parent.rect(screenLoc.x - hp.green.width / 2, screenLoc.y - 50, hp.green.width, hp.green.height/2);
	}
	public void attack(){
		
		if(current.targetPlayer){
			float angle = (float) -Math.atan2(Client.p.pos.x - pos.x, Client.p.pos.y
					- pos.y)+PApplet.PI/2;
					
			angle+=0.2;
			PVector vel1 = PVector.fromAngle(angle);

			if(System.currentTimeMillis()-lastFire>450){
				for(int x = 0; x<current.shotsPerFrame;x++){
					
					shots.add(new Projectile(pos.x,pos.y,pos.x,pos.y, null, angle-=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,current.range,Client.sl.getSprite("86.png",6, 4), parent));
				}
				
				frame++;
				lastFire=System.currentTimeMillis();
			}
		}else{
			float startAngle=0;
			if(System.currentTimeMillis()-lastFire>450){
				for(int x = 0; x<current.shotsPerFrame;x++){
					
					shots.add(new Projectile(pos.x,pos.y,pos.x,pos.y, null, startAngle+=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,current.range,Client.sl.getSprite("86.png",13, 0), parent));
				}
				
				frame++;
				lastFire=System.currentTimeMillis();
		}
			
		}
		if(System.currentTimeMillis()-lastPatternChange>5000){
			if(changes%2==0){
				attackPattern2();
				changes++;
				lastPatternChange=System.currentTimeMillis();
			}else{
				attackPattern1();
				changes++;
				lastPatternChange=System.currentTimeMillis();
			}
			
		}
		
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

	public void death() {
		
		Client.enemies.remove(this);
		
		calculateDrops();
		
	}

	public void calculateDrops() {
		Lootbag loot = new Lootbag(pos.x,pos.y,32,32,Client.sl.getSprite("135.png", 2, 9), parent);
		loot.contents[0]=new Item(Client.sl.getSprite("97.png", 4, 0), "Coral Bow", 0, 0, 0, 0, 0, 0);
		Client.loot.add(loot);
		
	}

	public void dropLoot() {
		// TODO Auto-generated method stub
		
	}
}
