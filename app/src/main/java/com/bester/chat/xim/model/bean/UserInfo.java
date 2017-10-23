package com.bester.chat.xim.model.bean;

/**
 * Created by Wzich on 2017/10/21.
 */

public class UserInfo {
    private String userName;    //用户名
    private String hxId;    //环信ID
    private String nick;        //用户昵称
    private String photo;       //用户头像

    public UserInfo() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserInfo(String userName, String hxId, String nick, String photo) {
        this.userName = userName;
        this.hxId = hxId;
        this.nick = nick;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", hxId='" + hxId + '\'' +
                ", nick='" + nick + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
