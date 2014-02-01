package com.eciz.evosciencia.entities;


public class Quest {

	private int[] scientistsIds;
	private String item;
	
	public int[] getScientistsIds() {
		return scientistsIds;
	}
	public void setScientistsIds(int[] scientistsIds) {
		this.scientistsIds = scientistsIds;
	}
	public String getName() {
		return item;
	}
	public void setName(String name) {
		this.item = name;
	}
	
}
