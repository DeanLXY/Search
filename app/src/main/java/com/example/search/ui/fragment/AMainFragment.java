package com.example.search.ui.fragment;

import android.support.v4.app.Fragment;

import com.example.search.AccountFragment;
import com.example.search.ApplationFragment;
import com.example.search.CollectionFragment;
import com.example.search.HistoryFragment;
import com.example.search.R;
import com.example.search.RecommendFragment;
import com.example.search.ui.activity.PortalActivity;

import java.util.ArrayList;

/**
 * Created by wxj on 2015-9-20.
 */
public class AMainFragment extends AStripTabsFragment{

    @Override
    protected Fragment newFragment(StripTabItem bean) {
        switch (Integer.parseInt(bean.getType())){
            case 1001:
                return new ApplationFragment();
            case 1002:
                return new RecommendFragment();
            case 1003:
                return new HistoryFragment();
            case 1004:
                return new CollectionFragment();
            case 1005:
                return new AccountFragment();
        }
        return null;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);

        ((PortalActivity) getActivity()).toggleToolbarShown(true);
    }

    @Override
    protected ArrayList generateTabs() {
        ArrayList<StripTabItem> beans = new ArrayList<StripTabItem>();
        beans.add(new StripTabItem("1001", getString(R.string.module_main_app)));
        beans.add(new StripTabItem("1002", getString(R.string.module_main_recommend)));
        beans.add(new StripTabItem("1003", getString(R.string.module_main_collection)));
        beans.add(new StripTabItem("1004", getString(R.string.module_main_history)));
        beans.add(new StripTabItem("1005", getString(R.string.module_main_account)));
        return beans;
    }

}
