package XMLObjects;

import finalproj.Texture;

public class ContainerObject extends finalproj.Object{
boolean normalItems;
boolean loot;
	
	public ContainerObject(String name, int id, Texture tex,
			boolean normalItems, boolean loot) {
		super(name,id, tex);
		this.normalItems = normalItems;
		this.loot = loot;
	}
	
	
}
