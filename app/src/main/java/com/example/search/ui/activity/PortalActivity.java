package com.example.search.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.search.R;
import com.example.search.ui.fragment.AMainFragment;
import com.example.search.ui.fragment.AStripTabsFragment;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

/**
 * Created by wxj on 2015-9-20.
 */
public class PortalActivity extends BaseActivity {
    private Toolbar mToolbar;
    private View mStripView;
    public static final String FRAGMENT_TAG = "MainFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
        mToolbar = getToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AMainFragment mainFragment = new AMainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mainFragment, FRAGMENT_TAG).commit();
    }

    private AnimatorSet animatorSet;
    public void toggleToolbarShown(boolean shown) {
        if (mStripView == null) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            if (fragment != null && fragment instanceof AStripTabsFragment) {
                mStripView = ((AStripTabsFragment) fragment).getSlidingTabLayout();
            }
//            else if (fragment != null && fragment instanceof ATabLayoutFragment) {
//                mStripView = ((ATabLayoutFragment) fragment).getTabLayout();
//            }
        }

        if (mToolbar == null)
            return;

        if (animatorSet != null && animatorSet.isRunning())
            return;

        if (isToolbarShown() && shown)
            return;
        else if (!isToolbarShown() && !shown)
            return;



        PropertyValuesHolder toolBarHolder = null;
        if (shown) {
            toolBarHolder = PropertyValuesHolder.ofFloat("translationY", -1 * mToolbar.getHeight(), 0);
        }
        else {
            toolBarHolder = PropertyValuesHolder.ofFloat("translationY", 0, -1 * mToolbar.getHeight());
        }
        ObjectAnimator toolbarObjectAnim = ObjectAnimator.ofPropertyValuesHolder(mToolbar, toolBarHolder);
        toolbarObjectAnim.setDuration(150);

        ObjectAnimator stripObjectAnim = null;
        if (mStripView != null) {
            PropertyValuesHolder stripHolder = null;
            if (shown) {
                stripHolder = PropertyValuesHolder.ofFloat("translationY", -1 * mStripView.getHeight(), 0);
            }
            else {
                stripHolder = PropertyValuesHolder.ofFloat("translationY", 0, -1 * mStripView.getHeight());
            }
            stripObjectAnim = ObjectAnimator.ofPropertyValuesHolder(mStripView, stripHolder);
            stripObjectAnim.setDuration(150);
        }

        AnimatorSet animSet = new AnimatorSet();
        animatorSet = animSet;
        if (shown) {
            if (stripObjectAnim != null) {
//                animSet.playSequentially(toolbarObjectAnim, stripObjectAnim);
                animSet.play(toolbarObjectAnim);
                stripObjectAnim.setStartDelay(100);
                animSet.play(stripObjectAnim);
            }
            else {
                animSet.play(toolbarObjectAnim);
            }
        }
        else {
            if (stripObjectAnim != null) {
//                animSet.playSequentially(stripObjectAnim, toolbarObjectAnim);
                animSet.play(stripObjectAnim);
                toolbarObjectAnim.setStartDelay(100);
                animSet.play(toolbarObjectAnim);
            }
            else {
                animSet.play(toolbarObjectAnim);
            }
        }
        animSet.start();
    }
    private boolean isToolbarShown() {
        return mToolbar != null && mToolbar.getTranslationY() >= 0;
    }

}
