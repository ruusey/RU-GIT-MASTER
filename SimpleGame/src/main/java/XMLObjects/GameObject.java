package XMLObjects;

import finalproj.Texture;

public class GameObject extends finalproj.Object{
	public boolean drawOnGround;
	public boolean moveable;
	
	public GameObject(String name, int id, Texture tex, boolean drawOnGround,
			boolean moveable) {
		super(name, id, tex);
		this.drawOnGround = drawOnGround;
		this.moveable = moveable;
	}
	
	
	
}
