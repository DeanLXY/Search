package com.example.search.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Menu implements Serializable{
	
	private ArrayList<String> menuList;
	private Map<String, List<String>> menuDetails;
	
	
	
	public Menu(JSONArray jsonArray) throws JSONException{
		menuList =  new ArrayList<String>();
		menuDetails = new HashMap<String, List<String>>();
		for(int i=0; i<jsonArray.length(); i++){
			JSONObject jo = jsonArray.getJSONObject(i);
			String typename = jo.getString("typename").trim();
			
			List menuDetail = parseMenu(typename, jo.getJSONArray("foodList"));
			menuList.add(typename);
			menuDetails.put(typename, menuDetail);
		}
		
	}
	
	public List parseMenu(String desc, JSONArray jsonArray) throws JSONException{
		List list = new ArrayList<String>();
		list.add(desc.trim());
		for(int i=0; i<jsonArray.length(); i++){
			JSONObject jo = jsonArray.getJSONObject(i);
			
			String menuName = jo.getString("name");
			
			list.add(menuName.trim());
		}
		
		return list;
	}

	public ArrayList<String> getMenuList() {
		return menuList;
	}

	public Map<String, List<String>> getMenuDetails() {
		return menuDetails;
	}

}
