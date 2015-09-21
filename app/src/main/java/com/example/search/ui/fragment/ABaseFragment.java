package com.example.search.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.search.R;
import com.example.search.ui.activity.BaseActivity;

/**
 * Created by wxj on 2015-9-20.
 * ｛@link layoutResourceID()}
 * {@link #layoutInit(LayoutInflater, Bundle)}
 */
public abstract class ABaseFragment extends Fragment {

    private ViewGroup rootView;
    private View emptyLayout;
    private View loadFailureLayout;
    private View loadingLayout;
    private boolean contentEmpty;
    private View contentLayout;

    protected abstract int layoutResourceID();

    /**
     * 如果需要这个方法可以让子类重写，初始化视图内容
     *
     * @param inflater
     * @param savedInstanceSate
     */
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof BaseActivity)
            ((BaseActivity) activity).addFragment(toString(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutResourceID() > 0) {
            rootView = (ViewGroup) inflater.inflate(layoutResourceID(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            _layoutInit(inflater, savedInstanceState);

            layoutInit(inflater, savedInstanceState);

            return rootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ViewGroup getRootView() {
        return rootView;
    }

    protected View findViewById(int id) {
        return rootView.findViewById(id);
    }

    void _layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        emptyLayout = rootView.findViewById(R.id.layoutEmpty);
        if (emptyLayout != null) {
            View reloadView = emptyLayout.findViewById(R.id.layoutReload);
            if (reloadView != null)
                setViewOnClick(reloadView);
        }
        loadFailureLayout = rootView.findViewById(R.id.layoutReload);
        if (loadFailureLayout != null) {
            View reloadView = loadFailureLayout.findViewById(R.id.layoutReload);
            if (reloadView != null)
                setViewOnClick(reloadView);
        }
        loadingLayout = rootView.findViewById(R.id.layoutLoading);
        contentLayout = rootView.findViewById(R.id.layoutContent);
        setViewVisiable(loadingLayout, View.GONE);
        setViewVisiable(loadFailureLayout, View.GONE);
        setViewVisiable(emptyLayout, View.GONE);
        if (isContentEmpty()) {
            if (savedInstanceSate != null) {
                requestData();
            } else {
                setViewVisiable(emptyLayout, View.VISIBLE);
                setViewVisiable(contentLayout, View.GONE);
            }
        } else {
            setViewVisiable(contentLayout, View.VISIBLE);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null)
            requestData();
    }
    /**
     * 刷新数据实用
     * 用于加载当前页面，预加载页面延迟加载
     */
    public void requestData() {
    }

    public void setContentEmpty(boolean empty) {
        this.contentEmpty = empty;
    }

    public boolean isContentEmpty() {
        return contentEmpty;
    }

    /**
     * 返回数据是否空
     *
     * @param result
     * @return
     */
    protected boolean resultIsEmpty(String result) {
        return result == null ? true : false;
    }

    protected void setViewOnClick(View v) {
        if (v == null)
            return;

        v.setOnClickListener(innerOnClickListener);
    }

    View.OnClickListener innerOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            onViewClicked(v);
        }

    };

    public void onViewClicked(View view) {
        if (view.getId() == R.id.layoutReload)
            requestData();
        else if (view.getId() == R.id.layoutRefresh)
            requestData();
    }

    private void setViewVisiable(View v, int visibility) {
        if (v != null)
            v.setVisibility(visibility);
    }
}
