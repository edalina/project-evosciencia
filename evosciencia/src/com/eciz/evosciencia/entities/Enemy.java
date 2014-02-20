package com.eciz.evosciencia.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.values.GameValues;

public class Enemy extends Sprite {

	public static final float width = GameValues.avatar.getWidth();
	public static final float height = GameValues.avatar.getWidth();
	
	private int id;
	private Texture texture;
	private StanceEnum facing;
	public boolean isMoving = false;
	private int life = 100;
	private boolean alive = true;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public StanceEnum getFacing() {
		return facing;
	}
	public void setFacing(StanceEnum facing) {
		this.facing = facing;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}
	
}
