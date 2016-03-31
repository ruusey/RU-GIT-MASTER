package KRYO;

import KRYO.Network.*;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

public class ClientHandler {
	public void parseMessage(Connection connection, Object object, Client c) {
		if (object instanceof HELLO) {
			HELLO helloResponse = (HELLO) object;
			PositionClient.characters.put(helloResponse.UUID, new Character(helloResponse.UUID, 200, 200, 0, 0));
			PositionClient.id = helloResponse.UUID;
			
		}
		if (object instanceof UPDATECHARACTER) {
			UPDATECHARACTER h = (UPDATECHARACTER) object;
			PositionClient.characters.get(h.UUID).pos = h.newPos;
		}
		if (object instanceof ADDCHARACTER) {
			ADDCHARACTER h = (ADDCHARACTER) object;
			if (!PositionClient.characters.containsKey(h.UUID)) {
				PositionClient.characters.put(h.UUID, h.character);
			}
		}
		if (object instanceof DISCONNECT) {
			DISCONNECT h = (DISCONNECT) object;
			PositionClient.characters.remove(h.UUID);
		}
	}
}
