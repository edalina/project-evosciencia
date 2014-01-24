package com.eciz.evosciencia.enums;

public enum GameDifficulty {
	
	EASY("easy"),
	NORMAL("normal"),
	HARD("hard"),
	NIGHTMARE("xhard");
	
	String value;
	
	GameDifficulty(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
