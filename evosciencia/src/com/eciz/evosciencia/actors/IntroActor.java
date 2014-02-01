package com.eciz.evosciencia.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.screens.GameScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class IntroActor extends Table {
	
	private BitmapFont skipText;
	
	private Rectangle skipRec;
	
	private int index = 0;
	private long interval;
	
	public IntroActor() {
		GameValues.settingUtils.stopBGM();
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		skipText = new BitmapFont();
		
		skipRec = new Rectangle();
		skipRec.x = 25;
		skipRec.y = 10;
		skipRec.width = skipText.getBounds("skip").width;
		skipRec.height = skipText.getBounds("skip").height;
		
		GameValues.isNewGame = true;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		
		if( GameValues.introIndexPointer == 10 && index == 71 ) {
			GameValues.avatar.repositionAvatar(( GameValues.SCREEN_WIDTH - GameValues.avatar.getWidth() ) / 2, ( GameValues.SCREEN_HEIGHT - GameValues.avatar.getHeight() )/2);
			GameValues.currentScreen = new GameScreen();
			EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
			return;
		}
		
		GameValues.currentBatch.begin();

		GameValues.currentBatch.draw(GameValues.introAnimations[index], 0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT );
		if( (GameValues.introAnimations.length * (GameValues.introIndexPointer - 1)) + index < GameValues.introAnimations.length * 10
			&& TimeUtils.millis() - interval > 100 ) {
			
			interval = TimeUtils.millis();
			if( GameValues.introIndexPointer < 10) {
				int currentFileCount = (GameValues.introIndexPointer * GameValues.introAnimations.length) + index;
				String pathName = "intro/00" + ( currentFileCount < 10 ? "0" : "" ) + ( currentFileCount < 100 ? "0" : "" ) + currentFileCount + ".jpg";
				GameValues.introAnimations[index] = new Texture(Gdx.files.internal(pathName));
			}
			index++;
			if( index == GameValues.introAnimations.length - 1 && GameValues.introIndexPointer < 10 ) {
				index = 0;
				GameValues.introIndexPointer++;
			}
			
		}
		skipText.draw(GameValues.currentBatch, "skip", skipRec.getX(), skipRec.getY() + 15);
		GameValues.currentBatch.end();
		if( Gdx.input.isTouched() ) {
			
			if( EventUtils.isTap(skipRec) ) {
				GameValues.avatar.repositionAvatar(( GameValues.SCREEN_WIDTH - GameValues.avatar.getWidth() ) / 2, ( GameValues.SCREEN_HEIGHT - GameValues.avatar.getHeight() )/2);
				GameValues.currentScreen = new GameScreen();
				EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
			}
		}
	}
	
}