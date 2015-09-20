package com.example.search.ui.fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.example.search.R;
import com.example.search.adapter.FragmentPagerAdapter;
import com.example.search.ui.activity.BaseActivity;
import com.example.search.ui.widget.SlidingTabLayout;
import com.example.search.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by wxj on 2015-9-20.
 * {@link StripTabItem}
 * {@link IStripTabInitData}
 * {@link #generateTabs}
 */
public abstract class AStripTabsFragment<T extends AStripTabsFragment.StripTabItem> extends ABaseFragment implements ViewPager.OnPageChangeListener {

    private HashMap<String, Fragment> fragments;
    private MyViewPagerAdapter mViewPagerAdapter;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabs;


    @Override
    protected int layoutResourceID() {
        return R.layout.fragment_striptabs;
    }

    private ArrayList<T> mItems;
    private int mCurrentPosition = 0;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mCurrentPosition = viewPager.getCurrentItem();
        outState.putSerializable("items", mItems);
        outState.putInt("current", mCurrentPosition);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = savedInstanceState == null ? generateTabs()
                : (ArrayList<T>) savedInstanceState.getSerializable("items");

        mCurrentPosition = savedInstanceState == null ? 0
                : savedInstanceState.getInt("current");
    }

    protected abstract ArrayList<T> generateTabs();

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);

        setHasOptionsMenu(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabs = (SlidingTabLayout) findViewById(R.id.slidingTabs);
        setTabs(savedInstanceSate);
    }

    @Override
    public void onDestroy() {
        try {
            destoryFragments();
            viewPager.setAdapter(null);
            mViewPagerAdapter = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    protected void destoryFragments() {
        if (getActivity() != null) {
            if (getActivity() instanceof BaseActivity) {
                BaseActivity mainActivity = (BaseActivity) getActivity();
                if (mainActivity.isDestory())
                    return;
            }

            try {
                //TODO {#getFragmentManager || getChildFragmentManager}
                FragmentTransaction trs = getFragmentManager().beginTransaction();
                Set<String> keySet = fragments.keySet();
                for (String key : keySet) {
                    if (fragments.get(key) != null) {
                        trs.remove(fragments.get(key));
                        LogUtils.e("%s", "remove fragment , key = " + key);
                    }
                }
                trs.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTabs(Bundle savedInstanceSate) {
        if (getActivity() == null)
            return;
        fragments = new HashMap<String, Fragment>();

        if (mItems == null)
            return;
        for (int i = 0; i < mItems.size(); i++) {
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(makeFragmentName(i));
            if (fragment != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(fragment).commit();
            }
        }


        mViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(mViewPagerAdapter);
        if (mCurrentPosition >= mViewPagerAdapter.getCount())
            mCurrentPosition = 0;
        viewPager.setCurrentItem(mCurrentPosition);
        slidingTabs.setCustomTabView(R.layout.comm_lay_tab_indicator, android.R.id.text1);
        Resources res = getResources();
        slidingTabs.setSelectedIndicatorColors(res.getColor(R.color.comm_tab_selected_strip));
        slidingTabs.setDistributeEvenly(isDistributeEvenly());
        slidingTabs.setViewPager(viewPager);
        slidingTabs.setOnPageChangeListener(this);
        slidingTabs.setCurrent(mCurrentPosition);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;

        // 查看是否需要拉取数据
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof IStripTabInitData) {
            ((IStripTabInitData) fragment).onStripTabRequestData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    protected String makeFragmentName(int position) {
        return mItems.get(position).getTitle();
    }

    protected boolean isDistributeEvenly() {
        return mItems.size() <= 5;
    }

    public Fragment getCurrentFragment() {
        if (mViewPagerAdapter == null || mViewPagerAdapter.getCount() < mCurrentPosition)
            return null;

        return fragments.get(makeFragmentName(mCurrentPosition));
    }

    public SlidingTabLayout getSlidingTabLayout() {
        return slidingTabs;
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragments.get(makeFragmentName(position));
            if (fragment == null) {
                fragment = newFragment(mItems.get(position));
                fragments.put(makeFragmentName(position), fragment);
            }

            return fragment;
        }

        @Override
        protected String makeFragmentName(int position) {
            return AStripTabsFragment.this.makeFragmentName(position);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mItems.get(position).getTitle();
        }
    }


    abstract protected Fragment newFragment(T bean);

    public static class StripTabItem implements Serializable {

        private static final long serialVersionUID = 3680682035685685311L;

        private String type;

        private String title;

        private Serializable tag;

        public StripTabItem() {
        }

        public StripTabItem(String type, String title) {
            this.type = type;
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Serializable getTag() {
            return tag;
        }

        public void setTag(Serializable tag) {
            this.tag = tag;
        }
    }

    // 这个接口用于多页面时，只有当前的页面才加载数据，其他不显示的页面暂缓加载
    // 当每次onPagerSelected的时候，再调用这个接口初始化数据
    public interface IStripTabInitData {
        void onStripTabRequestData();
    }
}


