package finalproj;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import XMLObjects.EnemyObject;
import XMLObjects.PlayerObject;
import XMLObjects.ProjectileObject;
import XMLObjects.WeaponObject;

import finalproj.Player;

public final class Client extends PApplet {
	// OUR LEVEL IN TILES
	public static ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
	public static ArrayList<ArrayList<Tile>> visibleTiles = new ArrayList<ArrayList<Tile>>();
	// ALL ITEMS IN THE GAME (WIP)
	public static ArrayList<Entity> gameObject = new ArrayList<Entity>();
	// ALL ENEMIES AND VISIBLE ENEMIES
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<Enemy> visibleEnemies = new ArrayList<Enemy>();
	public static ArrayList<Lootbag> loot = new ArrayList<Lootbag>();
	// THE TILE WIDTH AND HEIGHT
	static int tileSize = 64;

	// CAMERA LOCATION
	int camX;
	int camY;

	// GAME ENTITIES
	public static Enemy e;
	public static Player p;
	public static SpriteLoader sl;
	public static GUI gui;
	public static XMLParse loader;

	// HELPER FOR POSITIONING ELEMENTS
	public PVector screenLoc;

	// LAST CHECK FOR WHEN THE CLIENT FIRED
	long lastCheck;

	// MAGNIFICATION OF GAME OBJECTS
	int m = 1;

	// FOR WORLD2SCREEN FUNCTIONALITY
	static int screenWidth;
	static int screenHeight;

	public static void main(String[] args) {

		PApplet.main(Client.class.getName());

	}

	public void draw() {

		background(0);
		if (p != null) {

			drawTiles();

			drawMiscTxt();
			playerShoot();

			ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
			for (Enemy enemy : enemies) {

				if (tilesContains(enemy.tile, visibleTiles)) {
					if (enemy.hp.actualHealth < 1) {
						toRemove.add(enemy);
					} else {
						enemy.firing = true;
						enemy.update(frameRate);
						enemy.attack();
						enemy.updateShots(frameRate);
						enemy.hp.update();
						checkPlayerHit(enemy);
						checkEnemyHit(enemy);
					}

				} else {
					enemy.shots.clear();
					enemy.firing = false;
				}

			}
			for (Enemy e : toRemove) {
				e.death();
			}
			p.update(frameRate);
			p.updateShots(frameRate);
			p.hp.update();

			gui.update(p);
			drawLoot();
			// HANDLE COLIISION WITH TILES

			ArrayList<Rectangle> collisions = checkCollision(p.colBox);
			if (collisions != null) {
				for (Rectangle col : collisions) {
					if (col != null) {
						p.handleTerrainCollision(col);
					}

				}
			}

		}

	}

	// DRAW MISC TEXT
	public void drawMiscTxt() {
		pushMatrix();
		textSize(10);
		fill(255, 100, 0);
		g.translate(camX, camY);
		text("Player X:" + p.pos.x + ", Player Y:" + p.pos.y + " FPS: " + frameRate, 20, 20);
		popMatrix();
	}

	public void drawLoot() {
		if (loot.size() == 0)
			return;
		for (Lootbag bag : loot) {
			if (bag.tile.posIn(p.pos)) {
				for (Item i : bag.contents) {
					if (i == null)
						continue;
					BufferedImage image = Client.sl.getSprite(i.tex);
					PImage toDraw = new PImage(scale(image, 32, 32));

					image(toDraw, gui.pos.x, gui.pos.y + gui.height / 2, 32, 32);
				}

			}
			BufferedImage image = Client.sl.getSprite(bag.tex);
			PImage toDraw = new PImage(Client.scale(image, 32, 32));
			PVector screenLoc = Client.world2screen(bag.pos);
			image(toDraw, screenLoc.x - toDraw.width / 2, screenLoc.y - toDraw.height / 2, 32, 32);
		}
	}

