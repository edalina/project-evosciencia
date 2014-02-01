package com.eciz.evosciencia.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.values.GameValues;
import com.eciz.evosciencia.values.HelpValues;

public class DialogUtils {
	
	public static boolean isDialogActive = false;
	
	private static BitmapFont dialogText;
	private static BitmapFont nextText;
	private static Rectangle nextRect;
	
	private static Texture dialogTexture;
	private static Texture helpAvatar;
	private static Texture charAvatar;
	
	private static String dialog = "";
	
	private static final float DIALOG_WIDTH = (GameValues.SCREEN_WIDTH/2) * GameValues.CAMERA_ZOOM;
	private static final float DIALOG_HEIGHT = (GameValues.SCREEN_HEIGHT/4) * GameValues.CAMERA_ZOOM;
	private static final float DIALOG_X = GameValues.camera.position.x - (DIALOG_WIDTH/2);
	private static final float DIALOG_Y = GameValues.camera.position.y - (DIALOG_HEIGHT*2-10);
	
	private static int idx = 0;
	
	public DialogUtils() {
		dialogTexture = new Texture(Gdx.files.internal("images/dialog.png"));
		helpAvatar = new Texture(Gdx.files.internal("images/einstein.png"));
		
		dialogText = new BitmapFont();
		dialogText.setScale(GameValues.CAMERA_ZOOM);
		dialogText.setColor(0, 0, 0, 1);
		
		nextText = new BitmapFont();
		nextText.setScale(GameValues.CAMERA_ZOOM);
		nextText.setColor(0, 0, 0, 1);
		nextRect = new Rectangle();
		
		nextRect.width = nextText.getBounds("next").width;
		nextRect.height = 15;
		nextRect.x = DIALOG_X + DIALOG_WIDTH - (nextRect.width) - 10;
		nextRect.y = DIALOG_Y + 10 - nextRect.height;
	}

	public static void introDialogs() {
		if( isDialogActive ) {
			Dpad.isDpadActive = false;
			if( idx == HelpValues.INTRO.length ) {
				isDialogActive = false;
				Dpad.isDpadActive = true;
				GameValues.isNewGame = false;
				return;
			}
			GameValues.currentBatch.draw(helpAvatar, DIALOG_X - 100, DIALOG_Y - 50, 167, 200);
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.drawWrapped(GameValues.currentBatch, HelpValues.INTRO[idx], DIALOG_X + 10, DIALOG_Y + DIALOG_HEIGHT - 10, DIALOG_WIDTH - 20);
			nextText.draw(GameValues.currentBatch, "next", nextRect.getX(), nextRect.getY()+15);
			if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					if( EventUtils.isTap(nextRect) ) {
						idx++;
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createDialog(String value) {
		dialog = value;
		createDialog();
	}
	
	public static void createDialog() {
		if( !dialog.equals("") ) {
//			Dpad.isDpadActive = false;
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.drawWrapped(GameValues.currentBatch, dialog, DIALOG_X + 10, DIALOG_Y + DIALOG_HEIGHT - 10, DIALOG_WIDTH - 20);
			nextText.draw(GameValues.currentBatch, "next", nextRect.getX(), nextRect.getY()+15);if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					if( EventUtils.isTap(nextRect) ) {
						System.out.println( "ACCEPTED" );
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createAvatarDialog() {
		
	}
	
}
