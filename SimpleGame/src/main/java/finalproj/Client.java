package finalproj;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.owlike.genson.Genson;

import finalproj.Player;

public final class Client extends PApplet {

	static ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	Genson gen = new Genson();
	int tileSize = 128;
	static PVector center;
	public PApplet graphics = this;
	public static Player p;
	long lastCheck;
	int camX;
	int camY;
	PVector screenLoc;
	Enemy e;

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

			p.update();
			p.updateShots();

			e.attack();
			e.updateShots();

			drawTiles();
			drawPlayer();
			drawMiscTxt();
			playerShoot();
			drawProjectiles();

			drawEnemy();
			drawEProjectiles();

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
					stroke(255, 0, 0);
					noFill();
					PVector screenLoc = world2screen(new PVector(t.colBox.x, t.colBox.y));
					// rect(screenLoc.x, screenLoc.y, tileSize, tileSize);
				} else {

					fill(t.color);
					rect((x * tileSize) + offsetX, (y * tileSize) + offsetY, tileSize, tileSize);
					stroke(255, 0, 0);
					noFill();
					PVector screenLoc = world2screen(new PVector(t.colBox.x, t.colBox.y));
					// rect(screenLoc.x, screenLoc.y, tileSize, tileSize);
				}

			}
		}
	}

	public void drawProjectiles() {
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for (Projectile pr : p.shots) {

			if (checkCollision(pr.colBox) != null) {
				// System.out.println("Shot Collided");
				shotsToRemove.add(pr);
			} else {
				fill(0, 255, 0);
				screenLoc = world2screen(pr.pos);
				ellipse(screenLoc.x, screenLoc.y, 20, 20);
				noFill();
				screenLoc = world2screen(new PVector(pr.colBox.x, pr.colBox.y));
				// rect(screenLoc.x, screenLoc.y, pr.width, pr.height);
			}

		}
		p.shots.removeAll(shotsToRemove);
	}

	public void drawEProjectiles() {
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		List<Projectile> shoots = e.shots.subList(0, e.shots.size());
		for (Projectile pr : shoots) {

			fill(0, 255, 0);
			screenLoc = world2screen(pr.pos);
			ellipse(screenLoc.x, screenLoc.y, e.current.shotDiameter, e.current.shotDiameter);
			noFill();
			screenLoc = world2screen(new PVector(pr.colBox.x, pr.colBox.y));
			// rect(screenLoc.x, screenLoc.y, pr.width, pr.height);
		}

	}
	

	public void drawEnemy() {
		PVector screenLoc = world2screen(new PVector(e.colBox.x, e.colBox.y));
		graphics.noFill();
		// rect(screenLoc.x, screenLoc.y, p.width, p.height);
		fill(255, 0, 0);
		screenLoc = world2screen(e.pos);
		ellipse(screenLoc.x, screenLoc.y, e.width, e.height);
	}

	public void drawPlayer() {
		PVector screenLoc = world2screen(new PVector(p.colBox.x, p.colBox.y));
		graphics.noFill();
		// rect(screenLoc.x, screenLoc.y, p.width, p.height);
		fill(0, 0, 255);
		screenLoc = world2screen(p.pos);
		ellipse(screenLoc.x, screenLoc.y, 40, 40);
	}

	public void handleTerrainCollision(Rectangle col) {
		// Rectangle col = checkCollision(p.colBox);
		if (p.vel.x > 0) {
			p.pos.x -= col.getWidth();
			return;
		}

		if (p.vel.y > 0) {
			p.pos.y -= col.getHeight();
			return;
		}

		if (p.vel.x < 0) {
			p.pos.x += col.getWidth();
			return;
		}

		if (p.vel.y < 0) {
			p.pos.y += col.getHeight();
			return;
		}
	}

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

	public PVector world2screen(PVector worldCoord) {
		PVector offset = PVector.sub(p.pos, new PVector(width / 2, height / 2));
		return PVector.sub(worldCoord, offset);
	}

	public void setup() {
		frameRate(60);
		lastCheck = System.currentTimeMillis();
		p = new Player(500, 500, 20, 40);
		e = new Enemy(700, 700, new PVector(0, 0), 60);

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

	public void playerShoot() {

		if (p.firing && System.currentTimeMillis() - lastCheck > 100) {

			float angle = (float) -Math.atan2(mouseX - (width / 2), mouseY - (height / 2)) + PI / 2;

			PVector vel1 = PVector.fromAngle(angle);

			p.shots.add(new Projectile(p.pos.x, p.pos.y, p.pos.x, p.pos.y, vel1, angle, 20, 10, 10.0f, 400.0f));

			
			lastCheck = System.currentTimeMillis();
		}
	}

	public void mouseReleased() {
		p.firing = false;
	}

}