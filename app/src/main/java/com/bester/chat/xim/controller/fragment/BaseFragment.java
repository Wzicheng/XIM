package com.bester.chat.xim.controller.fragment;

import android.content.Context;
import android.view.View;

/**
 * Created by Wzich on 2017/10/23.
 */

public abstract class BaseFragment{
    public final Context context;
    public View rootView;
    public boolean isInitData;

    public BaseFragment(Context context) {
        this.context = context;
        this.rootView = initView();
    }

    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData(){

    }
}