	// DRAW THE MAP TILES
	public void drawTiles() {

		int heroGridX = ((int) p.pos.x / tileSize);
		int heroGridY = ((int) p.pos.y / tileSize);
		int leftGrid = (heroGridX - (width / tileSize) / 2) - 1;
		int topGrid = (heroGridY - (height / tileSize) / 2) - 1;
		int rightGrid = 1 + heroGridX + (width / tileSize) / 2;
		int bottomGrid = 2 + heroGridY + (height / tileSize) / 2;

		// MAKE SURE WE DONT GO OUTSIDE THE BOUNDS OF THE ARRAYLIST
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
		ArrayList<ArrayList<Tile>> all = new ArrayList<ArrayList<Tile>>();
		for (int y = topGrid; y < bottomGrid; y++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int x = leftGrid; x < rightGrid; x++) {
				Tile t = tiles.get(x).get(y);
				row.add(tiles.get(x).get(y));
				BufferedImage image = Client.sl.getSprite(t.tex);

				PImage toDraw = new PImage(scale(image, 64, 64));

				screenLoc = world2screen(t.pos);

				image(toDraw, (screenLoc.x), (screenLoc.y));

			}
			all.add(row);
		}
		visibleTiles = all;

	}

	// CHECK IF THE ENEMY SHOTS HIT THE PLAYER
	// WILL MOVE TO PLAYER CLASS WHEN ADDING LIST ENEMIES
	public void checkPlayerHit(Enemy e) {
		if (e == null || e.shots.size() == 0)
			return;
		ArrayList<Shot> shotsToRemove = new ArrayList<Shot>();
		ArrayList<Tile> testTiles = getAdjacentTiles(getTile(p.pos));
		if (testTiles == null)
			return;
		for (Shot pr : e.shots) {
			if (testTiles.contains(pr.tile)) {
				if (pr.colBox.intersects(p.colBox) && !pr.isHit) {
					pr.isHit = true;
					float percent = pr.dmg * 0.85f;
					int damage = pr.dmg - p.p.def;
					screenLoc = world2screen(p.pos);
					fill(0, 255, 0);
					textSize(30);
					if (percent > damage) {
						System.out.println("Hit Player: " + (int) percent);
						p.hp.Hit((int) percent);
						text((int) percent, screenLoc.x, screenLoc.y - 32);
					} else {
						System.out.println("Hit Player: " + damage);
						p.hp.Hit((int) damage);
						text((int) damage, screenLoc.x, screenLoc.y - 32);
					}

					shotsToRemove.add(pr);
				}
			}

		}
		e.shots.removeAll(shotsToRemove);
	}

	// CHECK IF THE PLAYER SHOTS HIT THE ENEMY
	// MOVE TO ENEMY CLASS WHEN VISIBLE PLAYERS LIST
	public void checkEnemyHit(Enemy e) {
		if (e == null)
			return;
		ArrayList<Shot> shotsToRemove = new ArrayList<Shot>();
		ArrayList<Tile> testTiles = getAdjacentTiles(getTile(e.pos));
		if (testTiles == null)
			return;
		for (Shot pr : p.shots) {

			if (testTiles.contains(pr.tile)) {
				if (pr.colBox.intersects(e.colBox) && !pr.isHit) {
					pr.isHit = true;
					float percent = pr.dmg * 0.85f;
					int damage = pr.dmg - e.en.def;
					screenLoc = world2screen(e.pos);
					fill(0, 255, 0);
					textSize(30);

					if (percent > damage) {
						e.hp.Hit((int) percent);
						text((int) percent, screenLoc.x, screenLoc.y - 32);
					} else {
						e.hp.Hit(damage);
						text((int) damage, screenLoc.x, screenLoc.y - 32);
					}

					shotsToRemove.add(pr);
				}
			}

		}
		p.shots.removeAll(shotsToRemove);
	}

	// HELPER FOR RECTANGLE COLLISION WITH MAP TERRAIN
	// MOVING TO PLAYER CLASS WHEN ADDED VISIBLE ENTITIES
	public static ArrayList<Rectangle> checkCollision(Rectangle boundingBox) {
		ArrayList<Rectangle> collisions = new ArrayList<Rectangle>();
		ArrayList<Tile> testTiles = getAdjacentTiles(getTile(p.pos));
		if (testTiles == null)
			return null;
		for (Tile t : testTiles) {
			if (t.takesCol && boundingBox.intersects(t.colBox)) {
				collisions.add(t.colBox.intersection(p.colBox));

			}
		}
		return collisions;
	}

	// HANDLE A SIMPLE COLLISION AABB
	// SAVES TIME WHEN NOT CHECKING FOR PLAYER TERRAIN COLLISION
	public static Rectangle checkSimpleCollision(Rectangle boundingBox) {
		ArrayList<Tile> testTiles = getAdjacentTiles(getTile(new PVector(boundingBox.x, boundingBox.y)));
		if (testTiles == null)
			return null;
		for (Tile t : testTiles) {
			if (t.takesCol && boundingBox.intersects(t.colBox)) {
				return t.colBox.intersection(p.colBox);

			}
		}
		return null;
	}

	// CONVERT WORLD COORDINATES TO FIT IN THE CAMERA
	public static PVector world2screen(PVector worldCoord) {
		PVector offset = PVector.sub(p.pos, new PVector((screenWidth / 2) - 100, screenHeight / 2));
		return PVector.sub(worldCoord, offset);
	}

	// EVERY FRAME HANDLE POTENTIAL SHOOTING
	public void playerShoot() {

		if (p.firing && System.currentTimeMillis() - lastCheck > 10 * (p.w.rateOfFire * p.p.dex)) {
			screenLoc = world2screen(p.pos);
			float angle = (float) -Math.atan2(mouseX - (screenLoc.x), mouseY - (screenLoc.y)) + PI / 2;
			float angleABS = (angle) - PI / 2;
			if (angleABS >= -3 * PI / 4 && angleABS <= -PI / 4) {
				// p.img = sl.getSprite("93.png", 0, 5);
			} else if (angleABS > -PI / 4 && angleABS <= PI / 4) {
				// p.img = sl.getSprite("93.png", 0, 4);
			} else if (angleABS > PI / 4 && angleABS <= 3 * PI / 4) {
				// p.img = flipHorz(sl.getSprite("93.png", 0, 3));
			} else if (angleABS >= 3 * PI / 4 && angleABS <= PI) {
				// p.img = sl.getSprite("93.png", 0, 3);
			} else {
				// p.img = sl.getSprite("93.png", 0, 5);
			}
			// ADD PROJECTILES
			PVector vel1 = PVector.fromAngle(angle);
			WeaponObject equipped = p.w;
			ProjectileObject toShoot = loader.getProjectileObject(p.w.projectile);
			float mult = (float) (0.5 + p.p.att / 50.0f);
			float damage = random(equipped.minDmg, equipped.maxDmg) * mult;
			p.shots.add(new Shot(toShoot, p.pos.x, p.pos.y, p.pos.x, p.pos.y, vel1, angle - equipped.angleBetweenShots,
					toShoot.size, (int) damage, equipped.speed, (float) equipped.range, this));
			p.shots.add(new Shot(toShoot, p.pos.x, p.pos.y, p.pos.x, p.pos.y, vel1, angle + equipped.angleBetweenShots,
					toShoot.size, (int) damage, equipped.speed, (float) equipped.range, this));

			lastCheck = System.currentTimeMillis();
		}
		// p.img=sl.getSprite(0, 0);
	}

	public void setup() {
		
		// MAKE THE TILE SIZE MAGNIFIED
		loader = new XMLParse("src/main/java/xml/items.xml");
		tileSize *= m;

		sl = new SpriteLoader(64, 64, 5, 10, "src/main/java/images");
		// CONSTRUCT TILE MAP
		for (int x = 0; x < 100; x++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int y = 0; y < 100; y++) {
				if (random(1) > 0.9) {

					row.add(new Tile(loader.getGameObject("Wall2"), x * tileSize, y * tileSize, tileSize, tileSize,
							color(0, 0, 255), true, this));
				} else {

					row.add(new Tile(loader.getGameObject("Wall1"), x * tileSize, y * tileSize, tileSize, tileSize,
							random(255), false, this));
				}

			}
			tiles.add(row);

		}
		// FOR SHOOTING TIMING
		lastCheck = System.currentTimeMillis();

		// LOAD SPRITES IN DIRECTORY
		PlayerObject pl = loader.getPlayerObject("Archer");
		p = new Player(pl, 500, 500, 64, this);

		for (int i = 0; i < 5; i++) {
			EnemyObject eo = loader.getEnemyObject("Enemy1");
			Enemy en = new Enemy(eo, random(1000), random(1000), new PVector(0, 0), 64, this);
			enemies.add(en);
		}
		// NEW GUI (WIP)
		gui = new GUI(p.p.hp, p.p.hp, new PVector(width - 200, 0), 200, height, this);

		// STATIC VAR FOR WIDTH AND HEIGHT
		screenWidth = this.width;
		screenHeight = this.height;

	}

	public void settings() {
		size(800, 700);

	}

	public void keyPressed() {
		if (key == 'w') {
			p.vel.y = -6;
			if (p.firing)
				return;
			// p.tex = sl.getSprite("93.png", 0, 5);
		} else if (key == 's') {
			p.vel.y = 6;
			if (p.firing)
				return;
			// p.img = sl.getSprite("93.png", 0, 4);
		} else if (key == 'a') {
			p.vel.x = -6;
			if (p.firing)
				return;
			// p.img = flipHorz(sl.getSprite("93.png", 0, 3));
		} else if (key == 'd') {
			p.vel.x = 6;
			if (p.firing)
				return;
			// p.img = sl.getSprite("93.png", 0, 3);
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

	// CONVERT TO BUFFERD IMAGE AND PRESERVER ARGB TRANSPARENCY
	public static BufferedImage scale(BufferedImage i, int newW, int newH) {
		Image toolkitImage = i.getScaledInstance(newW, newH, Image.SCALE_FAST);
		int width = toolkitImage.getWidth(null);
		int height = toolkitImage.getHeight(null);

		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = newImage.getGraphics();
		g.drawImage(toolkitImage, 0, 0, null);
		g.dispose();
		return newImage;
	}

	// FLIP A BUFFEREDIMAGE OVER ITS HORIZONTAL AXIS
	public BufferedImage flipHorz(BufferedImage i) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-i.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter((BufferedImage) i, null);
	}

	public static Tile getTile(PVector pos) {
		if (pos.x < 0 || pos.y < 0)
			return null;
		return tiles.get((int) (pos.x / tileSize)).get((int) (pos.y / tileSize));

	}

	public static ArrayList<Tile> getAdjacentTiles(Tile t) {
		ArrayList<Tile> res = new ArrayList<Tile>();
		PVector indexT = indexInTiles(t);
		int x = (int) indexT.x;
		int y = (int) indexT.y;

		if (x > 0 && y > 0) {
			res.add(tiles.get(x).get(y));

		}
		if (x + 1 < tiles.size())
			res.add(tiles.get(x + 1).get(y));
		if (x - 1 > -1)
			res.add(tiles.get(x - 1).get(y));
		if (y + 1 < tiles.size())
			res.add(tiles.get(x).get(y + 1));
		if (y - 1 > -1)
			res.add(tiles.get(x).get(y - 1));
		if (x + 1 < tiles.size() && y + 1 < tiles.size())
			res.add(tiles.get(x + 1).get(y + 1));
		if (x - 1 > -1 && y - 1 > -1)
			res.add(tiles.get(x - 1).get(y - 1));
		if (x - 1 > -1 && y + 1 < tiles.size())
			res.add(tiles.get(x - 1).get(y + 1));
		if (x + 1 < tiles.size() && y - 1 > -1)
			res.add(tiles.get(x + 1).get(y - 1));

		return res;

	}

	public static PVector indexInTiles(Tile t) {
		int x = 0, y = 0;
		for (ArrayList<Tile> t1 : tiles) {
			if (t1.contains(t)) {
				x = tiles.indexOf(t1);
				y = t1.indexOf(t);
			}

		}
		return new PVector(x, y);
	}

	public boolean tilesContains(Tile t, ArrayList<ArrayList<Tile>> toTest) {
		for (ArrayList<Tile> t1 : toTest) {
			for (Tile t2 : t1) {
				if (t2.equals(t))
					return true;
			}
		}
		return false;
	}

}