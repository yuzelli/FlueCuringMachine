package com.example.yuzelli.fluecuringmachine.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 51644 on 2017/6/5.
 */

public class UserInfoBean implements Serializable {

    private String userName;
    private String passWords;
    private String token;

    public UserInfoBean(String userName, String passWords,String token) {
        this.userName = userName;
        this.passWords = passWords;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWords() {
        return passWords;
    }

    public void setPassWords(String passWords) {
        this.passWords = passWords;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Map<String,String> getLogin(String userName, String password){
        Map<String,String> map = new HashMap<>();
        map.put("username",userName);
        map.put("password",password);
        return map;

    }
}
