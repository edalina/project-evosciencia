package com.eciz.evosciencia.utils;

import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.values.GameValues;

public class EventUtils {
	
	public static boolean isTap(Rectangle rectangle) {
		if( GameValues.touchPos.x >= rectangle.x &&
			GameValues.touchPos.x <= rectangle.x + rectangle.width &&
			GameValues.touchPos.y >= rectangle.y &&
			GameValues.touchPos.y <= rectangle.y + rectangle.height) {
			return true;
		}
		return false;
	}

}
