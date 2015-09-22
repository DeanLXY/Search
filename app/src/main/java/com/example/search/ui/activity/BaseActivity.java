package com.example.search.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.example.search.R;
import com.example.search.ui.fragment.ABaseFragment;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by wxj on 2015-9-20.
 * {@link #isDestory 2015年9月20日 16:46:58}
 */
public class BaseActivity extends AppCompatActivity {

    private View rootView;
    protected Toolbar mToolbar;
    private HashMap<String, WeakReference<ABaseFragment>> fragmentRefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentRefs = new HashMap<String, WeakReference<ABaseFragment>>();
        // 如果设备有实体MENU按键，overflow菜单不会再显示
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
        if (viewConfiguration.hasPermanentMenuKey()) {
            try {
                Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(viewConfiguration, false);
            } catch (Exception e) {
            }
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        rootView = view;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView = view;

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null)
            setSupportActionBar(mToolbar);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    View getRootView() {
        return rootView;
    }

    public void addFragment(String tag, ABaseFragment fragment) {
        fragmentRefs.put(tag, new WeakReference<ABaseFragment>(fragment));
    }

    public void removeFragment(String tag) {
        fragmentRefs.remove(tag);
    }

    //2015年9月20日 16:46:37
    private boolean isDestory;

    public void setDestory(boolean destory) {
        this.isDestory = destory;
    }

    public boolean isDestory() {
        return isDestory;
    }
}
