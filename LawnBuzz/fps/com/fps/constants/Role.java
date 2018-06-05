package com.fps.constants;

import java.util.HashMap;
import java.util.Map;

public enum Role {
	Admin(0), Guest(1), VIP(2), Child(3);
	private static Map<Integer, Role> map = new HashMap<Integer, Role>();
	static {
		for (Role cc : Role.values()) {
			map.put(cc.type, cc);
		}
	}
	public int type;

	Role(int type) {
		this.type = type;
	}

	public static Role valueOf(int type) {
		return map.get(type);
	}
}
