package finalproj;

import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PVector;

public class Player extends Movement {

	public int health;
	public boolean firing;
	ArrayList<Projectile> shots = new ArrayList<Projectile>();
	public HealthBar hp;
	public Player(int x, int y, int health, int diameter) {
		super(x, y, diameter, diameter);
		pos = new PVector(x, y);
		vel = new PVector(0, 0);
		this.health = health;
		firing = false;
		final int x1 = (700 - diameter*2) / 2;
		final int y1 = (700) / 2;
		hp = new HealthBar((int)x1,(int)y1,200,diameter/2, health);
		colBox = new Rectangle((int) pos.x - (diameter / 2), (int) pos.y
				- (diameter / 2), diameter, diameter);
	}

	public void update() {

		pos.add(vel);
		hp.update();
		colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;

	}

	public void updateX() {

		pos.x+=vel.x;
		colBox.x = (int) pos.x - colBox.width / 2;
		//colBox.y = (int) pos.y - colBox.height / 2;

	}

	public void updateY() {

		pos.y+=vel.y;
		//colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;

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
}
