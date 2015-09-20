package com.example.search.adapter;



import java.util.List;

import com.example.search.R;
import com.example.search.sql.Devices;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListViewAdapter extends BaseAdapter {
	
	List<Devices> mList;
	// 用来导入布局  
    private LayoutInflater inflater = null; 
    Context mContext;
	
	public MyListViewAdapter(Context context,List<Devices> list){
		this.mList = list;
		this.mContext = context;
		inflater = (LayoutInflater) context  
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder = null;
		if (mViewHolder == null) {
			
			mViewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_view_item,null);
			
			mViewHolder.title = (TextView) convertView.findViewById(R.id.title);
			mViewHolder.count = (TextView) convertView.findViewById(R.id.count);
			mViewHolder.content = (TextView) convertView.findViewById(R.id.content);
			mViewHolder.icon = (SmartImageView) convertView.findViewById(R.id.icon);
			mViewHolder.collect_app = (Button) convertView.findViewById(R.id.collect_app);
			
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		final Devices devices = mList.get(position);
		mViewHolder.title.setText(devices.getName());
		mViewHolder.count.setText("有34253人添加收藏");
		mViewHolder.content.setText(devices.getIntroduciton());
		
	    /*
		 * 开启新线程加载图片
		 * 加载过程中会显示加载图片
		 * 加载图片之后, 会在内存中缓存, 本地文件缓存
		 * 加载图片如果失败, 会显示失败图片
		 */
		mViewHolder.icon.setImageUrl(devices.getLogo().toString(), R.drawable.ic_launcher, R.drawable.ic_launcher);
		
		
		mViewHolder.collect_app.setText("收藏");
		mViewHolder.collect_app.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "收藏:"+devices.getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		return convertView;
	}
	static class ViewHolder {
	    TextView title;
	    TextView count;
	    TextView content;
	    SmartImageView icon;
	    Button collect_app;
	}
}
