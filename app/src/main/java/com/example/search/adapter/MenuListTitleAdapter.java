package com.example.search.adapter;

import java.util.zip.Inflater;

import com.example.search.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuListTitleAdapter extends BaseAdapter{
	private Context mContext;
	private String[] array;
	private LayoutInflater inflater;
	
	public MenuListTitleAdapter(Context context,String[] arr){
		mContext = context;
		array = arr;
		inflater = (LayoutInflater) context  
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	}
	
	@Override
	public int getCount() {
		return array.length;
	}

	@Override
	public Object getItem(int position) {
		return array[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.list_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.textItem);
		tv.setText(array[position]);
		
		return convertView;
	}

}
