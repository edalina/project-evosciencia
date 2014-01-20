package com.eciz.evosciencia.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.values.GameValues;

public class MenuScreenActor extends Table {
	
	private BitmapFont startButton;
	private BitmapFont optionButton;
	private BitmapFont aboutButton;
	
	private final String START_STRING = "Start";
	private final String OPTION_STRING = "Options";
	private final String ABOUT_STRING = "About";
	
	public MenuScreenActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		
		startButton = new BitmapFont();
		optionButton = new BitmapFont();
		aboutButton = new BitmapFont();
		
		startButton.setColor(1, 1, 1, 1);
		optionButton.setColor(1, 1, 1, 1);
		aboutButton.setColor(1, 1, 1, 1);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(LoadAssets.splashTexture, 0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		startButton.draw(GameValues.currentBatch, START_STRING, (GameValues.SCREEN_WIDTH - startButton.getBounds(START_STRING).width)/2, 100);
		optionButton.draw(GameValues.currentBatch, OPTION_STRING, (GameValues.SCREEN_WIDTH - optionButton.getBounds(OPTION_STRING).width)/2, 75);
		aboutButton.draw(GameValues.currentBatch, ABOUT_STRING, (GameValues.SCREEN_WIDTH - aboutButton.getBounds(ABOUT_STRING).width)/2, 50);
		GameValues.currentBatch.end();
	}
	
}
