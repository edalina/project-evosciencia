package com.eciz.evosciencia;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eciz.evosciencia.values.GameValues;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "EvoSciencia";
		cfg.width = GameValues.SCREEN_WIDTH;
		cfg.height = GameValues.SCREEN_HEIGHT;
		
		new LwjglApplication(EvoSciencia.getMainInstance(), cfg);
	}
}
