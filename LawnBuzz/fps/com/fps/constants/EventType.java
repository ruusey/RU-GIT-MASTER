package com.fps.constants;

import java.util.HashMap;
import java.util.Map;

public enum EventType {
	Sport(0), Concert(1), Adventure(2), Traveling(3);
	private static Map<Integer, EventType> map = new HashMap<Integer, EventType>();
	static {
		for (EventType cc : EventType.values()) {
			map.put(cc.type, cc);
		}
	}
	public int type;

	EventType(int type) {
		this.type = type;
	}

	public static EventType valueOf(int type) {
		return map.get(type);
	}
}
