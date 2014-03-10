package com.eciz.evosciencia.entities;

public class Coordinate {

	private int x;
	private int y;
	private int map;
	
	public Coordinate() {
	}
	
	public Coordinate(int map) {
		setX(384);
		setY(384);
		setMap(map);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getMap() {
		return map;
	}
	public void setMap(int map) {
		this.map = map;
	}
	
}
