package com.eciz.evosciencia.enums;

public enum BGMEnum {
	
	BGM_1("bgm/midnight_sorrow.wav"),
	BGM_2("bgm/monster_attack.wav"),
	BGM_3("bgm/moving_to_the_beat.wav"),
	BGM_4("bgm/mysterious_path.wav"),
	BGM_5("bgm/prelude_to_spring.wav");
	
	private String value;
	
	private BGMEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
