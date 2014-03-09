package com.eciz.evosciencia.enums;

public enum StanceEnum {
	
	FRONT_STAND("front_standby"),
	FRONT_WALK1("front_walk1"),
	FRONT_WALK2("front_walk2"),
	FRONT_ATTACK("front_attack"),
	BACK_STAND("back_standby"),
	BACK_WALK1("back_walk1"),
	BACK_WALK2("back_walk2"),
	BACK_ATTACK("back_attack"),
	LEFT_STAND("left_standby"),
	LEFT_WALK1("left_walk1"),
	LEFT_WALK2("left_walk2"),
	LEFT_ATTACK("left_attack"),
	RIGHT_STAND("right_standby"),
	RIGHT_WALK1("right_walk1"),
	RIGHT_WALK2("right_walk2"),
	RIGHT_ATTACK("right_attack"),
	DEATH("death");
	
	private String value;

	StanceEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
