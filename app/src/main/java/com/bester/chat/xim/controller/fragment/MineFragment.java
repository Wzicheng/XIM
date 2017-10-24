package com.bester.chat.xim.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bester.chat.xim.R;
import com.bester.chat.xim.controller.activity.LoginActivity;
import com.bester.chat.xim.controller.activity.MainActivity;
import com.bester.chat.xim.controller.activity.SplashActivity;
import com.bester.chat.xim.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Wzich on 2017/10/23.
 */

public class MineFragment extends Fragment {

    private Button mBtnMineOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtnMineOut = (Button) view.findViewById(R.id.btn_mine_out);

        //设置监听
        mBtnMineOut.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_mine_out:
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            //登录环信服务器退出
                            EMClient.getInstance().logout(false, new EMCallBack() {
                                @Override
                                public void onSuccess() {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //退出成功提示并返回登录页面
                                            Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                                            intent.putExtra("isFromMine",true);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                    });
                                }

                                @Override
                                public void onError(int i, final String s) {
                                    //提示退出失败
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity(),"登陆失败" + s,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onProgress(int i, String s) {

                                }
                            });
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

    }
}
