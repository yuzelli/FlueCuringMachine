package com.example.yuzelli.fluecuringmachine.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseFragment;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;
import com.example.yuzelli.fluecuringmachine.view.activity.ChangePassActivity;
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

    @OnClick(R.id.tv_customer_service)
    public void tvCustomerService() {
        Uri uri = Uri.parse("tel:" + ConstantsUtils.CUSTOMER_SERVICE_TEL_PHONE);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(uri);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.tv_change_pass)
    public void tvChangePass() {
        showPopupWindow();
    }

    @OnClick(R.id.tv_exit_login)
    public void tvExitLogin() {
        SharePreferencesUtil.saveObject(context, ConstantsUtils.USER_LOGIN_INFO, null);
        getActivity().finish();
        MainActivity.actionStart(context);
    }

    private void showPopupWindow() {
        final Dialog dialog = new Dialog(getActivity(), R.style.PhotoDialog2);
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.personal_change_select_diallog, null);
        dialog.setContentView(view);
        final EditText et_input = (EditText) view.findViewById(R.id.et_input);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoBean userInfo = (UserInfoBean) SharePreferencesUtil.readObject(getActivity(), ConstantsUtils.USER_LOGIN_INFO);
                String password = et_input.getText().toString().trim();
                if (password.equals(userInfo.getPassWords())){
                    dialog.dismiss();
                    ChangePassActivity.actionStart(getActivity());
                }else {
                    showToast("密码错误");
                }
            }
        });
//        android Activity改成dialog样式后 怎设置点击空白处关闭窗体，如图点击窗体意外的地方关闭窗体
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

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