package com.eciz.evosciencia.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.eciz.evosciencia.dao.helper.DatabaseHelper;
import com.eciz.evosciencia.dao.model.AvatarModel;

public class AvatarDAO {
	
	public AvatarDAO(Context context) {
		new DatabaseHelper(context);
	}
	
	public void addAvatar(AvatarModel avatarModel) {
		ContentValues values = new ContentValues();
		values.put("coordinate", avatarModel.getCoordinate());
		values.put("playtime", avatarModel.getPlaytime());
		
		DatabaseHelper.mainDb.insert("avatar", null, values);
		DatabaseHelper.mainDb.close();
	}
	
	public AvatarModel getAvatarModel(int id) {
		
		Cursor cursor = DatabaseHelper.mainDb.query("avatar",
				new String[]{"id", "coordinate", "playtime"},
				"id=?" + new String[]{String.valueOf(id)},
				null, null, null, null);
		
		if( cursor != null ) {
			cursor.moveToFirst();
		} else {
			return null;
		}
		
		return new AvatarModel(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
	}
//	
//	public List<AvatarModel> getAllAvatarInfo() {
//		
//	}

}
