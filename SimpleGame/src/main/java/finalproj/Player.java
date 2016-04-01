package finalproj;

import java.awt.List;
import java.util.ArrayList;

import processing.core.PVector;

public class Player {
	PVector pos;
	PVector vel;
	int dmg;
	public boolean firing;
	ArrayList<Projectile> shots = new ArrayList<Projectile>();
	public Player(int x, int y, int dmg){
		pos = new PVector(x,y);
		vel= new PVector(0,0);
		this.dmg=dmg;
		firing=false;
	}
	public void update(){
		
		pos.add(vel);
	}
	public void updateShots(){
		ArrayList<Projectile> shotsToRemove = new ArrayList<Projectile>();
		for(Projectile p : shots){
			if(PVector.dist(pos,p.pos)>150){
				shotsToRemove.add(p);
			}else{
				p.update();
			}
		}
		shots.removeAll(shotsToRemove);
	}
}
