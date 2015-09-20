package com.example.search.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DeviceSQLiteOpenHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "merchant.db";  
    private static final int DATABASE_VERSION = 1;

    public DeviceSQLiteOpenHelper(Context context) {  
        //CursorFactory设置为null,使用默认值  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    } 
    
	public DeviceSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {		
		String sql = "CREATE TABLE merchant(id integer primary key autoincrement,devicesId varchar(20),Name varchar(60),recommend integer(2),history integer(2),collection integer(2),isprivate integer(2),userlevel integer(2),introduction varchar(600),logo varchar(600))";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE person ADD COLUMN other STRING");  
	}

}
