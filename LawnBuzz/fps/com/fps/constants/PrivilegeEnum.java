package com.fps.constants;

public enum PrivilegeEnum {

	INHERIT(-1),
	NONE(0),
	BASIC_AUTHENTICATION(1),
	USER_PROXY(2),
	SYSTEM_PROXY(4),
	//	8
	SEND_QUESTIONNAIRE(16),
	UPDATE_QUESTIONNAIRE_RESPONSE(32),
	RETRIEVE_QUESTIONNAIRE_RESPONSE(64);

	private long id;
	
	private PrivilegeEnum(long id) {
		this.id = id;
	}

	public long id() {
		return id;
	}
	
	public static PrivilegeEnum valueOf(long id) {
		for (PrivilegeEnum privilege : PrivilegeEnum.values()) {
			if (privilege.id() == id) {
				return privilege;
			}
		}
		return null;
	}	
	
}
