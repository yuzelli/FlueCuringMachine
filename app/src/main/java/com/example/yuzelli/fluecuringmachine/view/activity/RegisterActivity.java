package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;

public class RegisterActivity extends BaseActivity {


    @Override
    protected int layoutInit() {
        return R.layout.activity_register;
    }

    @Override
    protected void binEvent() {

    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }
}
