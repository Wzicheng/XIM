package com.bester.chat.xim.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bester.chat.xim.model.bean.UserInfo;
import com.bester.chat.xim.model.db.UserAccountDB;

/**
 * 用户账号数据库操作类
 * Created by Wzich on 2017/10/21.
 */

public class UserAccountDAO {
    private final UserAccountDB mHelper;

    public UserAccountDAO(Context context) {
        mHelper = new UserAccountDB(context);
    }

    //添加用户到数据库
    public void addAccount(UserInfo userInfo){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getWritableDatabase();

        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID,userInfo.getHxId());
        values.put(UserAccountTable.COL_NAME,userInfo.getUserName());
        values.put(UserAccountTable.COL_NICK,userInfo.getNick());
        values.put(UserAccountTable.COL_PHOTO,userInfo.getPhoto());

        db.replace(UserAccountTable.TABLE_NAME,null,values);
    }

    //根据环信ID获取用户信息
    public UserInfo getAccountByHxId(String hxId){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from " + UserAccountTable.TABLE_NAME + " where " + UserAccountTable.COL_HXID
                + "=?";

        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        UserInfo userInfo = null;
        if (cursor.moveToNext()){
            userInfo = new UserInfo();

            //封装对象
            userInfo.setHxId(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfo.setUserName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));

        }
        //关闭资源
        cursor.close();
        //将数据返回
        return userInfo;
    }
}
