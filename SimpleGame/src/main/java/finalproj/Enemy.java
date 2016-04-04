package finalproj;

import java.util.ArrayList;

import processing.core.PVector;

public class Enemy extends Movement implements Runnable{
	public ArrayList<Projectile> shots = new ArrayList<Projectile>();
	public AttackPattern current;
	int frame;
	long lastFire;
	public Enemy(float x, float y, PVector vel, int diameter) {
		super(x, y, vel, diameter);
		attackPattern1();
		lastFire=System.currentTimeMillis();
	}

	public void attackPattern1() {
		int shotDelay = 100;
		boolean targetPlayer = true;
		int shotsPerFrame = 10;
		float angleBetweenShots = 1f;
		int shotDamage = 60;
		int shotDiameter = 40;
		float shotSpeed = 8;
		current = new AttackPattern(shotDelay, targetPlayer,shotsPerFrame,angleBetweenShots,shotDamage,shotDiameter,shotSpeed);
		
	}

	public void attackPattern2() {

	}

	public void attackPattern3() {

	}
	public void attack(){
		float startAngle=0;
		if(System.currentTimeMillis()-lastFire>450){
			for(int x = 0; x<current.shotsPerFrame;x++){
				
				shots.add(new Projectile(pos.x,pos.y,pos.x,pos.y, null, startAngle+=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,400.0f));
			}
			
			frame++;
			lastFire=System.currentTimeMillis();
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
		while(true){
			attack();
			updateShots();
			
			
		}
		
		
	}
}
