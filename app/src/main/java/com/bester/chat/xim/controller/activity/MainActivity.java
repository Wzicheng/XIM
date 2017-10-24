package com.bester.chat.xim.controller.activity;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bester.chat.xim.R;
import com.bester.chat.xim.controller.fragment.BaseFragment;
import com.bester.chat.xim.controller.fragment.ChatFragment;
import com.bester.chat.xim.controller.fragment.ContactFragment;
import com.bester.chat.xim.controller.fragment.FindFragment;
import com.bester.chat.xim.controller.fragment.MineFragment;
import com.bester.chat.xim.controller.fragment.SwitchFragment;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

public class MainActivity extends FragmentActivity {
    private Button mBtnTopBarAdd;
    private FrameLayout mFlMain;
    private RadioGroup mRgBottomBar;
    private RadioButton mBtnBottomBarChat;
    private RadioButton mBtnBottomBarContact;
    private RadioButton mBtnBottomBarFind;
    private RadioButton mBtnBottomBarMine;
    private List<BaseFragment> baseFragments;
    /**
     * 页面位置
     */
    private int position = 0;
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

    }


    private void initDate() {
        baseFragments = new ArrayList<>();
        //添加会话，联系人，发现，我，页面
        baseFragments.add(new ChatFragment(this));
        baseFragments.add(new ContactFragment(this));
        baseFragments.add(new FindFragment(this));
        baseFragments.add(new MineFragment(this));

        mRgBottomBar.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        mRgBottomBar.check(R.id.btn_bottom_bar_chat);
    }

    private void initView() {
        mBtnTopBarAdd = (Button) findViewById(R.id.btn_Top_bar_add);
        mFlMain = (FrameLayout) findViewById(R.id.fl_main);
        mRgBottomBar = (RadioGroup) findViewById(R.id.rg_bottom_bar);
        mBtnBottomBarChat = (RadioButton) findViewById(R.id.btn_bottom_bar_chat);
        mBtnBottomBarContact = (RadioButton) findViewById(R.id.btn_bottom_bar_contact);
        mBtnBottomBarFind = (RadioButton) findViewById(R.id.btn_bottom_bar_find);
        mBtnBottomBarMine = (RadioButton) findViewById(R.id.btn_bottom_bar_mine);

    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                default:
                    position = 0;
                    break;
                case R.id.btn_bottom_bar_contact:
                    position = 1;
                    break;
                case R.id.btn_bottom_bar_find:
                    position = 2;
                    break;
                case R.id.btn_bottom_bar_mine:
                    position = 3;
                    break;
            }
            switchFragment();
        }
    }

    /**
     * 设置fragment中显示的页面
     */
    private void switchFragment() {
        //创建管理器
        FragmentManager maneger = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = maneger.beginTransaction();
        //替换
        transaction.replace(R.id.fl_main, new SwitchFragment(getBaseFragment()));
        //提交事务
        transaction.commit();
    }

    /**
     * 根据position 获得相应页面
     * @return
     */
    private BaseFragment getBaseFragment() {
        BaseFragment baseFragment = baseFragments.get(position);
        if(baseFragment != null&&!baseFragment.isInitData){
            baseFragment.initData();//联网请求或者绑定数据
            baseFragment.isInitData = true;
        }
        return baseFragment;
    }
}


