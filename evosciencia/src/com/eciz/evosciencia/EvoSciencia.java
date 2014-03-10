package com.eciz.evosciencia;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.screens.SplashScreen;
import com.eciz.evosciencia.values.GameValues;

public class EvoSciencia extends Game implements ApplicationListener {
	
	private static EvoSciencia mainInstance = null;
	
	public static EvoSciencia getMainInstance() {
		if( mainInstance == null )
			mainInstance = new EvoSciencia();
		return mainInstance;
	}

	@Override
	public void create() {
		
		Texture.setEnforcePotImages(false);
		
		GameValues.currentScreen = new SplashScreen();
//		GameValues.currentScreen = new AIScreen();
		setScreen(GameValues.currentScreen);
		
//		Gdx.input.setCatchBackKey(true);
		
	}
	
	public void loadAssets() {
		
		GameValues.dpad = new Dpad();
		LoadAssets.loadAllAssets();
		
	}
	
	@Override
	public void dispose() {
		GameValues.currentScreen.dispose();
	}

}
