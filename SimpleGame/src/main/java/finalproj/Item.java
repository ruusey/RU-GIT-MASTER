package finalproj;

import java.awt.image.BufferedImage;

public class Item {
	public BufferedImage sprite;
	public String name;
	
	public int healthBonus;
	public int manaBonus;
	public int spdBonus;
	public int attBonus;
	public int defBonus;
	public int dexBonus;
	public Item(BufferedImage sprite, String name, int healthBonus, int manaBonus, int spdBonus, int attBonus, int defBonus,
			int dexBonus) {
		this.sprite = sprite;
		this.healthBonus = healthBonus;
		this.manaBonus = manaBonus;
		this.spdBonus = spdBonus;
		this.attBonus = attBonus;
		this.defBonus = defBonus;
		this.dexBonus = dexBonus;
	}
	

}
