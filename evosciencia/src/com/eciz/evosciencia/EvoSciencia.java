package com.eciz.evosciencia;

import com.badlogic.gdx.Game;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.screens.GameScreen;
import com.eciz.evosciencia.values.GameValues;

public class EvoSciencia extends Game {

	@Override
	public void create() {
		LoadAssets.loadAllAssets();
		GameValues.currentScreen = new GameScreen();
		setScreen(GameValues.currentScreen);
	}
	
	@Override
	public void dispose() {
		GameValues.currentScreen.dispose();
	}
	
}
