package finalproj;

import packets.UPDATE;
import processing.core.PApplet;
import test.ClientHandler;

import java.util.ArrayList;

import finalproj.Player;

public final class Client extends PApplet{

    ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();
    ArrayList<ArrayList<Tile>> renderedTiles = new ArrayList<ArrayList<Tile>>();
    
    public static Player p;
    public static void main(String[] args) {
    	PApplet.main(Client.class.getName());
       
    }
    
    public void draw(){
    	background(0);
    	if(p!=null){
    		
    		p.update();
        	fill(255);
        	ellipse(p.pos.x,p.pos.y,50,50);
        
    	}
    	
    }
    public void setup(){
    	p = new Player(400,400,20);
    	for(int x=0; x<tiles.size(); x++){
    		
    		for(int y=0; y<tiles.get(0).size(); y++){
    			tiles.get(x).add(new Tile(x*10,y*10,10,10));
        		
        		
        	}
    		
    	}
    }
    public void settings(){
    	size(640,480);
    }
    public void keyPressed(){
    	if(key=='w'){
    		for(ArrayList<Tile> t1 : tiles){
    			
    		}
    		
    		
    	}else if(key=='s'){
    		p.vel.y=5;
    	}
    	else if(key=='a'){
    		p.vel.x=-5;
    	}
    	else if(key=='d'){
    		p.vel.x=5;
    	}
    }
    public void keyReleased(){
    	if(key=='w'){
    		p.vel.y=0;
    		
    	}else if(key=='s'){
    		p.vel.y=0;
    	}
    	else if(key=='a'){
    		p.vel.x=0;
    	}
    	else if(key=='d'){
    		p.vel.x=0;
    	}
    }
    public void loadAndRomoveLeft(){
    	for(ArrayList<Tile> t1 : tiles){
			for(int x = 0 ; x< t1.size();x++){
				Tile t = new Tile(1,1,1,1);
			}
		}
    }
 
}