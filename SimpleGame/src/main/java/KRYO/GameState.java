package KRYO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.Semaphore;

import com.owlike.genson.Genson;

import KRYO.Network.*;

public class GameState extends Thread {
	public static Hashtable<String,Character> characters = new Hashtable<String,Character>();
	public static Semaphore sem;
Genson gen = new Genson();
	public void run() {
		sem = new Semaphore(1);
		while (true) {
			try {
				sem.acquire();
				
				for (Character c : characters.values()) {
					if(!c.tryToMoveTo()) continue;
					UPDATECHARACTER cu = new UPDATECHARACTER();
					cu.UUID = c.UUID;
					cu.newPos = c.pos;
					PositionServer.server.sendToAllUDP(cu);

				}
			} catch (Exception e) {

			} finally {
				sem.release();
			}

		}
	}

	public static Character getByUUID(String UUID){
		return characters.get(UUID);
	}
}
