package com.eciz.evosciencia.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.screens.GameScreen;
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
		startRectangle.y = 100;
		optionRectangle.y = 75;
		aboutRectangle.y = 50;
		
	}
	
	public void draw() {
		startButton.draw(GameValues.currentBatch, START_STRING, startRectangle.x, startRectangle.y);
		optionButton.draw(GameValues.currentBatch, OPTION_STRING, optionRectangle.x, optionRectangle.y);
		aboutButton.draw(GameValues.currentBatch, ABOUT_STRING, aboutRectangle.x, aboutRectangle.y);
	}
	
	public void checkEvents() {
		if( Gdx.input.isTouched() && MenuControls.isMenuActive ) {
			GameValues.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			GameValues.camera.unproject(GameValues.touchPos);
			
			if( GameValues.touchPos.x >= MenuControls.getInstance().getStartRectangle().x &&
				GameValues.touchPos.x <= MenuControls.getInstance().getStartRectangle().x + MenuControls.getInstance().getStartRectangle().width &&
				GameValues.touchPos.y >= MenuControls.getInstance().getStartRectangle().y - 15 &&
				GameValues.touchPos.y <= MenuControls.getInstance().getStartRectangle().y + MenuControls.getInstance().getStartRectangle().height - 15) {
				
				// MOVE TO GAME PROPER, ACTUALLY GAME DATA FIRST
				GameValues.currentScreen = new GameScreen();
				EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
			}
			
			if( GameValues.touchPos.x >= MenuControls.getInstance().getOptionRectangle().x &&
				GameValues.touchPos.x <= MenuControls.getInstance().getOptionRectangle().x + MenuControls.getInstance().getOptionRectangle().width &&
				GameValues.touchPos.y >= MenuControls.getInstance().getOptionRectangle().y - 15 &&
				GameValues.touchPos.y <= MenuControls.getInstance().getOptionRectangle().y + MenuControls.getInstance().getOptionRectangle().height - 15) {
				
				// MOVE OPTION SCREEN
				System.out.println( "OPTION" );
			}
			
			if( GameValues.touchPos.x >= MenuControls.getInstance().getAboutRectangle().x &&
				GameValues.touchPos.x <= MenuControls.getInstance().getAboutRectangle().x + MenuControls.getInstance().getAboutRectangle().width &&
				GameValues.touchPos.y >= MenuControls.getInstance().getAboutRectangle().y - 15 &&
				GameValues.touchPos.y <= MenuControls.getInstance().getAboutRectangle().y + MenuControls.getInstance().getAboutRectangle().height - 15) {
				
				// MOVE ABOUT SCREEN
				System.out.println( "ABOUT" );
			}
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
