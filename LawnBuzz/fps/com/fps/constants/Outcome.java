package com.fps.constants;

import java.util.HashMap;
import java.util.Map;

public enum Outcome {
	ExtraAllowance(0), SalarayRaise(1), Vacation(2), Grounded(3);
	private static Map<Integer, Outcome> map = new HashMap<Integer, Outcome>();
	static {
		for (Outcome cc : Outcome.values()) {
			map.put(cc.type, cc);
		}
	}
	public int type;

	Outcome(int type) {
		this.type = type;
	}

	public static Outcome valueOf(int type) {
		return map.get(type);
	}
}
