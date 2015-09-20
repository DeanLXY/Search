package com.example.search.sql;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Devices implements Serializable{
	
	private int Id;
	private String DevicesId;
	private String Name;
	private int Recommend;
	private int History;
	private int Collection;
	private int Isprivate;
	private int userlevel;
	private String Introduciton;
	private String Logo;
	
	
	public Devices() {
		super();
	}
	public Devices(JSONObject response) throws JSONException{
		super();
		 Id = response.getInt("id");
		 Name = response.getString("name");
		 Introduciton = response.getString("desc");
         Logo = response.getString("url");
         Introduciton = response.getString("desc");
	}
	
	public Devices(int id, String devicesId, String name, int recommend,
			int history, int collection, int isprivate, int userlevel,
			String introduciton, String logo) {
		super();
		Id = id;
		DevicesId = devicesId;
		Name = name;
		Recommend = recommend;
		History = history;
		Collection = collection;
		Isprivate = isprivate;
		this.userlevel = userlevel;
		Introduciton = introduciton;
		Logo = logo;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDevicesId() {
		return DevicesId;
	}
	public void setDevicesId(String devicesId) {
		DevicesId = devicesId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getRecommend() {
		return Recommend;
	}
	public void setRecommend(int recommend) {
		Recommend = recommend;
	}
	public int getHistory() {
		return History;
	}
	public void setHistory(int history) {
		History = history;
	}
	public int getCollection() {
		return Collection;
	}
	public void setCollection(int collection) {
		Collection = collection;
	}
	public int getIsprivate() {
		return Isprivate;
	}
	public void setIsprivate(int isprivate) {
		Isprivate = isprivate;
	}
	public String getIntroduciton() {
		return Introduciton;
	}
	public void setIntroduciton(String string) {
		Introduciton = string;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String string) {
		Logo = string;
	}
	
	
	public int getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(int userlevel) {
		this.userlevel = userlevel;
	}
	@Override
	public String toString() {
		return "Devices [Id=" + Id + ", DevicesId=" + DevicesId + ", Name="
				+ Name + ", Recommend=" + Recommend + ", History=" + History
				+ ", Collection=" + Collection + ", Isprivate=" + Isprivate
				+ ", userlevel=" + userlevel + ", Introduciton=" + Introduciton
				+ ", Logo=" + Logo + "]";
	}

}
