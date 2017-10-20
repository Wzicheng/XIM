package com.bester.chat.xim;

import android.app.Application;

import com.bester.chat.xim.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by Wzich on 2017/10/11.
 */

public class XIMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EaseUI
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //设置需要验证后才接受群邀请
        options.setAutoAcceptGroupInvitation(false);
        EMClient.getInstance().init(this, options);

//        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);

        /**
         * 初始化模型层类
         */
        Model.getInstance().init(this);
    }
}
