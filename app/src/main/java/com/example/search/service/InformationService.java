package com.example.search.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import android.accounts.NetworkErrorException;
import android.util.Log;
import android.util.Xml;

import com.example.search.sql.Devices;

public class InformationService {
	/**
	 * 请求服务端, 得到包含数据的XML, 解析XML, 得到List<Blog>
	 * @return	包含Devices对象的List集合
	 * @throws Exception 
	 * 
	 * 這個鏈接暫時也不用
	 */
	public List<Devices> getDevices() throws Exception {
		Log.e("ggj", "getDevices:");
		String path = "http://115.28.227.2:8080/admin/app/queryTag?id=11";
		
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);

		int code = conn.getResponseCode();
		Log.e("ggj", "code:"+code);
		if (code == 200) {
			
			
			 List<Devices>listNews=parseJson(conn.getInputStream());

			
			
			
			return parseXML(conn.getInputStream());
		}

		throw new NetworkErrorException("网络异常: " + code);
		
	}
   //xml解析
	public List<Devices> parseXML(InputStream inputStream) throws Exception {	
		Log.e("ggj", "parseXML:");
		List<Devices> list = new ArrayList<Devices>();
		Devices devices = null;
		
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inputStream, "UTF-8");
		for (int type = parser.getEventType(); type != XmlPullParser.END_DOCUMENT; type = parser.next()) {
			if (type == XmlPullParser.START_TAG) {
				if ("information".equals(parser.getName())) {
					devices = new Devices();
					list.add(devices);
				}else if ("logo".equals(parser.getName())) {
					devices.setLogo(parser.nextText());
				}else if ("name".equals(parser.getName())) {
					devices.setName(parser.nextText());
				}else if ("introduction".equals(parser.getName())) {
					devices.setIntroduciton(parser.nextText());
				}else if ("userlevel".equals(parser.getName())) {
					devices.setUserlevel(parser.next());
				}else if ("isprivate".equals(parser.getName())) {
					devices.setIsprivate(parser.next());
				}
			}
		}
		
		return list;
	}
	
	//json解析
	
	public List<Devices> parseJson(InputStream inputStream){
		  List<Devices> listNews = new ArrayList<Devices>();
	        byte[] jsonBytes = convertIsToByteArray(inputStream);
	        String json = new String(jsonBytes);
	        try {
	            JSONObject jo=new JSONObject(json);
	                //JSONObject jo = jsonArray.getJSONObject(i);
	                
	            Devices de = new Devices();
	            de.setId(jo.getInt("id"));
	            de.setLogo(jo.getString("url"));
	            de.setIntroduciton(jo.getString("desc"));
	                
	            listNews.add(de);
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return listNews;
	}
	
	
	 private byte[] convertIsToByteArray(InputStream inputStream) {
	        ByteArrayOutputStream baos=new ByteArrayOutputStream();
	        byte buffer[]=new byte[1024];
	        int length=0;
	        try {
	            while ((length=inputStream.read(buffer))!=-1) {
	                baos.write(buffer, 0, length);             
	            }
	            inputStream.close();
	            baos.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return baos.toByteArray();
	    }

}
