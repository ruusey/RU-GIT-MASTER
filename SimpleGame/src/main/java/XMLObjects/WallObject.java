package XMLObjects;

import finalproj.Texture;

public class WallObject extends finalproj.Object{
	Texture topTex;

	public WallObject(String name, int id, Texture tex, Texture topTex) {
		super(name, id, tex);
		this.topTex = topTex;
	}
	
}
