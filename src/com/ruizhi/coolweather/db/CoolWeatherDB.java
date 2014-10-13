package com.ruizhi.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ruizhi.coolweather.model.City;
import com.ruizhi.coolweather.model.County;
import com.ruizhi.coolweather.model.Province;

/**
 * 数据库操作类(单例)
 * @author Jay
 *
 */
public class CoolWeatherDB {
	/**数据库名*/
	private static final String DB_NAME = "coolweather";
	/**省表*/
	private static final String PROVINCE_TABLE = "province";
	/**市表*/
	private static final String CITY_TABLE = "city";
	/**县表*/
	private static final String COUNTY_TABLE = "county";
	/**省表name字段*/
	private static final String PROVINCE_NAME = "name";
	/**省表code字段*/
	private static final String PROVINCE_CODE = "code";
	/**市表name字段*/
	private static final String CITY_NAME = "name";
	/**市表code字段*/
	private static final String CITY_CODE = "code";
	/**县表name字段*/
	private static final String COUNTY_NAME = "name";
	/**县表code字段*/
	private static final String COUNTY_CODE = "code";
	
	private static final int VERSION = 1;
	private CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	
	private CoolWeatherDB(Context context) {
		CoolWeatherSQLiteHelper dbHelper = new CoolWeatherSQLiteHelper(context, 
				DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	public CoolWeatherDB getInstance(Context context) {
		synchronized (this) {
			if(coolWeatherDB == null) {
				coolWeatherDB = new CoolWeatherDB(context);
			}
			return coolWeatherDB;
		}
	}
	
	/**
	 * 保存省
	 * @param province
	 */
	public void saveProvince(Province province) {
		ContentValues values = new ContentValues();
		values.put(PROVINCE_NAME, province.getName());
		values.put(PROVINCE_CODE, province.getCode());
		db.insert(PROVINCE_TABLE, null, values);
	}
	
	/**
	 * 加载数据库保存的省份
	 * @return
	 */
	public List<Province> loadProvinces() {
		List<Province> provinceList = new ArrayList<Province>();
		Province province = null;
		Cursor cursor = db.query(DB_NAME, null, null, null, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				province = new Province();
				String name = cursor.getString(cursor.getColumnIndex(PROVINCE_NAME));
				String code = cursor.getString(cursor.getColumnIndex(PROVINCE_CODE));
				province.setName(name);
				province.setCode(code);
				provinceList.add(province);
			} while(cursor.moveToNext());
		}
		return provinceList;
	}
	
	/**
	 * 保存市
	 * @param province
	 */
	public void saveCity(City city) {
		ContentValues values = new ContentValues();
		values.put(CITY_NAME, city.getName());
		values.put(CITY_CODE, city.getCode());
		db.insert(CITY_TABLE, null, values);
	}
	
	/**
	 * 加载数据库特定省份下面的城市
	 * @param provinceId
	 * @return
	 */
	public List<City> loadCities(int provinceId) {
		List<City> cityList = new ArrayList<City>();
		City city = null;
		Cursor cursor = db.query(DB_NAME, null, "province_id = ?", 
				new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				city = new City();
				String name = cursor.getString(cursor.getColumnIndex(CITY_NAME));
				String code = cursor.getString(cursor.getColumnIndex(CITY_CODE));
				city.setName(name);
				city.setCode(code);
				cityList.add(city);
			} while(cursor.moveToNext());
		}
		return cityList;
	}
	
	/**
	 * 保存县
	 * @param province
	 */
	public void saveCounty(County county) {
		ContentValues values = new ContentValues();
		values.put(COUNTY_NAME, county.getName());
		values.put(COUNTY_CODE, county.getCode());
		db.insert(COUNTY_TABLE, null, values);
	}
	/**
	 * 加载数据库特定城市下面的县
	 * @param cityId
	 */
	public List<County> loadCounties(int cityId) {
		List<County> countyList = new ArrayList<County>();
		County county = null;
		Cursor cursor = db.query(DB_NAME, null, "province_id = ?", 
				new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()) {
			do{
				county = new County();
				String name = cursor.getString(cursor.getColumnIndex(COUNTY_NAME));
				String code = cursor.getString(cursor.getColumnIndex(COUNTY_CODE));
				county.setName(name);
				county.setCode(code);
				countyList.add(county);
			} while(cursor.moveToNext());
		}
		return countyList;
	}
}
