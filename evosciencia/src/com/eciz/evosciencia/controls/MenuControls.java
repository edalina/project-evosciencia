package com.eciz.evosciencia.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.screens.AboutScreen;
import com.eciz.evosciencia.screens.SaveDataScreen;
import com.eciz.evosciencia.screens.SettingScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class MenuControls {
	
	private BitmapFont startButton;
	private BitmapFont optionButton;
	private BitmapFont aboutButton;
	
	private Rectangle startRectangle;
	private Rectangle optionRectangle;
	private Rectangle aboutRectangle;
	
	private final String START_STRING = "Start";
	private final String OPTION_STRING = "Options";
	private final String ABOUT_STRING = "About";
	
	public static boolean isMenuActive = false;
	
	private static MenuControls instance = null;

	public static MenuControls getInstance() {
		if( instance == null )
			instance = new MenuControls();
		isMenuActive = true;
		return instance;
	}

	public MenuControls() {
		startButton = new BitmapFont();
		optionButton = new BitmapFont();
		aboutButton = new BitmapFont();
		
		startButton.setColor(1, 1, 1, 1);
		optionButton.setColor(1, 1, 1, 1);
		aboutButton.setColor(1, 1, 1, 1);
		
		startRectangle = new Rectangle();
		optionRectangle = new Rectangle();
		aboutRectangle = new Rectangle();
		
		startRectangle.width = startButton.getBounds(START_STRING).width;
		optionRectangle.width = optionButton.getBounds(OPTION_STRING).width;
		aboutRectangle.width = aboutButton.getBounds(ABOUT_STRING).width;
		startRectangle.height = startButton.getBounds(START_STRING).height;
		optionRectangle.height = optionButton.getBounds(OPTION_STRING).height;
		aboutRectangle.height = aboutButton.getBounds(ABOUT_STRING).height;
		
		startRectangle.x = (GameValues.SCREEN_WIDTH - startRectangle.width)/2;
		optionRectangle.x = (GameValues.SCREEN_WIDTH - optionRectangle.width)/2;
		aboutRectangle.x = (GameValues.SCREEN_WIDTH - aboutRectangle.width)/2;
		startRectangle.y = 85;
		optionRectangle.y = 60;
		aboutRectangle.y = 35;
		
	}
	
	public void draw() {
		startButton.draw(GameValues.currentBatch, START_STRING, startRectangle.x, startRectangle.y + 15);
		optionButton.draw(GameValues.currentBatch, OPTION_STRING, optionRectangle.x, optionRectangle.y + 15);
		aboutButton.draw(GameValues.currentBatch, ABOUT_STRING, aboutRectangle.x, aboutRectangle.y + 15);
	}
	
	public void checkEvents() {
		if( Gdx.input.isTouched() && MenuControls.isMenuActive ) {
			
			if( EventUtils.isTap(MenuControls.getInstance().getStartRectangle()) ) {
				// MOVE TO GAME PROPER, ACTUALLY GAME DATA FIRST
				GameValues.currentScreen = new SaveDataScreen();
			}
			
			if( EventUtils.isTap(MenuControls.getInstance().getOptionRectangle()) ) {
				// MOVE OPTION SCREEN
				GameValues.currentScreen = new SettingScreen();
			}
			
			if( EventUtils.isTap(MenuControls.getInstance().getAboutRectangle()) ) {
				// MOVE ABOUT SCREEN
				GameValues.currentScreen = new AboutScreen();
			}
			
			EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
		}
	}

	public BitmapFont getStartButton() {
		return startButton;
	}

	public void setStartButton(BitmapFont startButton) {
		this.startButton = startButton;
	}

	public BitmapFont getOptionButton() {
		return optionButton;
	}

	public void setOptionButton(BitmapFont optionButton) {
		this.optionButton = optionButton;
	}

	public BitmapFont getAboutButton() {
		return aboutButton;
	}

	public void setAboutButton(BitmapFont aboutButton) {
		this.aboutButton = aboutButton;
	}

	public Rectangle getStartRectangle() {
		return startRectangle;
	}

	public void setStartRectangle(Rectangle startRectangle) {
		this.startRectangle = startRectangle;
	}

	public Rectangle getOptionRectangle() {
		return optionRectangle;
	}

	public void setOptionRectangle(Rectangle optionRectangle) {
		this.optionRectangle = optionRectangle;
	}

	public Rectangle getAboutRectangle() {
		return aboutRectangle;
	}

	public void setAboutRectangle(Rectangle aboutRectangle) {
		this.aboutRectangle = aboutRectangle;
	}
	
}
