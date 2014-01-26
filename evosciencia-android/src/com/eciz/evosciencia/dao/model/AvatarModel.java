package com.eciz.evosciencia.dao.model;

public class AvatarModel {

	private int id;
	private String coordinate;
	private int playtime;
	
	public AvatarModel(String coordinate, int playtime) {
		this.coordinate = coordinate;
		this.playtime = playtime;
	}
	
	public AvatarModel(int id, String coordinate, int playtime) {
		this.id = id;
		this.coordinate = coordinate;
		this.playtime = playtime;
	}

	public AvatarModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public long getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}
	
}
