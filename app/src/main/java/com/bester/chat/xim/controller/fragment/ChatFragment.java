package com.bester.chat.xim.controller.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bester.chat.xim.R;

/**
 * Created by Wzich on 2017/10/23.
 */

public class ChatFragment extends BaseFragment{
    public ChatFragment(Context context) {
        super(context);
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_chat,null);
        return view;
    }
}
