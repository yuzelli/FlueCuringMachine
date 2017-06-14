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
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

public class ChangePassActivity extends BaseActivity {


    @BindView(R.id.img_black)
    ImageView imgBlack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_okpassword)
    EditText etOkpassword;
    @BindView(R.id.tv_change_pass)
    TextView tvChangePass;

    private Context context;
    private ChangePassHandler handler;
    private UserInfoBean userInfo;

    @Override
    protected int layoutInit() {
        return R.layout.activity_change_pass;
    }

    @Override
    protected void binEvent() {
        context = this;
        handler = new ChangePassHandler();
        userInfo = (UserInfoBean) SharePreferencesUtil.readObject(context, ConstantsUtils.USER_LOGIN_INFO);
        imgBlack.setVisibility(View.VISIBLE);
        imgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("修改密码");
        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passWord = etPassword.getText().toString().trim();
                String okPassword = etOkpassword.getText().toString().trim();
                if (passWord.equals("")) {
                    showToast("密码不能为空！");
                }
                if (okPassword.equals("")) {
                    showToast("确认密码不能为空");
                }
                if (!passWord.equals(okPassword)) {
                    showToast("两次密码输入不一致！");
                }


                OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.CHANG_USER_PASS_POST, UserInfoBean.getChange(userInfo.getUserName(), passWord,getToken()), new OkHttpClientManager.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {
                        showToast("请求数据失败！");
                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {

                        JSONObject object = new JSONObject(result);
                        int code = object.optInt("errorCode");
                        switch (code) {
                            case 0:
                                handler.sendEmptyMessage(ConstantsUtils.CHANGE_PASS);
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
        });
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,ChangePassActivity.class);
        context.startActivity(intent);
    }

    class  ChangePassHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ConstantsUtils.CHANGE_PASS:
                    userInfo.setPassWords(etPassword.getText().toString().trim());
                    SharePreferencesUtil.saveObject(context, ConstantsUtils.USER_LOGIN_INFO, userInfo);
                    userInfo = (UserInfoBean) SharePreferencesUtil.readObject(context, ConstantsUtils.USER_LOGIN_INFO);
                    if (userInfo != null) {
                        finish();
                    }else {
                        showToast("保存用户信息失败！请重新修改密码！");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void fillData() {

    }

}
