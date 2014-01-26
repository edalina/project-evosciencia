package com.eciz.evosciencia.dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eciz.evosciencia.values.GameValues;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static SQLiteDatabase mainDb = null;
	
	private static final int VERSION = 1;
	
	private static final String NEW_SQL = "BEGIN TRANSACTION;" +
			"CREATE TABLE avatar( id REAL PRIMARY KEY, coordinate TEXT, playtime REAL);" +
			"CREATE TABLE scientists( id REAL PRIMARY KEY, name TEXT );" +
			"CREATE TABLE items( id REAL PRIMARY KEY, name TEXT, description TEXT, bonus TEXT, type TEXT);" +
			"CREATE TABLE game_maps( sprite TEXT, npcId REAL );" +
			"CREATE TABLE monsters( sprite TEXT, level REAL, name TEXT );" +
			"COMMIT;";
	
	public DatabaseHelper(Context context) {
		super(context, GameValues.DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NEW_SQL);
		
		mainDb = this.getWritableDatabase();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropSql = "BEGIN TRANSACTION;" +
				"DROP TABLE IF EXISTS avatar;" +
				"DROP TABLE IF EXISTS scientists;" +
				"DROP TABLE IF EXISTS items;" +
				"DROP TABLE IF EXISTS game_maps;" +
				"DROP TABLE IF EXISTS monsters;" +
				"COMMIT;";
		db.execSQL(dropSql);
		
		onCreate(db);
	}
	
}
