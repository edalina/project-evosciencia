package com.eciz.evosciencia.enums;

public enum StanceEnum {
	
	FRONT_STAND_1("front_standby_1"),
	FRONT_STAND_2("front_standby_2"),
	FRONT_WALK1("front_walk1"),
	FRONT_WALK2("front_walk2"),
	BACK_STAND_1("back_standby_1"),
	BACK_STAND_2("back_standby_2"),
	BACK_WALK1("back_walk1"),
	BACK_WALK2("back_walk2"),
	LEFT_STAND_1("left_standby_1"),
	LEFT_STAND_2("left_standby_2"),
	LEFT_WALK1("left_walk1"),
	LEFT_WALK2("left_walk2"),
	RIGHT_STAND_1("right_standby_1"),
	RIGHT_STAND_2("right_standby_2"),
	RIGHT_WALK1("right_walk1"),
	RIGHT_WALK2("right_walk2");
	
	private String value;

	StanceEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
