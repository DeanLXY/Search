package com.example.search.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.search.MenuListActivity;
import com.example.search.R;
import com.example.search.adapter.MyListViewAdapter.ViewHolder;
import com.example.search.sql.Devices;
import com.loopj.android.image.SmartImageView;

public class MenuDetailAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<String> list;
	// 用来导入布局  
    private LayoutInflater inflater = null; 
	
	public MenuDetailAdapter(Context context,List<String> list){
		this.mContext = context;
		this.list = list;
		inflater = (LayoutInflater) context  
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	}
	
	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
	
		ViewHolder mViewHolder = null;
		if (mViewHolder == null) {
			
			mViewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.details_item,null);
			
			mViewHolder.title = (TextView) convertView.findViewById(R.id.textItem);
			/*mViewHolder.count = (TextView) convertView.findViewById(R.id.count);
			mViewHolder.content = (TextView) convertView.findViewById(R.id.content);
			mViewHolder.icon = (SmartImageView) convertView.findViewById(R.id.icon);
			mViewHolder.collect_app = (Button) convertView.findViewById(R.id.collect_app);*/
			
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		//final Devices devices = mList.get(position);
		mViewHolder.title.setText(list.get(position));
		/*mViewHolder.count.setText("有34253人添加收藏");
		mViewHolder.content.setText(devices.getIntroduciton());*/
		
	    /*
		 * 开启新线程加载图片
		 * 加载过程中会显示加载图片
		 * 加载图片之后, 会在内存中缓存, 本地文件缓存
		 * 加载图片如果失败, 会显示失败图片
		 */
		/*mViewHolder.icon.setImageUrl(devices.getLogo().toString(), R.drawable.ic_launcher, R.drawable.ic_launcher);
		
		
		mViewHolder.collect_app.setText("收藏");
		mViewHolder.collect_app.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "收藏:"+devices.getName(), Toast.LENGTH_SHORT).show();
			}
		});*/
		
		return convertView;
	}
	
	static class ViewHolder {
	    TextView title;
	   /* TextView count;
	    TextView content;
	    SmartImageView icon;
	    Button collect_app;*/
	}
}
