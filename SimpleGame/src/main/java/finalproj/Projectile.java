package finalproj;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.sql.Time;

import processing.core.PApplet;
import processing.core.PVector;

public class Projectile extends Entity {

	int dmg;
	PVector source;
	int diameter;
	public float angle;
	float mag;
	float range;
	public boolean isHit;

	public Projectile(float x, float y, float sx, float sy, PVector vel,
			float angle, int diameter, int dmg, float mag, float range, BufferedImage sprite,
			PApplet parent) {
		super(x, y, vel, diameter,sprite, parent);
		source = new PVector(sx, sy);
		this.dmg = dmg;
		this.angle = angle;
		this.mag = mag;
		this.range = range;
		this.diameter = diameter;
		this.isHit = false;
		
	}

	public void update() {

		PVector up = PVector.fromAngle(angle);
		up.setMag(mag);
		pos.add(up);
		tile = Client.getTile(pos);
		colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;

	}
}
