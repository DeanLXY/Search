package com.example.search;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.search.ui.fragment.ASwiperefreshDelayFragment;

public class RecommendFragment extends ASwiperefreshDelayFragment {

     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    }

    @Override
    protected int layoutResourceID() {
        return R.layout.recommend_fragment;
    }


    @Override
    public void onStripTabRequestData() {

    }
}
