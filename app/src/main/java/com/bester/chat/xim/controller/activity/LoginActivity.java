package com.bester.chat.xim.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bester.chat.xim.R;

import static com.hyphenate.easeui.model.EaseDefaultEmojiconDatas.getData;

/**
 * Created by Wzich on 2017/10/16.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button mBtnLoginBack;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String userName = getIntent().getStringExtra("userName");
        mEtUsername.setText(userName);
    }

    private void initView() {
        mBtnLoginBack = (Button) findViewById(R.id.btn_login_back);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        //绑定监听
        mBtnLoginBack.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_back:
                finish();
                break;
            case R.id.btn_login:
                //获取输入用户登录信息,并进行处理
                getUserInformation();
                finish();
                break;
        }
    }
    //获取输入用户登录信息，并进行校验
    private void getUserInformation() {
        //登录验证成功，跳转到主页面
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
