package com.eciz.evosciencia.entities;

public class User {

	private int id;
	private Coordinate coordinate;
	private int playtime;
	private String avatar;
	private int life;
	private int level;
	private int[] scientists;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	public int getPlaytime() {
		return playtime;
	}
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int[] getScientists() {
		return scientists;
	}
	public void setScientists(int[] scientists) {
		this.scientists = scientists;
	}
	
}
