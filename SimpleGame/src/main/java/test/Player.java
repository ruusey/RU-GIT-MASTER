package test;

import java.io.Serializable;

import processing.core.PVector;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PVector pos;
	PVector vel;
	String UUID;
	public Player(int x, int y,String UUID){
		pos=new PVector(x,y);
		vel=new PVector(0,0);
		this.UUID=UUID;
	}
	public void update(){
		pos.add(vel);
	}
}
