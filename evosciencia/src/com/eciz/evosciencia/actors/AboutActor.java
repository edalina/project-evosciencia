package com.eciz.evosciencia.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.screens.MenuScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class AboutActor extends Table {
	
	private Texture saveDataBox;
	
	private BitmapFont title;
	private BitmapFont content;
	private BitmapFont back;
	
	private Rectangle backRec;
	
	private String titleTxt = "About";
	private String contentTxt = "" +
			"Goal: Finish all the main quest\n" +
			"EvoSciencia @ 2014\n" +
			"Developed by:\n" +
			"\tErynn Jae Dalina\n" +
			"\tIa Celine Gonzales\n" +
			"\tAmando Carlo Pingul\n" +
			"\tZhandy Teves\n";
	private String backTxt = "back";
	
	private int coor = 30;
	
	public AboutActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		saveDataBox = new Texture(Gdx.files.internal("images/character_box.png"));
		
		title = new BitmapFont();
		content = new BitmapFont();
		back = new BitmapFont();
		
		title.setScale(2);
		content.setScale(1);
		
		backRec = new Rectangle();
		backRec.set(20, 10, back.getBounds(backTxt).width, 15);
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(saveDataBox, coor, coor, GameValues.SCREEN_WIDTH - (coor*2), GameValues.SCREEN_HEIGHT - (coor*2));
		title.draw(GameValues.currentBatch, titleTxt, coor + 10, GameValues.SCREEN_HEIGHT);
		content.drawMultiLine(GameValues.currentBatch, contentTxt, coor * 3, GameValues.SCREEN_HEIGHT - (coor*3));
		back.draw(GameValues.currentBatch, backTxt, backRec.x, backRec.y + 15);
		GameValues.currentBatch.end();
		if( Gdx.input.isTouched() ) {
			if( !GameValues.touchDown ) {
				GameValues.touchDown = true;
				if( EventUtils.isTap(backRec) ) {
					GameValues.currentScreen = new MenuScreen();
					EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
				}
			}
		} else {
			GameValues.touchDown = false;
		}
		
	}
	
}
