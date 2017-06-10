package com.example.yuzelli.fluecuringmachine.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseFragment;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;
import com.example.yuzelli.fluecuringmachine.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/6/5.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;
    @BindView(R.id.tv_customer_service)
    TextView tvCustomerService;
    @BindView(R.id.tv_change_pass)
    TextView tvChangePass;
    @OnClick(R.id.tv_exit_login)
    public void tvExitLogin(){
        SharePreferencesUtil.saveObject(context, ConstantsUtils.USER_LOGIN_INFO, null);
        getActivity().finish();
        MainActivity.act ionStart(context);
    }


    private Context context;
    @Override
    protected int layoutInit() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void bindEvent(View v) {
        context = getActivity();
        tvTitle.setText("我的");
    }

    @Override
    protected void fillData() {

    }

}