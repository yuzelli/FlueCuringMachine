package com.example.yuzelli.fluecuringmachine.view.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.utils.ListViewForScrollViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetSystemActivity extends BaseActivity {


    @BindView(R.id.rl_black)
    RelativeLayout rlBlack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.tv_weizhi)
    TextView tvWeizhi;
    @BindView(R.id.lv_weizhi)
    ListViewForScrollViewUtil lvWeizhi;
    @BindView(R.id.tv_shuifeng)
    TextView tvShuifeng;
    @BindView(R.id.lv_shuifeng)
    ListViewForScrollViewUtil lvShuifeng;

    @Override
    protected int layoutInit() {
        return R.layout.activity_set_system;
    }

    @Override
    protected void binEvent() {

    }

    @Override
    protected void fillData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
