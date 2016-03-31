package KRYO;

import processing.core.PVector;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

// This class is a convenient place to keep things common to both the client and server.
public class Network {
	static public final int portTCP = 54555;
	static public final int portUDP = 54555;

	// This registers objects that are going to be sent over the network.
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(HELLO.class);
		kryo.register(UPDATECHARACTER.class);
		kryo.register(processing.core.PVector.class);
		kryo.register(Character.class);
		kryo.register(ADDCHARACTER.class);
		
	}
	static public class SomeRequest {
	       public String text;
	      
	    }
	static public class SomeResponse {
	       public String text;
	}
	static public class HELLO{
		
		public String UUID;
		
		
	}
	static public class UPDATECHARACTER{
		
		public String UUID;
		public PVector newPos;
		
		
	}
static public class ADDCHARACTER{
		
		public String UUID;
		public Character character;
		
		
	}
	
}