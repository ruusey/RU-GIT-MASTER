package finalproj;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import XMLObjects.EnemyObject;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemy extends Entity implements Lootable{
	public ArrayList<Shot> shots = new ArrayList<Shot>();
	public AttackPattern current;
	int frame;
	long lastFire;
	long lastPatternChange;
	int patternIndex = 0;
	boolean firing=true;
	public HealthBar hp;
	public EnemyObject en;
	public Enemy(EnemyObject en,float x, float y, PVector vel, int diameter,PApplet parent) {
		super(en.name,en.id,en.tex,x, y, vel, diameter,parent);
		current=en.patterns.get(0);
		lastFire=System.currentTimeMillis();
		lastPatternChange=System.currentTimeMillis();
		hp = new HealthBar((int)pos.x,(int)pos.y,200,diameter/2, en.hp);
		this.en=en;
	}

	public void update(float dt){
		drawPlayer();
		drawProjectiles();
		tile = Client.getTile(pos);
		
	}
	
	public void drawProjectiles(){
		for (Shot pr : shots) {
			BufferedImage image = Client.sl.getSprite(pr.tex);
			PVector screenLoc = Client.world2screen(pr.pos);
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
	public void drawPlayer(){
		PVector screenLoc = Client.world2screen(new PVector(colBox.x, colBox.y));
		parent.noFill();
		parent.fill(255, 0, 0);
		BufferedImage image = Client.sl.getSprite(tex);
		PImage toDraw = new PImage(Client.scale(image,64,64));
		screenLoc = Client.world2screen(pos);
		parent.image(toDraw,screenLoc.x-toDraw.width/2, screenLoc.y-toDraw.height/2, 64, 64);

		parent.rect(screenLoc.x - hp.green.width / 2, screenLoc.y - 50, hp.green.width, hp.green.height/2);
	}
	public void attack(){
		if(firing){
			shoot();
		}
		
		updateAttackPattern();
		
		
	}
	
	public void shoot(){
		if(current.targetPlayer){
			float angle = (float) -Math.atan2(Client.p.pos.x - pos.x, Client.p.pos.y
					- pos.y)+PApplet.PI/2;
			//ADJUST ANGLE TO AIM AT PLAYER		
			angle+=0.2;
			if(System.currentTimeMillis()-lastFire>current.shotDelay){
				for(int x = 0; x<current.shotsPerFrame;x++){
					
					shots.add(new Shot(Client.loader.getProjectileObject(current.projectileName),pos.x,pos.y,pos.x,pos.y, null, angle-=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,current.range, parent));
				}
				
				frame++;
				lastFire=System.currentTimeMillis();
			}
		}else{
			float startAngle=0;
			if(System.currentTimeMillis()-lastFire>current.shotDelay){
				for(int x = 0; x<current.shotsPerFrame;x++){
					
					shots.add(new Shot(Client.loader.getProjectileObject(current.projectileName),pos.x,pos.y,pos.x,pos.y, null, startAngle+=current.angleBetweenShots,current.shotDiameter,current.shotDamage,current.shotSpeed,current.range, parent));
				}
				
				frame++;
				lastFire=System.currentTimeMillis();
		}
			
		}
	}
	public void updateShots(float dt) {
		ArrayList<Shot> shotsToRemove = new ArrayList<Shot>();
		for (Shot p : shots) {
			if (PVector.dist(p.source, p.pos) > p.range
					|| Client.checkSimpleCollision(p.colBox) != null) {
				shotsToRemove.add(p);
			} else {
				p.update(dt);
			}
		}
		
		shots.removeAll(shotsToRemove);
	}
	public void updateAttackPattern(){
		if(en.patterns.size()>1){
													//ADD PATTERN CHANGE DELAY PARAM IN ENEMY XML OBJECT
			if(System.currentTimeMillis()-lastPatternChange>5000){
				if(patternIndex+1==en.patterns.size()){
					patternIndex=0;
					current = en.patterns.get(patternIndex);
				}else{
					patternIndex++;
					current = en.patterns.get(patternIndex);
				}
				

				
				lastPatternChange=System.currentTimeMillis();
			}
		}
		
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
		Texture lootTexture = new Texture("135.png", 2, 9);
		Lootbag loot = new Lootbag("test",1,lootTexture,pos.x,pos.y,32,32, parent);
		loot.contents[0]=Client.loader.weapons.get("Coral Bow");
		Client.loot.add(loot);
		
	}

	public void dropLoot() {
		// TODO Auto-generated method stub
		
	}
}
