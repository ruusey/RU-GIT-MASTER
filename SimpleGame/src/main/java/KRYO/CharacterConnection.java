package KRYO;

import com.esotericsoftware.kryonet.Connection;

public class CharacterConnection extends Connection {
		public Character character;
		public CharacterConnection(Character character){
			this.character=character;
		}
		public CharacterConnection(){
			character=null;
		}
	}