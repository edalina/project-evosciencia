package com.eciz.evosciencia.entities;

import com.badlogic.gdx.math.Rectangle;

public class Checkpoint {

	private Rectangle rectangle;
	private String value;
	
	public Checkpoint(String value, Rectangle rectangle) {
		this.value = value;
		this.rectangle = rectangle;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
