package KRYO;

import KRYO.Network.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

public class ServerHandler {

	public void parseMessage(Connection connection, Object object, Server s) {
		// IF UPDATE CHARACTER REQUEST IS RECEIVED
		if (object instanceof UPDATECHARACTER) {

			UPDATECHARACTER u = (UPDATECHARACTER) object;

			GameState.characters.get(u.UUID).dest = u.newPos;
			System.out.println();

		}
		// RETURN THE HELLO PACKET WITH THE SAME UUID
		if (object instanceof HELLO) {

			HELLO h = (HELLO) object;

			try {
				Character newChar = new Character(h.UUID, 200, 200, 0, 0);
				GameState.sem.acquire();
				GameState.characters.put(h.UUID, newChar);
				connection.sendUDP(h);
				for (Character c : GameState.characters.values()) {
					ADDCHARACTER add = new ADDCHARACTER();
					add.UUID = c.UUID;
					add.character = c;

					PositionServer.server.sendToAllUDP(add);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				GameState.sem.release();
			}

		}
		if (object instanceof DISCONNECT) {

			DISCONNECT h = (DISCONNECT) object;

			GameState.characters.remove(h.UUID);
			
			PositionServer.server.sendToAllUDP(h);

		}
	}
}
