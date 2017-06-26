package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.EquipmentDetailBean;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.BaiduLoading;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class SetSytemParametersActivity extends BaseActivity {


    @OnClick(R.id.rl_black)
    public void rlBlack() {
        finish();
    }

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.spr_jieduan)
    Spinner sprJieduan;
    @BindView(R.id.spr_moshi)
    Spinner sprMoshi;
    @BindView(R.id.spr_zhuangtai)
    Spinner sprZhuangtai;
    private EquipmentDetailBean equipmentDetai;
    private SSPHandler handler;

    private int jeduanIndex = -1;
    private int moshiIndex = 1;
    private int zhuantaiIndex = -1;

    @Override
    protected int layoutInit() {
        return R.layout.activity_set_sytem_parameters;
    }

    private Context context;

    @Override
    protected void binEvent() {
        context = this;
        equipmentDetai = (EquipmentDetailBean) getIntent().getSerializableExtra("equipmentDetai");
        handler = new SSPHandler();
        tvAction.setVisibility(View.VISIBLE);
        tvAction.setText("设置完成");
        tvTitle.setText("系统参数设置");
        findViewById(R.id.rl_black).setVisibility(View.VISIBLE);
        tvName.setText(equipmentDetai.getDevice().getDeviceName());
        jeduanIndex = Integer.valueOf(equipmentDetai.getSystemData().getPeriodNum());
        zhuantaiIndex = equipmentDetai.getSystemData().getGo();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            list.add(i + "");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprJieduan.setAdapter(adapter);
        sprJieduan.setSelection(jeduanIndex);
        sprJieduan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jeduanIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> moshiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>(Arrays.asList(new String[]{"曲线模式"})));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprMoshi.setAdapter(moshiAdapter);
        sprMoshi.setSelection(0);
        sprMoshi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                moshiIndex = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> zhuangtaiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>(Arrays.asList(new String[]{"停止", "运行"})));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprZhuangtai.setAdapter(zhuangtaiAdapter);
        sprZhuangtai.setSelection(zhuantaiIndex);
        sprZhuangtai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zhuantaiIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiduLoading.onBeiginDialog(context);
                OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.SET_TEMP_POST + equipmentDetai.getDevice().getDeviceId() + "/system", getMap(), new OkHttpClientManager.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {
                        showToast("请求数据失败！");
                        BaiduLoading.onStopDialog();
                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        BaiduLoading.onStopDialog();
                        JSONObject object = new JSONObject(result);
                        int code = object.optInt("errorCode");
                        switch (code) {
                            case 0:
                                Message msg = new Message();
                                msg.what = ConstantsUtils.SET_SYSTEM_SSP;
                                showToast("修改成功！");
                                handler.sendMessage(msg);
                                break;
                            case 10001:
                                showToast("参数错误！");
                                break;
                            case 10002:
                                handler.sendEmptyMessage(ConstantsUtils.TOKEN_FALSE);
                                break;
                            default:
                                break;
                        }
                    }
                });

            }
        });

    }

    private Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("period", jeduanIndex + "");
        map.put("L_model", "1");
        map.put("go", zhuantaiIndex + "");
        map.put("token", getToken());

        return map;
    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context, EquipmentDetailBean equipmentDetai) {
        Intent intent = new Intent(context, SetSytemParametersActivity.class);
        intent.putExtra("equipmentDetai", equipmentDetai);
        context.startActivity(intent);
    }


    class SSPHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.SET_SYSTEM_SSP:
                    finish();
                    break;
                case ConstantsUtils.TOKEN_FALSE:
                    LoginActivity.actionStart(context,true);
                    break;
                default:
                    break;
            }
        }
    }

}
