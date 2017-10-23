package com.bester.chat.xim.model.dao;

/**
 * Created by Wzich on 2017/10/21.
 */

public class UserAccountTable {
    public static final String TABLE_NAME = "table_account";
    public static final String COL_NAME = "name";
    public static final String COL_HXID = "hxid";
    public static final String COL_NICK = "nick";
    public static final String COL_PHOTO = "photo";

    public static final String CREATE_TABLE = "create table "
            + TABLE_NAME + " ("
            + COL_HXID + " text primary key,"
            + COL_NAME + " text,"
            + COL_NICK + " text,"
            + COL_PHOTO + " text);";

}
