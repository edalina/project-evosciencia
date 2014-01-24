package com.eciz.evosciencia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.actors.SplashScreenActor;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.values.GameValues;

public class SplashScreen implements Screen {
	
	private Stage stage;
	
	public SplashScreen() {
		EvoSciencia.getMainInstance().loadAssets();
		stage = new Stage();
		GameValues.camera = (OrthographicCamera) stage.getCamera();
		
		stage.addActor(new SplashScreenActor());
	}

	@Override
	public void render(float delta) {
		
		if( LoadAssets.assetManager.update() ) {
			GameValues.currentScreen = new MenuScreen();
			EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		this.dispose();
	}

}
