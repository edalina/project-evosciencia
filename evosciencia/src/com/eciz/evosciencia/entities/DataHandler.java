package com.eciz.evosciencia.entities;

import java.util.List;

public class DataHandler {

	private List<String> maps;
	private List<Scientist> scientists;
	private List<Quest> quests;
	private List<User> users;
	
	public List<String> getMaps() {
		return maps;
	}
	public void setMaps(List<String> maps) {
		this.maps = maps;
	}
	public List<Scientist> getScientists() {
		return scientists;
	}
	public void setScientists(List<Scientist> scientists) {
		this.scientists = scientists;
	}
	public List<Quest> getQuests() {
		return quests;
	}
	public void setQuests(List<Quest> quests) {
		this.quests = quests;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
