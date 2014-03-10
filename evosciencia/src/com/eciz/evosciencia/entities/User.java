package com.eciz.evosciencia.entities;

public class User {

	private int id;
	private Coordinate coordinate;
	private long playtime;
	private String avatar;
	private int life;
	private int level;
	private int damage;
	private int[] scientists;
	private boolean[] questDone;
	private long experience = 0;
	private boolean isCurrentQuestDone = false;
	
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
	public long getPlaytime() {
		return playtime;
	}
	public void setPlaytime(long playtime) {
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
	public int getDamage() {
		damage = 10 + (2*level); 
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int[] getScientists() {
		return scientists;
	}
	public void setScientists(int[] scientists) {
		this.scientists = scientists;
	}
	public boolean[] getQuestDone() {
		return questDone;
	}
	public void setQuestDone(boolean[] questDone) {
		this.questDone = questDone;
	}
	public long getExperience() {
		return experience;
	}
	public void setExperience(long experience) {
		this.experience = experience;
	}
	public boolean isCurrentQuestDone() {
		return isCurrentQuestDone;
	}
	public void setCurrentQuestDone(boolean isCurrentQuestDone) {
		this.isCurrentQuestDone = isCurrentQuestDone;
	}
	
}
