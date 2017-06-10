package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class RegisterActivity extends BaseActivity {


    @OnClick(R.id.img_black)
    public void imgBlack() {
        finish();
    }

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_okpassword)
    EditText etOkpassword;

    @OnClick(R.id.tv_register)
    public void tvRegister() {
        String userName = etUsername.getText().toString().trim();
        String passWorid = etPassword.getText().toString().trim();
        String okPassword = etOkpassword.getText().toString().trim();

        if (userName.equals("")) {
            showToast("用户名不能为空！");
        }
        if (passWorid.equals("")) {
            showToast("密码不能为空！");
        }
        if (okPassword.equals("")) {
            showToast("确认密码不能为空");
        }
        if (!passWorid.equals(okPassword)) {
            showToast("两次密码输入不一致！");
        }

        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.REGISTER_POST, UserInfoBean.getLogin(userName, passWorid), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                showToast("请求数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {

                JSONObject object = new JSONObject(result);
                int code = object.optInt("errorCode");
                switch (code){
                    case 0:
//                        Message msg = new Message();
//                        msg.what = ConstantsUtils.LOGIN_GET_DATA;
//
//                        showToast("成功！");
                        break;
                    case 10010:

                     //   handler.sendMessage(msg);
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

    @Override
    protected int layoutInit() {
        return R.layout.activity_register;
    }

    @Override
    protected void binEvent() {

    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
