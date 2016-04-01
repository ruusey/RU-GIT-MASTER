package finalproj;

import processing.core.PApplet;
import processing.core.PVector;
import test.ClientHandler;

import java.util.ArrayList;

import finalproj.Player;

public final class Client extends PApplet {

	ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();

	int tileSize = 64;
	static PVector center;

	public static Player p;
	long lastCheck;

	public static void main(String[] args) {
		PApplet.main(Client.class.getName());

	}

	public void draw() {
		background(0);
		if (p != null) {
			int offsetMaxX = tiles.size()*tileSize - width;
			int offsetMaxY = tiles.size()*tileSize - height;
			int offsetMinX = 0;
			int offsetMinY = 0;
			int camX = (int) (p.pos.x - width / 2);
			int camY = (int) (p.pos.y - height / 2);
			if (camX > offsetMaxX){
				 camX = offsetMaxX;
			}else if (camX < offsetMinX){
				camX = offsetMinX;
			}
			if (camY > offsetMaxY){
				 camY = offsetMaxY;
			}else if (camX < offsetMinX){
				camY = offsetMinY;
			} 
			
			g.translate(-camX, -camY);   
			int mapWidth = tiles.size() * tileSize;
			int mapHeight = tiles.get(0).size() * tileSize;
			
			int offsetX = width/2 - Math.round(p.pos.x) ; 
			offsetX = Math.min(offsetX, 0); 
			offsetX = Math.max(offsetX, width - mapWidth); 
			int offsetY = height / 2 - Math.round(p.pos.y); 
			offsetY = Math.min(offsetY, 0);
			offsetY = Math.max(offsetY, height - mapHeight);
			int firstTileX = (-offsetX) / tileSize;
			// System.out.println("" + firstTileX);
			int lastTileX = firstTileX + (width / tileSize) + 1;
			

			for (int y = 0; y < tiles.get(0).size(); y++) {
				for (int x = 0; x<  tiles.size(); x++) {
					
					fill(tiles.get(x).get(y).color);
					rect((x * tileSize) + offsetX, (y * tileSize) + offsetY,
							tileSize, tileSize);
				}
			}

		
			fill(255, 0, 0);
			ellipse(p.pos.x, p.pos.y, 40, 40);
			p.update();
			tryShoot();
			p.updateShots(); 
			for (Projectile pr : p.shots) {

				fill(0, 255, 0);
				ellipse(pr.pos.x, pr.pos.y, 20, 20);
			}

		}

	}

	public void setup() {
		center = new PVector(width / 2, height / 2);
		lastCheck = System.currentTimeMillis();
		p = new Player(width / 2, height / 2, 20);

		for (int x = 0; x < 100; x++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int y = 0; y < 100; y++) {

				row.add(new Tile(x * tileSize, y * tileSize, tileSize,
						tileSize, random(244)));

			}
			tiles.add(row);

		}
	}

	public void settings() {
		size(700, 700);
	}

	public void keyPressed() {
		if (key == 'w') {

			p.vel.y = -5;

		} else if (key == 's') {
			p.vel.y = 5;
		} else if (key == 'a') {
			p.vel.x = -5;
		} else if (key == 'd') {
			p.vel.x = 5;
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

			float angle = (float) -Math.atan2(mouseX - (width / 2), mouseY
					- (height / 2))
					+ PI / 2;
			System.out.println(angle);
			float xVelocity = (3) * cos(angle);
			float yVelocity = (3) * sin(angle);
			PVector vel = new PVector(xVelocity, yVelocity);

			p.shots.add(new Projectile(p.pos.x, p.pos.y, vel, 10));
			lastCheck = System.currentTimeMillis();
		}
	}

	public void mouseReleased() {
		p.firing = false;
	}

}