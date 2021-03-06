package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.BaiduLoading;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class RegisterActivity extends BaseActivity {


    @OnClick(R.id.rl_black)
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
            return;
        }
        if (passWorid.equals("")) {
            showToast("密码不能为空！");
            return;
        }
        if (okPassword.equals("")) {
            showToast("确认密码不能为空");
            return;
        }
        if (!passWorid.equals(okPassword)) {
            showToast("两次密码输入不一致！");
            return;
        }
        BaiduLoading.onBeiginDialog(context);
        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.REGISTER_POST, UserInfoBean.getChange(userName, passWorid, getToken()), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                BaiduLoading.onStopDialog();
                showToast("请求数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                BaiduLoading.onStopDialog();
                JSONObject object = new JSONObject(result);
                int code = object.optInt("errorCode");
                switch (code) {
                    case 0:
                        finish();
                        showToast("注册成功！");
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

    private Context context;

    @Override
    protected int layoutInit() {
        return R.layout.activity_register;
    }

    @Override
    protected void binEvent() {
        context = this;
        findViewById(R.id.rl_black).setVisibility(View.VISIBLE);
    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    class RegisterHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.REGISTER_SUCCESS:

                    break;
                default:
                    break;

            }
        }
    }
}
