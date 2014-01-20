package com.eciz.evosciencia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.eciz.evosciencia.actors.GameScreenActor;
import com.eciz.evosciencia.values.GameValues;

public class GameScreen implements Screen {
	
	private Stage stage;

	public GameScreen() {
		stage = new Stage();
		GameValues.camera = (OrthographicCamera) stage.getCamera();
		GameValues.controlCamera = (OrthographicCamera) stage.getCamera();
//		GameValues.camera.zoom = 0.5f;
		stage.addActor(new GameScreenActor());
	}
	
	@Override
	public void render(float delta) {
		// Clearing screen
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
		// Initialize utilities
//		GameValues.camera = new OrthographicCamera();
//		GameValues.camera.setToOrtho(false, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
//		GameValues.camera.position.x = Avatar.x < GameValues.SCREEN_WIDTH/2 ? GameValues.SCREEN_WIDTH/2 : Avatar.x;
//		GameValues.camera.position.y = Avatar.y > GameValues.SCREEN_HEIGHT/2 ? GameValues.SCREEN_HEIGHT/2 : Avatar.y;
		
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
		
	}
	
}
