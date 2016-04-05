package finalproj;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.Rectangle;
import java.util.ArrayList;
import com.owlike.genson.Genson;

import finalproj.Player;

public final class Client extends PApplet {
	// OUR LEVEL IN TILES
	public static ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	Genson gen = new Genson();

	// THE TILE WIDTH AND HEIGHT
	int tileSize = 128;

	// CAMERA LOCATION
	int camX;
	int camY;

	// GAME ENTITIES
	public static Enemy e;
	public static Player p;

	PVector screenLoc;
	// LAST CHECK FOR WHEN THE CLIENT FIRED
	long lastCheck;

	static int screenWidth;
	static int screenHeight;
	
	public static void main(String[] args) {

		PApplet.main(Client.class.getName());

	}

	public void draw() {
		background(0);
		if (p != null) {

			// GIVE US SOME INFO ABOUT THE PLAYER EVERY ONCE IN A WHILE
			Tile tile = tiles.get((int) p.pos.x / tileSize).get((int) p.pos.y / tileSize);

			if (frameCount % 120 == 0) {
				System.out.println("  Tile " + tile.colBox.x + " : " + tile.colBox.y + " : " + tile.takesCol);
				System.out.println("Player " + p.colBox.x + " : " + p.colBox.y);

			}

			int offsetMaxX = tiles.size() * tileSize - width;
			int offsetMaxY = tiles.size() * tileSize - height;
			int offsetMinX = 0;
			int offsetMinY = 0;

			camX = (int) (world2screen(p.pos).x - width / 2);
			camY = (int) (world2screen(p.pos).y - height / 2);

			// if (camX > offsetMaxX) {
			// camX = offsetMaxX;
			// } else if (camX < offsetMinX) {
			// camX = offsetMinX;
			// }
			// if (camY > offsetMaxY) {
			// camY = offsetMaxY;
			// } else if (camY < offsetMinY) {
			// camY = offsetMinY;
			// }

			g.translate(-camX, -camY);

		

			

			drawTiles();
			drawMiscTxt();
			playerShoot();
			drawProjectiles();
			checkPlayerHit();
			checkEnemyHit();
		
			drawEProjectiles();
			
			p.update();
			p.updateShots();
			p.hp.update();
			
			e.update();
			e.attack();
			e.updateShots();
			e.hp.update();
			// HANDLE COLIISION WITH TILES
			Rectangle col = checkCollision(p.colBox);
			if (col != null) {
				handleTerrainCollision(col);
			} else {
				// p.update();
			}

		}

	}

	public void drawMiscTxt() {
		pushMatrix();
		fill(255, 100, 0);
		g.translate(camX, camY);
		text("Player X:" + p.pos.x + ", Player Y:" + p.pos.y, 20, 20);
		popMatrix();
	}

