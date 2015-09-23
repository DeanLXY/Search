package com.example.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.search.adapter.MenuDetailAdapter;
import com.example.search.adapter.MenuListTitleAdapter;
import com.example.search.bean.Menu;
import com.example.search.ui.activity.BaseActivity;
import com.example.search.utils.SystemBarUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter;
import dev.dworks.libs.astickyheader.SimpleSectionedListAdapter.Section;

public class MenuListActivity extends BaseActivity {


    private ListView mMenuList;
    private ListView mMenuDetail;


    /**
     * 左边listview的要使用的数组
     */
    //String[] arr = new String[] { "主食", "小吃", "饮料", "套餐D", "套餐E", "套餐F","主食", "小吃", "饮料", "套餐D", "套餐E", "套餐F" };


//	String[] arr2 = new String[] { "主食", "麦辣鸡汉堡", "双层牛排", "经典汉堡" };
//	String[] arr3 = new String[] { "小吃", "上校鸡块", "薯条", "劲暴鸡米花"};
//	String[] arr4 = new String[] { "饮料", "雪碧", "可乐", "橙汁"};
//	String[] arr5 = new String[] { "套餐D", "food", "food", "food", "food", "food", "food",
//			"food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food" };
//	String[] arr6 = new String[] { "套餐E", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food" };
//	String[] arr7 = new String[] { "套餐F", "food", "food", "food" , "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food", "food"};
//	
//	String[][] arr8 = new String[][] { arr2, arr3, arr4, arr5, arr6, arr7 };

    /**
     * 用来存放 food数组
     */
    List<String> list;

    /**
     * 用来记录每一个 1 2 3 4 5 6 在右边listview的位置；
     */
    List<Integer> nums = new ArrayList<Integer>();
    private MenuDetailAdapter menuDetailAdapter;
    private ArrayList<Section> sections = new ArrayList<Section>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setTitle("麦当劳");

        if (Build.VERSION.SDK_INT >= 19) {
            mToolbar.setPadding(mToolbar.getPaddingLeft(),
                    mToolbar.getPaddingTop() + SystemBarUtils.getStatusBarHeight(this),
                    mToolbar.getPaddingRight(),
                    mToolbar.getPaddingBottom());
        }
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("menu");
        Menu menu = (Menu) bundle.get("menu");
        ArrayList<String> menuList = menu.getMenuList();
        String[] arr = (String[]) menuList.toArray(new String[menuList.size()]);

        String[][] arr8 = new String[arr.length][];
        Map<String, List<String>> menuDetails = menu.getMenuDetails();
        for (int i = 0; i < arr.length; i++) {
            List<String> menuDetail = menuDetails.get(arr[i]);
            String[] array = (String[]) menuDetail.toArray(new String[menuDetail.size()]);
            arr8[i] = array;
            int secPos = 0;
            for (int j = i; j > 0; j--) {
                secPos += arr8[j - 1].length;
            }
            sections.add(new Section(secPos, arr[i]));
            nums.add(secPos);
        }


        mMenuList = (ListView) findViewById(R.id.menu_list);
//        mMenuList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mMenuDetail = (ListView) findViewById(R.id.menu_detail);

        MenuListTitleAdapter menuAdapter = new MenuListTitleAdapter(this, arr);
        mMenuList.setAdapter(menuAdapter);
        mMenuList.setItemChecked(0, true);
        list = new ArrayList<String>();

        for (int j = 0; j < arr8.length; j++) {
            for (int j2 = 0; j2 < arr8[j].length; j2++) {
                list.add(arr8[j][j2]);
            }
        }

//        for (int i = 0; i < arr8.length; i++) {
//            if (i == 0) {
//                nums.add(0);
//            } else if (i > 0 && i < arr8.length) {
//                int num = 0;
//                for (int j = 0; j < i; j++) {
//                    num = num + arr8[j].length;
//
//                }
//                nums.add(num);
//            }
//        }
        menuDetailAdapter = new MenuDetailAdapter(this, list);
        Log.i("ggj", "nums.size()是否等于arr8.length" + (nums.size() == arr8.length));
        SimpleSectionedListAdapter simpleSectionedGridAdapter = new SimpleSectionedListAdapter(this, menuDetailAdapter,
                R.layout.list_item_header, R.id.header);
        simpleSectionedGridAdapter.setSections(sections.toArray(new Section[0]));
        mMenuDetail.setAdapter(simpleSectionedGridAdapter);

        mMenuDetail.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (nums.contains(firstVisibleItem) && mMenuList.getChildCount() > 0) {
                    for (int i = 0; i < mMenuList.getChildCount(); i++) {
                        if (i == nums.indexOf(firstVisibleItem)) {
                            mMenuList.getChildAt(i).setBackgroundColor(
                                    getResources().getColor(R.color.theme_color));
                            mMenuDetail.getChildAt(0).setBackgroundColor(
                                    getResources().getColor(R.color.title_color));
                        } else {
                            mMenuList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                            mMenuDetail.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                }
            }
        });

        mMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                mMenuDetail.setSelection(nums.get(position)+position);
                mMenuDetail.smoothScrollToPosition(sections.get(position).getFirstPosition() + position);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(0, 0, 0, "收藏");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {

        }
        return super.onOptionsItemSelected(item);
    }
}
