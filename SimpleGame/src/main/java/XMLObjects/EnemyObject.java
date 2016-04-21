package XMLObjects;

import java.util.ArrayList;

import finalproj.AttackPattern;
import finalproj.Texture;

public class EnemyObject extends finalproj.Object{
	public int hp;
	public int def;
	public int size;
	public ArrayList<AttackPattern> patterns = new ArrayList<AttackPattern>();
	public EnemyObject(String name, int id, Texture tex, ArrayList<AttackPattern> attacks, int hp, int def,int size) {
		super(name, id, tex);
		this.patterns=attacks;
		this.hp=hp;
		this.def=def;
		this.size=size;
		
	}

}
