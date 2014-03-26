package com.eciz.evosciencia.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.screens.AboutScreen;
import com.eciz.evosciencia.screens.SaveDataScreen;
import com.eciz.evosciencia.screens.SettingScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class MenuControls {
	
	private Texture startButton;
	private Texture optionButton;
	private Texture aboutButton;
	
	private Rectangle startRectangle;
	private Rectangle optionRectangle;
	private Rectangle aboutRectangle;
	
	private final int DIMENSION = 70;
	
	public static boolean isMenuActive = false;
	
	private static MenuControls instance = null;

	public static MenuControls getInstance() {
		if( instance == null )
			instance = new MenuControls();
		isMenuActive = true;
		return instance;
	}

	public MenuControls() {
		startButton = new Texture(Gdx.files.internal("images/start_btn.png"));
		optionButton = new Texture(Gdx.files.internal("images/options_btn.png"));
		aboutButton = new Texture(Gdx.files.internal("images/about_btn.png"));
		
		startRectangle = new Rectangle();
		optionRectangle = new Rectangle();
		aboutRectangle = new Rectangle();
		
		startRectangle.x = ((GameValues.SCREEN_WIDTH - optionRectangle.width)/2) - (DIMENSION + (DIMENSION/2) + 20);
		optionRectangle.x = ((GameValues.SCREEN_WIDTH - optionRectangle.width)/2) - (DIMENSION/2);
		aboutRectangle.x = ((GameValues.SCREEN_WIDTH - aboutRectangle.width)/2) + (DIMENSION-20);
		startRectangle.y = 50;
		optionRectangle.y = 50;
		aboutRectangle.y = 50;
		
		startRectangle.width = 70;
		optionRectangle.width = 70;
		aboutRectangle.width = 70;
		startRectangle.height = 70;
		optionRectangle.height = 70;
		aboutRectangle.height = 70;
		
	}
	
	public void draw() {
		GameValues.currentBatch.draw(startButton, startRectangle.x, startRectangle.y, startRectangle.width, startRectangle.height);
		GameValues.currentBatch.draw(optionButton, optionRectangle.x, optionRectangle.y, optionRectangle.width, optionRectangle.height);
		GameValues.currentBatch.draw(aboutButton, aboutRectangle.x, aboutRectangle.y, aboutRectangle.width, aboutRectangle.height);
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

	public Texture getStartButton() {
		return startButton;
	}

	public void setStartButton(Texture startButton) {
		this.startButton = startButton;
	}

	public Texture getOptionButton() {
		return optionButton;
	}

	public void setOptionButton(Texture optionButton) {
		this.optionButton = optionButton;
	}

	public Texture getAboutButton() {
		return aboutButton;
	}

	public void setAboutButton(Texture aboutButton) {
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

	public static boolean isMenuActive() {
		return isMenuActive;
	}

	public static void setMenuActive(boolean isMenuActive) {
		MenuControls.isMenuActive = isMenuActive;
	}

	public static void setInstance(MenuControls instance) {
		MenuControls.instance = instance;
	}

}
