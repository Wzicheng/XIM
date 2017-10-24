package com.bester.chat.xim.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wzich on 2017/10/24.
 */
public class SwitchFragment extends Fragment {
    private BaseFragment baseFragment;
    public SwitchFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return baseFragment.rootView;
    }
}
