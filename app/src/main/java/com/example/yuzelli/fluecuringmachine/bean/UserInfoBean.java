package com.example.yuzelli.fluecuringmachine.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 51644 on 2017/6/5.
 */

public class UserInfoBean implements Serializable {

    public static Map<String,String> getLogin(String userName,String password){
        Map<String,String> map = new HashMap<>();
        map.put("username",userName);
        map.put("Password",password);
        return map;

    }
}
