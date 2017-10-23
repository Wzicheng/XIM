package com.bester.chat.xim.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.platform.comapi.map.E;
import com.bester.chat.xim.R;
import com.bester.chat.xim.model.Model;
import com.bester.chat.xim.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

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
                break;
        }
    }
    //获取输入用户登录信息，并进行校验
    private void getUserInformation() {
        final String userName = mEtUsername.getText().toString();
        final String password = mEtPassword.getText().toString();
        //校验输入是否正确
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"输入的用户名或密码不能为空!",Toast.LENGTH_SHORT).show();
            return;
        }
        //基本验证通过，到环信服务器验证
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(userName, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //对模型层数据的处理
                        Model.getInstance().loginSuccess();
                        //保存用户信息到本地数据库
                        Model.getInstance().getUserAccountDAO().addAccount(new UserInfo(userName,null,null,null));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登陆成功
                                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        //跳转到主页面
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        SplashActivity.splashActivity.finish();
                        finish();
                    }

                    @Override
                    public void onError(int i, final String s) {
                        //提示登录失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登陆失败" + s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    //登陆中
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }
}
