package finalproj;

import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PVector;

public class Player extends Movement {

	int health;
	public boolean firing;
	ArrayList<Projectile> shots = new ArrayList<Projectile>();

	public Player(int x, int y, int health, int diameter) {
		super(x, y, diameter, diameter);
		pos = new PVector(x, y);
		vel = new PVector(0, 0);
		this.health = health;
		firing = false;

		colBox = new Rectangle((int) pos.x - (diameter / 2), (int) pos.y
				- (diameter / 2), diameter, diameter);
	}

	public void update() {

		pos.add(vel);
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
			if (PVector.dist(p.source, p.pos) > 300
					|| Client.checkCollision(p.colBox) != null) {
				shotsToRemove.add(p);
			} else {
				p.update();
			}
		}
		shots.removeAll(shotsToRemove);
	}
}
