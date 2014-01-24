package com.eciz.evosciencia.entities;

import com.badlogic.gdx.math.Rectangle;

public class SkillSlot {

	private int id;
	private Rectangle rectangle;
	private long skillId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	
}
