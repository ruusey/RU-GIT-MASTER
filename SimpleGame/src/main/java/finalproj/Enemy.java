package finalproj;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Enemy extends Movement implements Runnable{
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
		float shotSpeed = 8;
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
		float shotSpeed = 8;
		float range = 400.0f;
		current = new AttackPattern(shotDelay, targetPlayer,shotsPerFrame,angleBetweenShots,shotDamage,shotDiameter,shotSpeed,range);
	}

	public void attackPattern3() {

	}
	public void update(){
		drawPlayer();
		drawProjectiles();
	}
	
	public void drawProjectiles(){
		for (Projectile pr : shots) {

			parent.fill(255, 165, 0);
			PVector screenLoc = Client.world2screen(pr.pos);
			parent.ellipse(screenLoc.x, screenLoc.y, current.shotDiameter, current.shotDiameter);
		}
	}
	public void drawPlayer(){
		PVector screenLoc = Client.world2screen(new PVector(colBox.x, colBox.y));
		parent.noFill();
		parent.fill(255, 0, 0);
		screenLoc = Client.world2screen(pos);
		parent.ellipse(screenLoc.x, screenLoc.y, width, height);

		parent.rect(parent.width / 2 - hp.green.width / 2, hp.green.y - 50, hp.green.width, hp.green.height);
	}
	public void attack(){
		
		if(current.targetPlayer){
			float angle = (float) -Math.atan2(Client.p.pos.x - pos.x, Client.p.pos.y
					- pos.y)+PApplet.PI/2;
					

			PVector vel1 = PVector.fromAngle(angle);

			if(System.currentTimeMillis()-lastFire>450){
				for(int x = 0; x<current.shotsPerFrame;x++){
					
					shots.add(new Projectile(pos.x,pos.y,pos.x,pos.y, null, angle-=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,current.range,null, parent));
				}
				
				frame++;
				lastFire=System.currentTimeMillis();
			}
		}else{
			float startAngle=0;
			if(System.currentTimeMillis()-lastFire>450){
				for(int x = 0; x<current.shotsPerFrame;x++){
					
					shots.add(new Projectile(pos.x,pos.y,pos.x,pos.y, null, startAngle+=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,current.range,null, parent));
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
					|| Client.checkCollision(p.colBox) != null) {
				shotsToRemove.add(p);
			} else {
				p.update();
			}
		}
		shots.removeAll(shotsToRemove);
	}

	public void run() {
		
		
		
	}
}
