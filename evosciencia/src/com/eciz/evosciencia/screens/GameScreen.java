package com.eciz.evosciencia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.eciz.evosciencia.actors.GameScreenActor;
import com.eciz.evosciencia.values.GameValues;

public class GameScreen implements Screen {
	
	private Stage stage;
	public static TextField field;

	public GameScreen() {
		stage = new Stage();
//		GameValues.camera = (OrthographicCamera) stage.getCamera();
		GameValues.camera.zoom = GameValues.CAMERA_ZOOM;
		stage.addActor(new GameScreenActor());
		
		field = new TextField("", GameValues.skin);
//		field.setX(DIALOG_X + 50);
//		field.setY(DIALOG_Y + DIALOG_HEIGHT - (110 + dialogText.getBounds(questCompleteDialog).height));
//		field.setWidth(DIALOG_WIDTH - 100);
//		field.setHeight(25);
		field.setX(0);
		field.setY(0);
		field.setVisible(false);
		field.setWidth(100);
		field.setHeight(100);
		stage.addActor(field);
		
		Gdx.input.setInputProcessor(stage);
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
//		stage.dispose();
//		this.dispose();
	}
	
}
