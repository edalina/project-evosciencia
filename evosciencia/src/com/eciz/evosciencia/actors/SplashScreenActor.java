package com.eciz.evosciencia.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.values.GameValues;

public class SplashScreenActor extends Table {
	
	private BitmapFont loadingText;
	private final String LOADING_STRING = "Loading...";
	private boolean flag = false;
	
	public SplashScreenActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		
		LoadAssets.splashTexture = new Texture(Gdx.files.internal("images/splashscreen.jpg"));
		loadingText = new BitmapFont();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(LoadAssets.splashTexture, 0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		loadingText.setColor(1, 1, 1, 1);
		loadingText.draw(GameValues.currentBatch, LOADING_STRING, (GameValues.SCREEN_WIDTH - loadingText.getBounds(LOADING_STRING).width)/2, 20);
		GameValues.currentBatch.end();
	}

}
