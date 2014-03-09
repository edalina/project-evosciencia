package com.eciz.evosciencia.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.values.GameValues;

public class EventUtils {
	
	public static boolean isTap(Rectangle rectangle) {
		GameValues.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		GameValues.camera.unproject(GameValues.touchPos);
		if( GameValues.touchPos.x >= rectangle.x &&
			GameValues.touchPos.x <= rectangle.x + rectangle.width &&
			GameValues.touchPos.y >= rectangle.y &&
			GameValues.touchPos.y <= rectangle.y + rectangle.height) {
			return true;
		}
		return false;
	}
	
	public static StanceEnum avatarAttacking() {
		StanceEnum attackFace = null;
		switch(GameValues.avatar.facingFlag) {
			case FRONT_STAND:
				attackFace = StanceEnum.FRONT_ATTACK;
				break;
			case BACK_STAND:
				attackFace = StanceEnum.BACK_ATTACK;
				break;
			case LEFT_STAND:
				attackFace = StanceEnum.LEFT_ATTACK;
				break;
			case RIGHT_STAND:
				attackFace = StanceEnum.RIGHT_ATTACK;
				break;
			default:
				break;
		}
		return attackFace;
	}

}
