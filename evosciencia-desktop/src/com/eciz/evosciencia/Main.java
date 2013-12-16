package com.eciz.evosciencia;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "EvoSciencia";
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new EvoSciencia(), cfg);
	}
}
