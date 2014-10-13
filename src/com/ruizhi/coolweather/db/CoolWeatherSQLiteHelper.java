package com.ruizhi.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherSQLiteHelper extends SQLiteOpenHelper {
	/**����ʡ��*/
	private static final String CREATE_PROVINCE = "create table province ("
			+ "id int primary key autoincrement, "
			+ "name text, "
			+ "code text)";
	
	/**�����б�*/
	private static final String CREATE_CITY = "create table city ("
			+ "id int primary key autoincrement, "
			+ "name text, "
			+ "code text, "
			+ "provinceId int";
	
	/**�����ر�*/
	private static final String CREATE_COUNTY = "create table county ("
			+ "id int primary key autoincrement, "
			+ "name text, "
			+ "code text, "
			+ "cityId int";
	
	public CoolWeatherSQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
