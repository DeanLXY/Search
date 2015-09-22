package com.example.search.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;

import com.example.search.R;

/**
 * Created by wxj on 2015-9-22.
 */
public abstract class ARefreshFragment extends ABaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.layoutSwiperefresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    @Override
    public void onRefresh() {
    }
}
