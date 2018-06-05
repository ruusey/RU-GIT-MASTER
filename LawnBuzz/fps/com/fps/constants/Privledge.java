package com.fps.constants;

import java.util.HashMap;
import java.util.Map;

public enum Privledge {
	ReadOnly(0), Edit(1), FullAccess(2), Admin(3);
	private static Map<Integer, Privledge> map = new HashMap<Integer, Privledge>();
	static {
		for (Privledge cc : Privledge.values()) {
			map.put(cc.type, cc);
		}
	}
	public int type;

	Privledge(int type) {
		this.type = type;
	}

	public static Privledge valueOf(int type) {
		return map.get(type);
	}
}
