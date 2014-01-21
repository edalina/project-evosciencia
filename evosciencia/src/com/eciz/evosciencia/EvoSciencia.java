package com.eciz.evosciencia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.screens.GameScreen;
import com.eciz.evosciencia.screens.SplashScreen;
import com.eciz.evosciencia.values.GameValues;

public class EvoSciencia extends Game implements InputProcessor {
	
	private static EvoSciencia mainInstance = null;
	
	public static EvoSciencia getMainInstance() {
		if( mainInstance == null )
			mainInstance = new EvoSciencia();
		return mainInstance;
	}

	@Override
	public void create() {
		GameValues.currentScreen = new SplashScreen();
		setScreen(GameValues.currentScreen);
		
//		Gdx.input.setCatchBackKey(true);
		
		EvoSciencia.getMainInstance().loadAssets();
	}
	
	public void loadAssets() {
		LoadAssets.loadAllAssets();
		
		GameValues.dpad = new Dpad();
		
//		GameValues.currentScreen = new MenuScreen();
//		setScreen(GameValues.currentScreen);
//		
		GameValues.currentScreen = new GameScreen();
		setScreen(GameValues.currentScreen);
	}
	
	@Override
	public void dispose() {
		GameValues.currentScreen.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if( keycode == Keys.BACK ) {
			// Confirm
			System.out.println( "Back" );
			Gdx.app.exit();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
