package com.example.search.ui.activity;

import java.util.ArrayList;

import com.example.search.AccountFragment;
import com.example.search.ApplationFragment;
import com.example.search.CollectionFragment;
import com.example.search.CompanyDetailActivity;
import com.example.search.HistoryFragment;
import com.example.search.R;
import com.example.search.RecommendFragment;
import com.example.search.sql.Devices;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends BaseActivity implements View.OnClickListener{

	private ViewPager mViewPaper;

    
    private ApplationFragment mAFragment;
    private RecommendFragment mRFragment;
    private HistoryFragment mHFragment;
    private CollectionFragment mCFragment;
    private AccountFragment mAccountFragment;
    private CompanyDetailActivity mCompanyActivity;
    //标题Title
    private TextView onePagerTitle;
    private TextView twoPagerTitle;
    private TextView threePagerTitle;
    private TextView fourPagerTitle;
    private TextView fivePagerTitle;
    

    private int oldPosition = 0;//上一次页面位置

	private int currentItem;//当前页面
	
    private ArrayList<Fragment> fragmentList;

    private ArrayList<TextView> titleList = new ArrayList<TextView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() { 
		mViewPaper = (ViewPager) findViewById(R.id.viewpager);
	    mAFragment = new ApplationFragment();
	    mRFragment = new RecommendFragment();
	    mHFragment = new HistoryFragment();
	    mCFragment = new CollectionFragment();
	    mAccountFragment = new AccountFragment();
	    mCompanyActivity = new CompanyDetailActivity();
	    
	    fragmentList = new ArrayList<Fragment>();
	    fragmentList.add(mAFragment);
	    fragmentList.add(mRFragment);
	    fragmentList.add(mHFragment);
	    fragmentList.add(mCFragment);
	    fragmentList.add(mAccountFragment);

	    onePagerTitle = (TextView) findViewById(R.id.pager_one);
	    twoPagerTitle = (TextView) findViewById(R.id.pager_two);
	    threePagerTitle = (TextView) findViewById(R.id.pager_three);
	    fourPagerTitle = (TextView) findViewById(R.id.pager_four);
	    fivePagerTitle = (TextView) findViewById(R.id.pager_five);
	    
	    onePagerTitle.setOnClickListener(this);
	    twoPagerTitle.setOnClickListener(this);
	    threePagerTitle.setOnClickListener(this);
	    fourPagerTitle.setOnClickListener(this);
	    fivePagerTitle.setOnClickListener(this);
	   
	    titleList.add(onePagerTitle);
	    titleList.add(twoPagerTitle);
	    titleList.add(threePagerTitle);
	    titleList.add(fourPagerTitle);
	    titleList.add(fivePagerTitle);
	    
	    mViewPaper.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
	    mViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	private class MyOnPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			
			//更新当前页面
			currentItem = position;

			//标题
			titleList.get(position % fragmentList.size() ).setTextColor(getResources().getColor(R.color.theme_color));
			titleList.get(oldPosition % fragmentList.size()).setTextColor(Color.BLACK);;
			
			oldPosition = position;
		}
		
	}
	

	 public class MyViewPagerAdapter extends FragmentPagerAdapter {
         public MyViewPagerAdapter(FragmentManager fm) {
          super(fm);
     }

         
	@Override
     public Fragment getItem(int arg0) {
          return fragmentList.get(arg0);
     }
     @Override
     public int getCount() {
          return fragmentList.size();
     }
 
}
	@Override
	public void onClick(View v) {
		
		int position = 0;

		switch (v.getId()) {
		case R.id.pager_one:
			position = 0;
			break;
		case R.id.pager_two:
			position = 1;
			break;
		case R.id.pager_three:
			position = 2;
			break;
		case R.id.pager_four:
			position = 3;
			break;
		case R.id.pager_five:
			position = 4;
			break;
		default:
			break;
		}
		mViewPaper.setCurrentItem(position);
		
	}
	
	
}
