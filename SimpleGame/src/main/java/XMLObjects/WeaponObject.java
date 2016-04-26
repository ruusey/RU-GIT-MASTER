package XMLObjects;

import finalproj.Item;
import finalproj.Texture;

public class WeaponObject extends Item{
	public double rateOfFire;
	public String projectile;
	public int speed;
	public int minDmg;
	public int maxDmg;
	public int shots;
	public double range;
	public boolean multihit;
	public float angleBetweenShots;
	
	public WeaponObject(String name, int id, Texture tex,
			double rateOfFire, String projectile, int speed, int minDmg, int maxDmg,
			int shots, double range, boolean multihit, float angleBetweenShots) {
		super(name, id, tex);
		this.rateOfFire = rateOfFire;
		this.projectile = projectile;
		this.speed = speed;
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		this.shots = shots;
		this.range = range;
		this.multihit = multihit;
		this.angleBetweenShots=angleBetweenShots;
	}
	

}
