package com.bester.chat.xim.model;

import android.content.Context;

import com.bester.chat.xim.model.dao.UserAccountDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据模型层全局类
 * Created by Wzich on 2017/10/17.
 */

public class Model {
    private Context mComtext;
    private ExecutorService executors = Executors.newCachedThreadPool();
    //创建Model对象
    private static Model model = new Model();
    private UserAccountDAO userAccountDAO;

    /**
     * 私有化构造函数
     */
    private Model(){

    }

    /**
     * 获取单例对象
     * @return
     */
    public static Model getInstance(){
        return model;
    }

    /**
     * 初始化方法
     * @param context
     */
    public void init(Context context){
        mComtext = context;
        //创建用户账号数据库的操作类
        userAccountDAO = new UserAccountDAO(mComtext);
    }

    /**
     * 获取全局线程池
     * @return
     */
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }

    /**
     * 用户登陆成功后进行数据处理
     */
    public void loginSuccess() {

    }

    public UserAccountDAO getUserAccountDAO(){
         return userAccountDAO;
    }
}
