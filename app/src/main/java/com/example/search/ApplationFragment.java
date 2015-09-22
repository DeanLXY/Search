package com.example.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.search.adapter.MyListViewAdapter;
import com.example.search.bean.Menu;
import com.example.search.network.HttpManager;
import com.example.search.network.IRequestCallBack;
import com.example.search.sql.Devices;
import com.example.search.sql.DevicesDao;
import com.example.search.ui.fragment.ABaseFragment;
import com.example.search.ui.fragment.ARefreshFragment;
import com.example.search.ui.fragment.ASwiperefreshDelayFragment;
import com.example.search.utils.DialogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApplationFragment extends ASwiperefreshDelayFragment {

    private ListView mAppList;
    private TextView mSearchCount;
    private MyListViewAdapter adapter;

    private List<Devices> list = new ArrayList<Devices>();
    DevicesDao dao;
    private String path;
    private View layoutAd;

    @Override
    protected int layoutResourceID() {
        return R.layout.applation_fragment;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        layoutAd = inflater.inflate(R.layout.layout_ad_application, null);
        mSearchCount = (TextView) layoutAd.findViewById(R.id.search_count);
        mAppList = (ListView) findViewById(R.id.app_list);
        mAppList.addHeaderView(layoutAd);
        adapter = new MyListViewAdapter(getActivity(), list);
        mSearchCount.setText("共搜索出" + list.size() + "条");
        dao = new DevicesDao(getActivity());
        mAppList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 获取本机手机号
                TelephonyManager phoneMgr = (TelephonyManager) getActivity()
                        .getSystemService(getActivity().TELEPHONY_SERVICE);
                //http://115.28.227.2:8080/admin/app/queryTagDetail?id=YLAB00000001
                path = "http://115.28.227.2:8080/admin/app/queryTagDetail?id=";
                queryDetail(path + "YLAB00000001", null);
            }
        });

        mAppList.setAdapter(adapter);
    }

    @Override
    public void requestData() {
        super.requestData();
        // 搜索到蓝牙，发送 手机号+设备号+用户名+密码 ，到服务器端
        for (int i = 0; i < 16; i++) {
            post("12345678", "YLAB0000000" + i, "name", "password");
        }

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        requestData();
    }

    // 异步线程框架
    private void post(String phoneNumber, String deviceId, String userName,
                      String password) {
        //http://115.28.227.2:8080/admin/app/queryTag?id=YLAB00000001
        String path = "http://115.28.227.2:8080/admin/app/queryTag?id=";
        Map<String, String> params = new HashMap<String, String>();
        params.put("phoneNumber", phoneNumber);
        params.put("deviceId", deviceId);
        params.put("username", userName);
        params.put("password", password);
        HttpManager http = new HttpManager(getActivity(), new IRequestCallBack() {
            @Override
            public void onFailure(int requestCode, String message) {
                if (getRefreshLayout().isRefreshing())
                    getRefreshLayout().setRefreshing(false);
            }

            @Override
            public void onResponse(Object t) {
                if (getRefreshLayout().isRefreshing())
                    getRefreshLayout().setRefreshing(false);
                Devices devices = (Devices) t;
                list.add(devices);
                adapter.notifyDataSetChanged();
            }
        });
        http.post(path + deviceId, params, Devices.class);
    }

    private Object queryDetail(String path, String id) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        // &phone=13439773956&password=123456
        params.put("phone", "13439773956");
        params.put("password", "123456");

        client.get(path, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                if (statusCode == 200) {
                    try {
                        Log.e("ggj", "success");
                        JSONArray jsonArray = response.getJSONArray("cList");

                        System.out.println(jsonArray);
                        Menu menu = new Menu(jsonArray);


                        //jsonArray
//						Devices devices = new Devices(response);
//						
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("menu", menu);
//						//Intent intent = new Intent(getActivity(), CompanyDetailActivity.class);
                        Intent intent = new Intent(getActivity(), MenuListActivity.class);
                        intent.putExtra("menu", bundle);
                        startActivity(intent);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

//			@Override
//			public void onSuccess(int statusCode, JSONObject response) {
//				if (statusCode == 200) {
//					try {
//						Devices devices = new Devices(response);
//						list.add(devices);
//
//						adapter.notifyDataSetChanged();
//					} catch (JSONException e) {
//						throw new Error(e);
//					}
//				}
//			}
        });

        return null;
    }

    @Override
    public void onStripTabRequestData() {
//        requestData();
    }
}
