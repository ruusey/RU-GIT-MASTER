package finalproj;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import com.owlike.genson.Genson;

import finalproj.Player;

public final class Client extends PApplet {
	// OUR LEVEL IN TILES
	public static ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	public static ArrayList<ArrayList<Tile>> visibleTiles = new ArrayList<ArrayList<Tile>>();
	Genson gen = new Genson();

	// THE TILE WIDTH AND HEIGHT
	int tileSize=8;

	// CAMERA LOCATION
	int camX;
	int camY;
	public static PGraphics pg;
	// GAME ENTITIES
	public static Enemy e;
	public static Player p;
	public static SpriteLoader sl;
	PVector screenLoc;
	// LAST CHECK FOR WHEN THE CLIENT FIRED
	long lastCheck;
	int m = 8;
	
	static int screenWidth;
	static int screenHeight;
	
	public static void main(String[] args) {

		PApplet.main(Client.class.getName());
		
	}

	public void draw() {
		background(0);

		if (p != null) {

			int offsetMaxX = tiles.size() * tileSize - width;
			int offsetMaxY = tiles.size() * tileSize - height;
			int offsetMinX = 0;
			int offsetMinY = 0;

			camX = (int) (world2screen(p.pos).x - width / 2);
			camY = (int) (world2screen(p.pos).y - height / 2);

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
			drawTiles();
			drawMiscTxt();
			playerShoot();
			checkPlayerHit();
			checkEnemyHit();

			p.update();
			p.updateShots();
			p.hp.update();

			e.update();
			e.attack();
			e.updateShots();
			e.hp.update();

			// HANDLE COLIISION WITH TILES
			for (Rectangle col : checkCollision(p.colBox)) {
				if (col != null) {
					handleTerrainCollision(col);
				} else {
					// p.update();
				}

			}

		}

	}

	public void drawMiscTxt() {
		pushMatrix();
		fill(255, 100, 0);
		g.translate(camX, camY);
		text("Player X:" + p.pos.x + ", Player Y:" + p.pos.y+ " FPS: "+frameRate, 20, 20);
		popMatrix();
	}

	public void drawTiles() {
		int mapWidth = tiles.size() * tileSize;
		int mapHeight = tiles.get(0).size() * tileSize;

		int offsetX = width / 2 - Math.round(p.pos.x);

		int offsetY = height / 2 - Math.round(p.pos.y);

		int heroGridX = ((int) p.pos.x / tileSize);
		int heroGridY = ((int) p.pos.y / tileSize);
		int leftGrid = heroGridX - (width / tileSize) / 2;
		int topGrid = heroGridY - (height / tileSize) / 2;
		int rightGrid = 1 + heroGridX + (width / tileSize) / 2;
		int bottomGrid = 1 + heroGridY + (height / tileSize) / 2;
		if (leftGrid < 0) {
			leftGrid = 0;
		}
		if (topGrid < 0) {
			topGrid = 0;
		}
		if (rightGrid > tiles.size()) {
			rightGrid = tiles.size();
		}
		if (bottomGrid > tiles.get(0).size()) {
			bottomGrid = tiles.get(0).size();
		}
		if (topGrid < 0 || leftGrid < 0) {
			System.out.println();
		}
		ArrayList<ArrayList<Tile>> all = new ArrayList<ArrayList<Tile>>();
		for (int y = topGrid; y < bottomGrid; y++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int x = leftGrid; x < rightGrid; x++) {
				Tile t = tiles.get(x).get(y);
				row.add(t);

				PImage toDraw = new PImage(scale(t.img,64,64));
				
				screenLoc = world2screen(t.pos);
				image(toDraw, (screenLoc.x) , (screenLoc.y));

			}
			all.add(row);
		}
		visibleTiles = all;

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

	// HANDLE PLAYER TERRAIN COLLISION
	public void handleTerrainCollision(Rectangle col) {
		// Rectangle collision = checkCollision(p.colBox);
		if (col.width > col.height && p.vel.y < 0) {
			p.pos.y += col.getHeight();

		}
		if (col.width > col.height && p.vel.y > 0) {
			p.pos.y -= col.getHeight();

		}
		if (col.height > col.width && p.vel.x < 0) {
			p.pos.x += col.getWidth();

		}
		if (col.height > col.width && p.vel.x > 0) {
			p.pos.x -= col.getWidth();

		}
	}

	// HELPER FOR RECTANGLE COLLISION TERRAIN
	public static ArrayList<Rectangle> checkCollision(Rectangle boundingBox) {
		ArrayList<Rectangle> collisions = new ArrayList<Rectangle>();
		for (ArrayList<Tile> t1 : visibleTiles) {
			for (Tile t : t1) {
				if (t.takesCol && boundingBox.intersects(t.colBox)) {
					collisions.add(t.colBox.intersection(p.colBox));

				}
			}
		}
		return collisions;
	}

