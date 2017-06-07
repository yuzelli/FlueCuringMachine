package com.example.yuzelli.fluecuringmachine.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 51644 on 2017/6/5.
 */

public class InformationFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;

    @Override
    protected int layoutInit() {
        return R.layout.fragment_information;
    }

    @Override
    protected void bindEvent(View v) {
        tvTitle.setText("信息");
    }

    @Override
    protected void fillData() {

    }


}
