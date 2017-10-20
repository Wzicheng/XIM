package com.bester.chat.xim.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bester.chat.xim.R;
import com.bester.chat.xim.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Wzich on 2017/10/16.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private Button mBtnRegisterBack;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtPassword2;
    private Button mBtnConfirmRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mBtnRegisterBack = (Button) findViewById(R.id.btn_register_back);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtPassword2 = (EditText) findViewById(R.id.et_password2);
        mBtnConfirmRegister = (Button) findViewById(R.id.btn_confirm_register);

        //绑定监听
        mBtnRegisterBack.setOnClickListener(this);
        mBtnConfirmRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_back:
                finish();
                break;
            case R.id.btn_confirm_register:
                //获取用户注册信息
                getRegisterImformation();
                break;
        }
    }
    //获取用户注册信息，并进行校验
    private void getRegisterImformation() {
        //获取注册信息
        final String userName = mEtUsername.getText().toString();
        final String password = mEtPassword.getText().toString();
        final String password2 = mEtPassword2.getText().toString();
        //校验输入是否正确
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"输入的用户名或密码不能为空!",Toast.LENGTH_SHORT).show();
            return;
        } else if(!password.equals(password2)){
            Toast.makeText(this,"两次输入密码不一致，请重新输入!",Toast.LENGTH_SHORT).show();
            return;
        }
        //去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(userName,password);
                    Log.e("TAg","register succeed");
                    //注册成功，显示提示信息
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(RegisterActivity.this,"注册成功，将在2秒内跳转至登陆页面!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    DelayToLogin(userName);
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    //注册失败，显示提示信息
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this,"注册失败"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * 注册成功后延迟2s跳转到注册页面，并将注册的用户名传到登录界面
     * @param userName
     */
    private void DelayToLogin(final String userName) {
        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            @Override
            public void run() {
                // TODO Auto-generated method stub
                intent.putExtra("userName",userName);
                startActivity(intent);
                finish();
            }
        };
        time.schedule(timerTask, 2000);
    }
}
