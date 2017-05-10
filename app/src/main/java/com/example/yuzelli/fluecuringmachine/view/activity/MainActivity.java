package com.example.yuzelli.fluecuringmachine.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutInit() {
        return R.layout.activity_main;
    }

    @Override
    protected void binEvent() {
        //oncreate方法中的回调

    }

    @Override
    protected void fillData() {
       //onresume方法中的回调
    }
}
