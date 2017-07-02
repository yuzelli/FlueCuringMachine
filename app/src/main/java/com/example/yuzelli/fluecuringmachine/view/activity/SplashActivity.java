package com.example.yuzelli.fluecuringmachine.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.BaiduLoading;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;


import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.Request;


/**
 * 闪屏页，判断用户是否第一次登录
 * @author 李秉龙
 */
public class SplashActivity extends BaseActivity {
    private Context context;
    private boolean nextLogin = false;
    private ImageView iv_spl_background;
    private static final int ANIMATION_DURATION = 3000;
    private static final float SCALE_END = 1.13F;
    private static final int[] SPLASH_ARRAY = {
            R.drawable.splash3,
            R.drawable.splash7,
            R.drawable.splash10,
            R.drawable.splash16,
    };

    @Override
    protected int layoutInit() {
        return R.layout.activity_splash;
    }

    @Override
    protected void binEvent() {
        iv_spl_background = (ImageView) this.findViewById(R.id.iv_spl_background);
        Random r = new Random(SystemClock.elapsedRealtime());
        iv_spl_background.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        context = SplashActivity.this;
        animateImage();

    }

    @Override
    protected void fillData() {

    }


    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv_spl_background, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_spl_background, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // handler.sendEmptyMessageDelayed(ConstantUtil.START_ACTIVITY, 3000);
                handler.sendEmptyMessage(ConstantsUtils.SPLASH_START_ACTIVITY);
            }
        });
    }
    private void doLogin() {
        final UserInfoBean userInfo = (UserInfoBean) SharePreferencesUtil.readObject(this, ConstantsUtils.USER_LOGIN_INFO);
        if (userInfo==null){
            return;
        }
        BaiduLoading.onBeiginDialog(context);
        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.USERINFO_LOGIN, UserInfoBean.getLogin(userInfo.getUserName(), userInfo.getPassWords()), new OkHttpClientManager.DataCallBack() {
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
                        JSONObject json = object.optJSONObject("data");
                        String token = json.optString("token");
                        UserInfoBean user = new UserInfoBean(userInfo.getUserName(), userInfo.getPassWords(),token);
                        SharePreferencesUtil.saveObject(context, ConstantsUtils.USER_LOGIN_INFO, user);
                        nextLogin = true;
                        handler.sendEmptyMessage(ConstantsUtils.LOGIN_GET_DATA);
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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.SPLASH_START_ACTIVITY:
                    final UserInfoBean userInfo = (UserInfoBean) SharePreferencesUtil.readObject(context, ConstantsUtils.USER_LOGIN_INFO);
                    boolean isLogin ;
                    if (userInfo==null){
                        isLogin = false;
                    }else {
                        isLogin = true;
                    }
                    if (isLogin) {
                        doLogin();
//
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        LoginActivity.actionStart(SplashActivity.this,false);
                        finish();
                 }

                    break;
                case ConstantsUtils.LOGIN_GET_DATA:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
                default:
                    break;
            }
        }
    };


}