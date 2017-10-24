package com.bester.chat.xim.controller.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bester.chat.xim.R;
import com.bester.chat.xim.controller.fragment.ChatFragment;
import com.bester.chat.xim.controller.fragment.ContactFragment;
import com.bester.chat.xim.controller.fragment.FindFragment;
import com.bester.chat.xim.controller.fragment.MineFragment;



public class MainActivity extends FragmentActivity {
    private Button mBtnTopBarAdd;
    private FrameLayout mFlMain;
    private RadioGroup mRgBottomBar;
    private RadioButton mBtnBottomBarChat;
    private RadioButton mBtnBottomBarContact;
    private RadioButton mBtnBottomBarFind;
    private RadioButton mBtnBottomBarMine;

    private MainActivity mainTivity = null;

    private ChatFragment chatFragment;
    private ContactFragment contactFragment;
    private FindFragment findFragment;
    private MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        initListener();
    }

    private void initListener() {
        mRgBottomBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment = null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_bottom_bar_chat:
                        fragment = chatFragment;
                        break;
                    case R.id.btn_bottom_bar_contact:
                        fragment = contactFragment;
                        break;
                    case R.id.btn_bottom_bar_find:
                        fragment = findFragment;
                        break;
                    case R.id.btn_bottom_bar_mine:
                        fragment = mineFragment;
                        break;
                }
                if (fragment == null){
                    fragment = chatFragment;
                }
                switchFragment(fragment);
            }
        });

        mRgBottomBar.check(R.id.btn_bottom_bar_chat);
    }


    private void initDate() {
        chatFragment = new ChatFragment();
        contactFragment = new ContactFragment();
        findFragment = new FindFragment();
        mineFragment = new MineFragment();
    }

    private void initView() {
        mBtnTopBarAdd = (Button) findViewById(R.id.btn_Top_bar_add);
        mFlMain = (FrameLayout) findViewById(R.id.fl_main);
        mRgBottomBar = (RadioGroup) findViewById(R.id.rg_bottom_bar);
        mBtnBottomBarChat = (RadioButton) findViewById(R.id.btn_bottom_bar_chat);
        mBtnBottomBarContact = (RadioButton) findViewById(R.id.btn_bottom_bar_contact);
        mBtnBottomBarFind = (RadioButton) findViewById(R.id.btn_bottom_bar_find);
        mBtnBottomBarMine = (RadioButton) findViewById(R.id.btn_bottom_bar_mine);

        //用于在其他页面后finish()此页面
        mainTivity = this;

    }

    /**
     * 设置fragment中显示的页面
     */
    private void switchFragment(Fragment fragment) {
        //创建管理器
        FragmentManager maneger = getSupportFragmentManager();
        maneger.beginTransaction().replace(R.id.fl_main, fragment, null).commit();
    }
}


