package com.bester.chat.xim.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bester.chat.xim.R;
import com.bester.chat.xim.controller.activity.SplashActivity;
import com.bester.chat.xim.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import static android.R.attr.starStyle;
import static android.R.attr.start;

/**
 * Created by Wzich on 2017/10/23.
 */

public class MineFragment extends BaseFragment {

    private Button mBtnMineOut;

    public MineFragment(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_mine, null);
        return view;
    }
}
