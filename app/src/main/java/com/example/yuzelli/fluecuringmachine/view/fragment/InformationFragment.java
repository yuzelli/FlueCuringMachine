package com.example.yuzelli.fluecuringmachine.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseFragment;
import com.example.yuzelli.fluecuringmachine.utils.ListViewForScrollViewUtil;

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
    @BindView(R.id.lv_install)
    ListViewForScrollViewUtil lvInstall;
    @BindView(R.id.lv_getReady)
    ListViewForScrollViewUtil lvGetReady;
    @BindView(R.id.lv_maintain)
    ListViewForScrollViewUtil lvMaintain;
    Unbinder unbinder1;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
