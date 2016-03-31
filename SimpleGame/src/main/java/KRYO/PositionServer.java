package KRYO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import KRYO.Network.HELLO;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class PositionServer{
	static Server server;
	
	ServerHandler sh = new ServerHandler();
	public PositionServer() throws IOException {
		server = new Server() {
		
			protected Connection newConnection() {
				
				return new CharacterConnection();
			}
		};
		

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(server);

		server.addListener(new Listener() {
			public void received(Connection c, Object object) {
				// We know all connections for this server are actually
				// CharacterConnections.
				
				sh.parseMessage(c, object, server);

			}


			public void disconnected(Connection c) {
				CharacterConnection connection = (CharacterConnection) c;
				if (connection.character != null) {
					


					//server.sendToAllTCP(removeCharacter);
				}
			}
		});
		server.bind(Network.portTCP, Network.portUDP);
		server.start();
		System.out.println("started Server");
	}
	

	public static void main(String[] args) throws IOException {
		GameState state = new GameState();
		state.start();
		Log.set(Log.LEVEL_DEBUG);
		new PositionServer();
	}
	
}