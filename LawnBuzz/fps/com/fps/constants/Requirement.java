package com.fps.constants;

import java.util.HashMap;
import java.util.Map;

public enum Requirement {
	Food(0), NiceClothes(1), CompleteByDeadline(2), ArriveOnTime(3);
	private static Map<Integer, Requirement> map = new HashMap<Integer, Requirement>();
	static {
		for (Requirement cc : Requirement.values()) {
			map.put(cc.type, cc);
		}
	}
	public int type;

	Requirement(int type) {
		this.type = type;
	}

	public static Requirement valueOf(int type) {
		return map.get(type);
	}
}
