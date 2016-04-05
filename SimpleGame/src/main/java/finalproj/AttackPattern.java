package finalproj;

public class AttackPattern {
	int shotDelay;
	boolean targetPlayer;
	int shotsPerFrame = 6;
	float angleBetweenShots;
	int shotDamage;
	int shotDiameter;
	float shotSpeed;
	float range;
	public AttackPattern(int shotDelay, boolean targetPlayer, int shotsPerFrame, float angleBetweenShots,
			int shotDamage, int shotDiameter, float shotSpeed, float range) {

		this.shotDelay = shotDelay;
		this.targetPlayer = targetPlayer;
		this.shotsPerFrame = shotsPerFrame;
		this.angleBetweenShots = angleBetweenShots;
		this.shotDamage = shotDamage;
		this.shotDiameter = shotDiameter;
		this.shotSpeed = shotSpeed;
		this.range=range;
	}
	
}