	public void drawTiles() {
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

				} else {

					fill(t.color);
					rect((x * tileSize) + offsetX, (y * tileSize) + offsetY, tileSize, tileSize);

				}

			}
		}
	}

	// CHECK IF THE ENEMY SHOTS HIT THE PLAYER
	public void checkPlayerHit() {
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for (Projectile pr : e.shots) {
			if (pr.colBox.intersects(p.colBox) && !pr.isHit) {
				pr.isHit = true;
				p.hp.Hit(pr.dmg);
				shotsToRemove.add(pr);
			}
		}
		e.shots.removeAll(shotsToRemove);
	}

	// CHECK IF THE PLAYER SHOTS HIT THE ENEMY
	public void checkEnemyHit() {
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for (Projectile pr : p.shots) {
			if (pr.colBox.intersects(e.colBox) && !pr.isHit) {
				pr.isHit = true;
				e.hp.Hit(pr.dmg);
				shotsToRemove.add(pr);
			}
		}
		p.shots.removeAll(shotsToRemove);
	}

	// DRAW PLAYER PROJECTILES
	public void drawProjectiles() {
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for (Projectile pr : p.shots) {

			if (checkCollision(pr.colBox) != null) {

				shotsToRemove.add(pr);
			} else {
				fill(160, 32, 240);
				screenLoc = world2screen(pr.pos);
				ellipse(screenLoc.x, screenLoc.y, 20, 20);

			}

		}
		p.shots.removeAll(shotsToRemove);
	}

	// DRAW ENEMY PROJECTILES
	public void drawEProjectiles() {
		for (Projectile pr : e.shots) {

			fill(255, 165, 0);
			screenLoc = world2screen(pr.pos);
			ellipse(screenLoc.x, screenLoc.y, e.current.shotDiameter, e.current.shotDiameter);
		}

	}

	// DRAW THE ENEMY AND HIS HEALTH BAR
	public void drawEnemy() {
		PVector screenLoc = world2screen(new PVector(e.colBox.x, e.colBox.y));
		noFill();
		fill(255, 0, 0);
		screenLoc = world2screen(e.pos);
		ellipse(screenLoc.x, screenLoc.y, e.width, e.height);

		rect(width / 2 - e.hp.green.width / 2, e.hp.green.y - 50, e.hp.green.width, e.hp.green.height);
	}

	// DRAW THE PLAYER AND HIS HEALTH BAR


	// HANDLE PLAYER TERRAIN COLLISION
	public void handleTerrainCollision(Rectangle col) {
		Rectangle collision = checkCollision(p.colBox);
		if (collision.width>collision.height && p.vel.y<0) {
			p.pos.y += col.getHeight();
			
		}else if(collision.width>collision.height && p.vel.y>0){
			p.pos.y -= col.getHeight();
			
		}

		if (collision.height>collision.width && p.vel.x<0) {
			p.pos.x += col.getWidth();
			
		}else if(collision.width>collision.height && p.vel.x>0){
			p.pos.x -= col.getWidth();
			
		}
	}

	// HELPER FOR RECTANGLE COLLISION TERRAIN
	public static Rectangle checkCollision(Rectangle boundingBox) {
		for (ArrayList<Tile> t1 : tiles) {
			for (Tile t : t1) {
				if (t.takesCol && boundingBox.intersects(t.colBox)) {
					return t.colBox.intersection(p.colBox);

				}
			}
		}
		return null;
	}

	// CONVERT WORLD COORDINATES TO FIT IN THE CAMERA
	public static PVector world2screen(PVector worldCoord) {
		PVector offset = PVector.sub(p.pos, new PVector(screenWidth / 2, screenHeight / 2));
		return PVector.sub(worldCoord, offset);
	}

	// EVERY FRAME HANDLE POTENTIAL SHOOTING
	public void playerShoot() {

		if (p.firing && System.currentTimeMillis() - lastCheck > 100) {

			float angle = (float) -Math.atan2(mouseX - (width / 2), mouseY - (height / 2)) + PI / 2;

			PVector vel1 = PVector.fromAngle(angle);

			p.shots.add(new Projectile(p.pos.x, p.pos.y, p.pos.x, p.pos.y, vel1, angle, 20, 10, 10.0f, 400.0f, this));

			lastCheck = System.currentTimeMillis();
		}
	}

	public void setup() {
		frameRate(60);
		lastCheck = System.currentTimeMillis();
		p = new Player(500, 500, 550, 40, this);
		e = new Enemy(700, 700, new PVector(0, 0), 60, 2000, this);
		screenWidth=this.width;
		screenHeight=this.height;

		for (int x = 0; x < 50; x++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int y = 0; y < 50; y++) {
				if (random(1) > 0.9) {
					row.add(new Tile(x * tileSize, y * tileSize, tileSize, tileSize, color(0, 0, 255), true, this));
				} else {
					row.add(new Tile(x * tileSize, y * tileSize, tileSize, tileSize, random(255), false, this));
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

	public void mouseReleased() {
		p.firing = false;
	}

}