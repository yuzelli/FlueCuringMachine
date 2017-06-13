package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;
import com.example.yuzelli.fluecuringmachine.widgets.RoundImageView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.img_icon)
    RoundImageView imgIcon;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    @OnClick(R.id.tv_login)
    public void tvLogin() {
        final String userName = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (userName.equals("")) {
            showToast("请输入用户名！");
        }
        if (password.equals("")) {
            showToast("请输入用户名！");
        }

        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.USERINFO_LOGIN, UserInfoBean.getLogin(userName, password), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                showToast("请求数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {

                JSONObject object = new JSONObject(result);
                int code = object.optInt("errorCode");
                switch (code) {
                    case 10010:
                        Message msg = new Message();
                        msg.what = ConstantsUtils.LOGIN_GET_DATA;
                        JSONObject json = object.optJSONObject("data");
                        String token = json.optString("token");
                        UserInfoBean userInfo = new UserInfoBean(userName, password,token);
                        msg.obj = userInfo;
                        showToast("登陆成功！");
                        handler.sendMessage(msg);
                        break;
                    case 10011:
                        showToast("登陆失败！");
                        break;
                    case 10001:
                        showToast("参数错误！");
                        break;
                    case 10002:
                        showToast("没有权限！");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.tv_register)
    public void tvRegister() {
        RegisterActivity.actionStart(context);
    }

    private Context context;
    private LoginHandler handler;

    @Override
    protected int layoutInit() {
        return R.layout.activity_login;
    }

    @Override
    protected void binEvent() {
        context = this;
        handler = new LoginHandler();
    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    class LoginHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.LOGIN_GET_DATA:
                    UserInfoBean userInfo = (UserInfoBean) msg.obj;
                    SharePreferencesUtil.saveObject(context, ConstantsUtils.USER_LOGIN_INFO, userInfo);
                    userInfo = (UserInfoBean) SharePreferencesUtil.readObject(context, ConstantsUtils.USER_LOGIN_INFO);
                    if (userInfo != null) {
                        finish();
                    }else {
                        showToast("保存用户信息失败！请重新登陆！");
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
