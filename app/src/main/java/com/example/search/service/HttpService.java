package com.example.search.service;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import android.accounts.NetworkErrorException;
/*暂时不用*/
public class HttpService {
	
	String path = "http://192.168.1.109:8080/webTest/LoginServlet";
	public void post(String phoneNumber, String deviceId,String userName,String password) throws Exception {
		String params = "phonenumber="+URLEncoder.encode(phoneNumber, "UTF-8")+"&deviceId="+URLEncoder.encode(deviceId, "UTF-8")+"&userName="+URLEncoder.encode(userName, "UTF-8")+"&password="+URLEncoder.encode(password, "UTF-8");
	
		URL url = new URL(path);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setConnectTimeout(3000);
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");	// 设置请求头
		conn.setRequestProperty("Content-Length", params.getBytes().length + "");
		conn.setRequestProperty("Host", "192.168.1.1:8080");
	
		conn.setDoOutput(true);								// 设置当前请求需要输出数据
		conn.getOutputStream().write(params.getBytes());	// 以消息体形式写出数据
		
		int code = conn.getResponseCode();
		if (code != 200) {
			throw new NetworkErrorException("Code: " + code);
			
		}
	}

}
