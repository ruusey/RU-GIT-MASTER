package KRYO;

import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import KRYO.Network.*;
import processing.core.PApplet;
import processing.core.PVector;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.minlog.Log;

public class PositionClient extends PApplet{
	
	Client client;
	static String id;
	public static Character c;
	public static Hashtable<String,Character> characters = new Hashtable<String,Character>();
	public static boolean handshake;
	public boolean firing;
	public PositionClient () {
		client = new Client();
		client.start();
		
		final ClientHandler ch = new ClientHandler();
		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);

		// ThreadedListener runs the listener methods on a different thread.
		client.addListener(new ThreadedListener(new Listener() {
			public void connected (Connection connection) {
				
			}

			public void received (Connection connection, Object object) {
				ch.parseMessage(connection, object, client);
			}

			public void disconnected (Connection connection) {
				DISCONNECT c = new DISCONNECT();
				c.UUID=id;
				client.sendUDP(c);
				System.exit(0);
			}
		}));

		

		
		try {
			client.connect(5000, "localhost", Network.portTCP,Network.portUDP);
			System.out.println("Connected to server, Initiating Handshake");
			if(!handshake){
				handshake();
			}
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}

	}

	

	public static void main (String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		PApplet.main(PositionClient.class.getName());
		new PositionClient();
	}
	public void draw(){
		background(0);
		for(Character c: characters.values()){
			if(c!=null){
				fill(255);
				ellipse(c.pos.x,c.pos.y,30,30);
			}
		}
		
	}
	public void handshake(){
		String id = UUID.randomUUID().toString();
		HELLO h = new HELLO();
		h.UUID=id;
		if(!handshake){
			client.sendUDP(h);
		}
		
		handshake=true;
	}
	public void setup(){
		
	}
	public void settings(){
		size(640,480);
	}
	public void keyPressed(){
		if (key=='w'){
			UPDATECHARACTER uc = new UPDATECHARACTER();
			uc.newPos=new PVector(0,-20);
			uc.UUID=id;
			client.sendUDP(uc);
		} else if(key=='s'){
			UPDATECHARACTER uc = new UPDATECHARACTER();
			uc.newPos=new PVector(0,20);
			uc.UUID=id;
			client.sendUDP(uc);
		}
		else if(key=='a'){
			UPDATECHARACTER uc = new UPDATECHARACTER();
			uc.newPos=new PVector(-20,0);
			uc.UUID=id;
			client.sendUDP(uc);
		}
		else if(key=='d'){
			UPDATECHARACTER uc = new UPDATECHARACTER();
			uc.newPos=new PVector(20,0);
			uc.UUID=id;
			client.sendUDP(uc);
		}
	}
}