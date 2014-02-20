package com.eciz.evosciencia.entities;

import java.util.ArrayList;
import java.util.List;

import com.eciz.evosciencia.values.GameValues;


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
	
	public static List<Quest> getQuests() {
		List<Quest> quests = new ArrayList<Quest>();
		
		for( Quest quest : GameValues.dataHandler.getQuests() ) {
			for( int id : quest.getScientistsIds() ) {
				if( id == GameValues.currentScientist.getId() ) {
					quests.add(quest);
				}
			}
		}
		
		return quests;
	}
	
}
