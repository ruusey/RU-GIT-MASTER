package XMLObjects;

import finalproj.Object;
import finalproj.Texture;

public class ProjectileObject extends Object{
	public int correctionAngle;
	public int size;
	public ProjectileObject(String name, int id, Texture tex, int correctionAngle, int size) {
		super(name, id, tex);
		this.correctionAngle=correctionAngle;
		this.size=size;
	}

	
}
