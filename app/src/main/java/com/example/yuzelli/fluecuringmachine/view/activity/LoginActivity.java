package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.widgets.RoundImageView;

import java.io.IOException;

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
    public void tvLogin(){
        String userName = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (userName.equals("")){
            showToast("请输入用户名！");
        }
        if (password.equals("")){
            showToast("请输入用户名！");
        }

        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL +ConstantsUtils.USERINFO_LOGIN, UserInfoBean.getLogin(userName, password), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                showToast("请求数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                 showToast("登陆成功！"+result);
            }
        });
    }
    @OnClick(R.id.tv_register)
    public void tvRegister(){
        RegisterActivity.actionStart(context);
    }
    private Context context;
    @Override
    protected int layoutInit() {
        return R.layout.activity_login;
    }

    @Override
    protected void binEvent() {
        context = this;
    }

    @Override
    protected void fillData() {

    }

}
