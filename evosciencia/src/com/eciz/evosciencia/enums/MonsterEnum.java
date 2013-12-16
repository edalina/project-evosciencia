package com.eciz.evosciencia.enums;

public enum MonsterEnum {
	
	GOBLIN("goblin");
	
	private String value;

	MonsterEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
