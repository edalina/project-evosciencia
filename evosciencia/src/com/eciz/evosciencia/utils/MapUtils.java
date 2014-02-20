package com.eciz.evosciencia.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eciz.evosciencia.resources.Maps;

public class MapUtils {

	public static int getCurrentMapIndex() {
		String currentMapName = Maps.getMaps().name;
//		String currentMapName = "map1_1";
		Pattern pattern = Pattern.compile("map([1-9])");
		Matcher matcher = pattern.matcher(currentMapName);
		System.out.println( "1" );
		if( matcher.find() ) {
			System.out.println( "hhmmm" );
			return Integer.parseInt(matcher.group(1))-1;
		}
		return -1;
	}
	
}
