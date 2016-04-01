package finalproj;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.owlike.genson.Genson;

import finalproj.Player;

public final class Client extends PApplet {

	ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	Genson gen = new Genson();
	int tileSize = 128;
	static PVector center;
	public PApplet graphics = this;
	public static Player p;
	long lastCheck;

	public static void main(String[] args) {
		
		PApplet.main(Client.class.getName());

	}

	public void draw() {
		background(0);
		if (p != null) {
			Tile tile = tiles.get((int) p.pos.x / tileSize).get((int) p.pos.y / tileSize);
			if (frameCount % 120 == 0) {
				System.out.println("  Tile " + tile.colBox.x + " : " + tile.colBox.y + " : " + tile.takesCol);
				System.out.println("Player " + p.colBox.x + " : " + p.colBox.y);

			}
			int offsetMaxX = tiles.size() * tileSize - width;
			int offsetMaxY = tiles.size() * tileSize - height;
			int offsetMinX = 0;
			int offsetMinY = 0;

			int camX = (int) (world2screen(p.pos).x - width / 2);
			int camY = (int) (world2screen(p.pos).y - height / 2);

			if (camX > offsetMaxX) {
				camX = offsetMaxX;
			} else if (camX < offsetMinX) {
				camX = offsetMinX;
			}
			if (camY > offsetMaxY) {
				camY = offsetMaxY;
			} else if (camY < offsetMinY) {
				camY = offsetMinY;
			}

			g.translate(-camX, -camY);

			int mapWidth = tiles.size() * tileSize;
			int mapHeight = tiles.get(0).size() * tileSize;

			int offsetX = width / 2 - Math.round(p.pos.x);
			offsetX = Math.min(offsetX, 0);
			offsetX = Math.max(offsetX, width - mapWidth);

			int offsetY = height / 2 - Math.round(p.pos.y);
			offsetY = Math.min(offsetY, 0);
			offsetY = Math.max(offsetY, height - mapHeight);

		

			for (int y = 0; y < tiles.get(0).size(); y++) {
				for (int x = 0; x < tiles.size(); x++) {
					Tile t = tiles.get(x).get(y);
					if (t.takesCol) {
						
						fill(0, 255, 0);
						rect((x * tileSize) + offsetX, (y * tileSize) + offsetY, tileSize, tileSize);
						stroke(255,0,0);
						noFill();
						PVector screenLoc = world2screen(new PVector(t.colBox.x,t.colBox.y));
						rect(screenLoc.x, screenLoc.y, tileSize,tileSize);
					} else {
						
						fill(t.color);
						rect((x * tileSize) + offsetX, (y * tileSize) + offsetY, tileSize, tileSize);
						stroke(255,0,0);
						noFill();
						PVector screenLoc = world2screen(new PVector(t.colBox.x,t.colBox.y));
						rect(screenLoc.x, screenLoc.y, tileSize,tileSize);
					}

				}
			}

			noFill();
			PVector screenLoc = world2screen(new PVector(p.colBox.x,p.colBox.y));
			rect(screenLoc.x, screenLoc.y, p.diameter,p.diameter);
			fill(255, 0, 0);
			screenLoc = world2screen(p.pos);
			ellipse(screenLoc.x, screenLoc.y, 40, 40);

			pushMatrix();
			fill(255, 100, 0);
			g.translate(camX, camY);
			text("Player X:" + p.pos.x + ", Player Y:" + p.pos.y, 20, 20);
			popMatrix();

			if(checkCollision(p)!=null){
				Rectangle col =checkCollision(p); 
				if(col.width>col.height ){
					if(p.pos.y>col.y){
						p.pos.y+=5;
					}else{
						p.pos.y-=5;
						
						
					}
				}else if(col.height>col.width ){
					
					if(p.pos.x>col.x){
						p.pos.x+=5;
					}else{
						p.pos.x-=5;
					}
					
					
				}
			}
			else {
				p.update();
			}

			tryShoot();
			p.updateShots();

			for (Projectile pr : p.shots) {
				
				fill(0, 255, 0);
				screenLoc = world2screen(pr.pos);
				ellipse(screenLoc.x, screenLoc.y, 20, 20);
				

			}

		}

	}
	public Rectangle checkCollision(Player p){
		for(ArrayList<Tile> t1 : tiles){
			for(Tile t: t1){
				if (t.takesCol && p.colBox.intersects(t.colBox)) {
					return t.colBox.intersection(p.colBox);

				}
			}
		}
		return null;
	}

	public PVector world2screen(PVector worldCoord) {
		PVector offset = PVector.sub(p.pos, new PVector(width/2, height/2));
		return PVector.sub(worldCoord, offset);
	}

	public void setup() {
		lastCheck = System.currentTimeMillis();
		p = new Player(500,500, 20, 40);

		for (int x = 0; x < 50; x++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int y = 0; y < 50; y++) {
				if (random(1) > 0.9) {
					row.add(new Tile(x * tileSize, y * tileSize, tileSize, tileSize, color(0, 0, 255), true));
				} else {
					row.add(new Tile(x * tileSize, y * tileSize, tileSize, tileSize, random(255), false));
				}

			}
			tiles.add(row);

		}
	}

	

	public void settings() {
		size(700, 700);
	}

	public void keyPressed() {
		if (key == 'w') {

			p.vel.y = -6;

		} else if (key == 's') {
			p.vel.y = 6;
		} else if (key == 'a') {
			p.vel.x = -6;
		} else if (key == 'd') {
			p.vel.x = 6;
		} else if (key == ' ') {
			tileSize -= 10;
		}

	}

	public void keyReleased() {
		if (key == 'w') {
			p.vel.y = 0;

		} else if (key == 's') {
			p.vel.y = 0;
		} else if (key == 'a') {
			p.vel.x = 0;
		} else if (key == 'd') {
			p.vel.x = 0;
		}
	}

	public void mousePressed() {
		p.firing = true;
	}

	public void tryShoot() {
		if (p.firing && System.currentTimeMillis() - lastCheck > 100) {

			float angle = (float) -Math.atan2(mouseX - (width / 2), mouseY - (height / 2)) + PI / 2;
			System.out.println(angle);
			float xVelocity = (5) * cos(angle);
			float yVelocity = (5) * sin(angle);
			PVector vel = new PVector(xVelocity, yVelocity);

			p.shots.add(new Projectile(p.pos.x, p.pos.y, vel, 10));
			lastCheck = System.currentTimeMillis();
		}
	}

	public void mouseReleased() {
		p.firing = false;
	}

}