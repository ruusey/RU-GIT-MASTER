package com.fps.constants;

public enum Environment {

	UNKNOWN(0),
	DEV(1),
	QA(2),
	PROD(3);
	
	private int id;
	
	private Environment(int id) {
		this.id = id;
	}

	public int id() {
		return id;
	}
	
}
