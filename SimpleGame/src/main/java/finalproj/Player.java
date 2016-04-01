package finalproj;

import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PVector;

public class Player {
	PVector pos;
	PVector vel;
	int dmg;
	int diameter;
	public boolean firing;
	public Rectangle colBox;
	ArrayList<Projectile> shots = new ArrayList<Projectile>();
	
	public Player(int x, int y, int dmg, int diameter){
		pos = new PVector(x,y);
		vel= new PVector(0,0);
		this.dmg=dmg;
		firing=false;
		this.diameter=diameter;
		colBox = new Rectangle((int)pos.x-(diameter/2),(int)pos.y-(diameter/2),diameter,diameter);
	}
	public void update(){
		
		pos.add(vel);
		colBox.x=(int) pos.x-diameter/2;
		colBox.y=(int) pos.y-diameter/2;
		
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
