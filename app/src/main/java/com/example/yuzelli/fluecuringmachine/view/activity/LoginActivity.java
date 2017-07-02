package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.BaiduLoading;
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
    private boolean isAgainFlag = false;
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
        BaiduLoading.onBeiginDialog(context);
        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.USERINFO_LOGIN, UserInfoBean.getLogin(userName, password), new OkHttpClientManager.DataCallBack() {
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
                    case 10010:
                        Message msg = new Message();
                        msg.what = ConstantsUtils.LOGIN_GET_DATA;
                        JSONObject json = object.optJSONObject("data");
                        String token = json.optString("token");
                        UserInfoBean userInfo = new UserInfoBean(userName, password, token);
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
        isAgainFlag = getIntent().getBooleanExtra("isAgainFlag",false);
        if(isAgainFlag){
            UserInfoBean userInfoBean = (UserInfoBean) SharePreferencesUtil.readObject(context,ConstantsUtils.USER_LOGIN_INFO);
            etUsername.setText(userInfoBean.getUserName());
            etPassword.setText(userInfoBean.getPassWords());
        }
    }


    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context, boolean isAgainFlag) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("isAgainFlag", isAgainFlag);
        context.startActivity(intent);
    }

    class LoginHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.LOGIN_GET_DATA:
                    UserInfoBean u = (UserInfoBean) msg.obj;
                    SharePreferencesUtil.saveObject(context, ConstantsUtils.USER_LOGIN_INFO, u);
                    u = (UserInfoBean) SharePreferencesUtil.readObject(context, ConstantsUtils.USER_LOGIN_INFO);
                    if (u != null) {
                        MainActivity.actionStart(context);
                        finish();
                    } else {
                        showToast("保存用户信息失败！请重新登陆！");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isAgainFlag) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    // finish();
                    System.exit(0);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
