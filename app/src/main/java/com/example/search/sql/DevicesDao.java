package com.example.search.sql;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DevicesDao {

	private DeviceSQLiteOpenHelper mHelper;
	private SQLiteDatabase db;
	public DevicesDao(Context context) {
		super();
		mHelper = new DeviceSQLiteOpenHelper(context);
		//因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
		//db = mHelper.getWritableDatabase();
	}

	
	
	public long add(String devicesId, String Name,
			int Recommend, int History, int Collection,
			int Isprivate,int userlevel,String Introduciton, String Logo){
		
		SQLiteDatabase db = this.mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("devicesId", devicesId);
		values.put("Name", Name);
		values.put("Recommend", Recommend);
		values.put("History", History);
		values.put("Collection", Collection);
		values.put("Isprivate", Isprivate);
		values.put("userlevel", userlevel);
		values.put("Introduciton", Introduciton);
		values.put("Logo", Logo);
		
		long l = db.insert("merchant", null, values);
		db.close();
		return l;
	}
	
	public long add(Devices devices){
		
		SQLiteDatabase db = this.mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("devicesId", devices.getDevicesId());
		values.put("Name", devices.getName());
		values.put("Recommend", devices.getRecommend());
		values.put("History", devices.getHistory());
		values.put("Collection", devices.getCollection());
		values.put("Isprivate", devices.getIsprivate());
		values.put("userlevel", devices.getUserlevel());
		values.put("Introduciton", devices.getIntroduciton());
		values.put("Logo", devices.getLogo());
		
		long l = db.insert("merchant", null, values);
		db.close();
		return l;
	}
	

	
	public ArrayList<Devices> findAll() {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		ArrayList<Devices> devices = new ArrayList<Devices>();
		Cursor cursor = db.query("merchant", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String devicesId = cursor.getString(cursor.getColumnIndex("devicesId"));
			String name = cursor.getString(cursor.getColumnIndex("Name"));
			int recommend = cursor.getInt(cursor.getColumnIndex("Recommend"));
			int history = cursor.getInt(cursor.getColumnIndex("History"));
			int collection = cursor.getInt(cursor.getColumnIndex("Collection"));
			int isprivate = cursor.getInt(cursor.getColumnIndex("Isprivate"));
			int userlevel = cursor.getInt(cursor.getColumnIndex("userlevel"));
			String introduciton = cursor.getString(cursor.getColumnIndex("Introduciton"));
			String logo = cursor.getString(cursor.getColumnIndex("Logo"));
			
			Devices d = new Devices(id, devicesId, name, recommend, history, collection, isprivate, userlevel, introduciton, logo);
			devices.add(d);
		}
		cursor.close();
		db.close();
		return devices;

	}
}
