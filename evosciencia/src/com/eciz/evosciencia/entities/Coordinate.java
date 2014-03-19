package com.eciz.evosciencia.entities;

import com.eciz.evosciencia.values.GameValues;

public class Coordinate {

	private float x;
	private float y;
	private int map;
	
	public Coordinate() {
	}
	
	public Coordinate(int map) {
		
	}
	
	public static Coordinate getCurrentCoordinate() {
		Coordinate coordinate = new Coordinate();
		float x = GameValues.avatar.getX(),
			y = GameValues.avatar.getY();
		
		if( GameValues.maps.name.indexOf("_1") == -1 ) {
			x = 384;
			y = 384;
		}
		coordinate.setX(x);
		coordinate.setY(y);
		coordinate.setMap(GameValues.currentMapValue);
		
		return coordinate;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getMap() {
		return map;
	}
	public void setMap(int map) {
		this.map = map;
	}
	
}
