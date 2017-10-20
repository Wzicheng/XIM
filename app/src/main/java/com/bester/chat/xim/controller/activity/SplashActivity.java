package com.bester.chat.xim.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bester.chat.xim.R;
import com.bester.chat.xim.model.Model;
import com.hyphenate.chat.EMClient;

/**
 * Created by Wzich on 2017/10/16.
 */

public class SplashActivity extends Activity implements View.OnClickListener {
    /**
     * 欢迎启动画面2s后显示登陆注册窗口
     */
    private static final int SHOW_LOGINBAR = 0;
    /**
     * 已经登陆过账号，直接跳转到主界面
     */
    private static final int TO_MAINACTIVITY = 1;
    private RelativeLayout mRlLoginbar;
    private Button mBtnLogin;
    private Button mBtnRegister;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_LOGINBAR:
                    mRlLoginbar.setVisibility(View.VISIBLE);
                    break;
                case TO_MAINACTIVITY:
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initView();
        toLoginActivity();
    }

    private void toLoginActivity() {
        Model.getInstance().getGlobalThreadPool().execute(
            new Runnable() {
                @Override
                public void run() {
                    if (EMClient.getInstance().isLoggedInBefore()){ //登陆过，获取到当前用户的信息

                        handler.sendEmptyMessageDelayed(TO_MAINACTIVITY,2000);
                    } else { //还未登陆，显示登录注册
                        handler.sendEmptyMessageDelayed(SHOW_LOGINBAR,2000);
                    }
                }
            }
        );
    }

    private void initView() {
        mRlLoginbar = (RelativeLayout) findViewById(R.id.rl_loginbar);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        //绑定点击监听
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent loginIntent = new Intent(this,LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.btn_register:
                Intent registerIntent = new Intent(this,RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