	public static Rectangle checkSimpleCollision(Rectangle boundingBox) {

		for (ArrayList<Tile> t1 : visibleTiles) {
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
		PVector offset = PVector.sub(p.pos, new PVector(screenWidth / 2,
				screenHeight / 2));
		return PVector.sub(worldCoord, offset);
	}

	// EVERY FRAME HANDLE POTENTIAL SHOOTING
	public void playerShoot() {

		if (p.firing && System.currentTimeMillis() - lastCheck > 100) {

			float angle = (float) -Math.atan2(mouseX - (width / 2), mouseY
					- (height / 2))
					+ PI / 2;
			float angleABS = (angle) - PI / 2;
			if (angleABS >= -3 * PI / 4 && angleABS <= -PI / 4) {

			} else if (angleABS > -PI / 4 && angleABS <= PI / 4) {
				//p.img = sl.getSprite(0, 2);
			} else if (angleABS > PI / 4 && angleABS <= 3 * PI / 4) {
				//p.img = sl.getSprite(4, 1);
			} else if (angleABS >= 3 * PI / 4 && angleABS <= PI) {
				//p.img = sl.getSprite(7, 0);
			} else {
				//p.img = sl.getSprite(7, 0);
			}
			System.out.println(angleABS);
			PVector vel1 = PVector.fromAngle(angle);

			p.shots.add(new Projectile(p.pos.x, p.pos.y, p.pos.x, p.pos.y,
					vel1, angle - 0.15f, 32, 10, 10.0f, 400.0f, sl.getSprite("86.png", 1, 7), this));
			p.shots.add(new Projectile(p.pos.x, p.pos.y, p.pos.x, p.pos.y,
					vel1, angle + 0.15f, 32, 10, 10.0f, 400.0f, sl.getSprite("86.png", 1, 7), this));
			p.shots.add(new Projectile(p.pos.x, p.pos.y, p.pos.x, p.pos.y,
					vel1, angle, 32, 10, 10.0f, 400.0f, sl.getSprite("86.png", 1, 7), this));

			lastCheck = System.currentTimeMillis();
		}
		// p.img=sl.getSprite(0, 0);
	}

	public void setup() {
		
		tileSize*=m;
		pg = createGraphics(width,height, JAVA2D);
			sl = new SpriteLoader(64, 64, 5, 10, "src/main/java/images");
		
		lastCheck = System.currentTimeMillis();
		p = new Player(500, 500, 550, 64, sl.getSprite("93.png",0, 3), this);
		e = new Enemy(700, 700, new PVector(0, 0), 64, 2000, sl.getSprite("93.png",0, 0),
				this);
		screenWidth = this.width;
		screenHeight = this.height;

		for (int x = 0; x < 100; x++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int y = 0; y < 100; y++) {
				if (random(1) > 0.9) {
					BufferedImage randomTile = sl.getSprite("147.png",(int) random(0, 5),
							2);

					row.add(new Tile(x * tileSize, y * tileSize, tileSize,
							tileSize, color(0, 0, 255), true, randomTile, this));
				} else {
					BufferedImage randomTile = sl.getSprite("147.png",(int) random(0, 5),
							0);

					row.add(new Tile(x * tileSize, y * tileSize, tileSize,
							tileSize, random(255), false, randomTile, this));
				}

			}
			tiles.add(row);

		}

	}

	public void settings() {
		size(700, 700,JAVA2D);
		
	}

	public void keyPressed() {
		if (key == 'w') {

			p.vel.y = -6;
			if (p.firing)
				return;
			p.img = sl.getSprite("93.png",1, 5);
		} else if (key == 's') {
			p.vel.y = 6;
			if (p.firing)
				return;
			p.img = sl.getSprite("93.png",0, 4);
		} else if (key == 'a') {
			p.vel.x = -6;
			if (p.firing)
				return;
			p.img = flipHorz(sl.getSprite("93.png",0, 3));
		} else if (key == 'd') {
			p.vel.x = 6;
			if (p.firing)
				return;
			p.img = sl.getSprite("93.png",0, 3);
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
		if (p.vel.x > 0) {

		} else if (p.vel.x < 0) {

		} else if (p.vel.y > 0) {

		} else if (p.vel.y < 0) {

		}

	}

	public void mouseReleased() {
		p.firing = false;
	}
	public static BufferedImage scale(BufferedImage i, int newW, int newH){
		Image toolkitImage = i.getScaledInstance(newW, newH, 
			      Image.SCALE_FAST);
			int width = toolkitImage.getWidth(null);
			int height = toolkitImage.getHeight(null);

			// width and height are of the toolkit image
			BufferedImage newImage = new BufferedImage(width, height, 
			      BufferedImage.TYPE_INT_ARGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(toolkitImage, 0, 0, null);
			g.dispose();
		return newImage;
	}
	public BufferedImage flipHorz(BufferedImage i){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-i.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter((BufferedImage) i, null);
	}
}