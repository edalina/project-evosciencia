package com.eciz.evosciencia.utils;

import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.values.GameValues;

public class AvatarUtils {

	public static void repositionAvatarByCoordinate() {
		GameValues.avatar.repositionAvatar(GameValues.user.getCoordinate().getX(), GameValues.user.getCoordinate().getY());
		Dpad.positionDpad();
	}
	
}
