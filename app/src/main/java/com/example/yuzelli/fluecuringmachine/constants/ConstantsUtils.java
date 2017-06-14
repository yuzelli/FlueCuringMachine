package com.example.yuzelli.fluecuringmachine.constants;

/**
 * Created by 51644 on 2017/5/16.
 */

public class ConstantsUtils {

    //用户登录成功
    public static final int LOGIN_GET_DATA = 0x00001001;
    public static final int EQUIPMENT_LIST_GET_DATA = 0x00001002;
    public static final int EQUIPMENT_DETAIL_GET_DATA = 0x00001003;
    public static final int REGISTER_SUCCESS = 0x00001004;
    public static final int CHANGE_PASS = 0x00001005;
    public static final int SET_SYSTEM = 0x00001006;
    public static final int SET_SYSTEM_SSP = 0x00001007;

    public static final String ADDRESS_URL = "http://60.205.214.66:8080/emanager/rest/";
    public static final String USERINFO_LOGIN = "devicemanage/login/";
    public static final String EQUIPMENT_LIST = "devicemanage/device/list/";
    public static final String EQUIPMENT_DETAIL = "devicemanage/device/";
    public static final String REGISTER_POST = "devicemanage/user/add";
    public static final String CHANG_USER_PASS_POST = "devicemanage/user/change";
    public static final String SET_TEMP_POST = "devicemanage/task/";
    public static final String SET_SYSTEM_POST = "devicemanage/device/";



    //保持登录用户信息
    public static final String USER_LOGIN_INFO = "UserInfo";
    public static final String CUSTOMER_SERVICE_TEL_PHONE = "123456789";
}
