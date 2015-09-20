package com.example.search;



import java.util.ArrayList;
import java.util.List;

import com.example.search.adapter.MyListViewAdapter;
import com.example.search.sql.Devices;
import com.example.search.sql.DevicesDao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HistoryFragment extends Fragment {

	private ListView mHistoryList;
	private MyListViewAdapter mAdapter;
	private List<Devices> list = new ArrayList<Devices>();
	private DevicesDao dao;
     @Override
    public void onCreate( Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);

    }
     @Override
    public View onCreateView(LayoutInflater inflater,
    		 ViewGroup container,  Bundle savedInstanceState) {
       View parent = inflater.inflate(R.layout.history_fragment, container, false);
       /*mHistoryList = (ListView) parent.findViewById(R.id.history_app_list);
       getDevices();
       
       mAdapter = new MyListViewAdapter(getActivity(), list);
       mHistoryList.setAdapter(mAdapter);*/
    		   
    	return parent;
    }
     
     private void getDevices(){
    	//获取数据库
         dao = new DevicesDao(getActivity());
         list.addAll(dao.findAll());
     }
     

}
