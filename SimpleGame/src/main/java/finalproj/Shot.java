package finalproj;

import XMLObjects.ProjectileObject;
import processing.core.PApplet;
import processing.core.PVector;

public class Shot extends Entity {

	int dmg;
	PVector source;
	int diameter;
	public float angle;
	float mag;
	float range;
	public boolean isHit;

	public Shot(ProjectileObject p,float x, float y, float sx, float sy, PVector vel,
			float angle, int diameter, int dmg, float mag, float range,
			PApplet parent) {
		super(p.name,p.id,p.tex,x, y, vel, diameter, parent);
		source = new PVector(sx, sy);
		this.dmg = dmg;
		this.angle = angle;
		this.mag = mag;
		this.range = range;
		this.diameter = diameter;
		this.isHit = false;
		
	}

	public void update(float dt) {

		PVector up = PVector.fromAngle(angle);
		up.setMag(mag+(20/dt));
		
		pos.add(up);
		tile = Client.getTile(pos);
		colBox.x = (int) pos.x - colBox.width / 2;
		colBox.y = (int) pos.y - colBox.height / 2;

	}
}
