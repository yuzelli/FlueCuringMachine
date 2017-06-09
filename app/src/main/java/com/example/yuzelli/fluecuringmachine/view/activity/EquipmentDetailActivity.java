package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.EquipmentDetailBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.NumTrans;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;

public class EquipmentDetailActivity extends BaseActivity {


    @BindView(R.id.rl_black)
    RelativeLayout rl_black;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shangxiapeng)
    TextView tvShangxiapeng;
    @BindView(R.id.rl_set_tempAndTime)
    RelativeLayout rlSetTempAndTime;
    @BindView(R.id.tv_water_content)
    TextView tvWaterContent;
    @BindView(R.id.rl_set_water_content)
    RelativeLayout rlSetWaterContent;
    @BindView(R.id.tv_baking)
    TextView tvBaking;
    @BindView(R.id.rl_set_baking)
    RelativeLayout rlSetBaking;
    @BindView(R.id.tv_wind)
    TextView tvWind;
    @BindView(R.id.rl_set_wind)
    RelativeLayout rlSetWind;
    @BindView(R.id.tv_voltage)
    TextView tvVoltage;
    @BindView(R.id.rl_voltage)
    RelativeLayout rlVoltage;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.rl_num)
    RelativeLayout rlNum;
    @BindView(R.id.rl_set_system)
    RelativeLayout rlSetSystem;
    @BindView(R.id.tv_updryTemperature)
    TextView tvUpdryTemperature;
    @BindView(R.id.tv_upwetTemperature)
    TextView tvUpwetTemperature;
    @BindView(R.id.tv_periodNum)
    TextView tvPeriodNum;
    @BindView(R.id.tv_totalTime)
    TextView tvTotalTime;
    @BindView(R.id.tv_dryTarget)
    TextView tvDryTarget;
    @BindView(R.id.tv_wetTarget)
    TextView tvWetTarget;
    private EquipmentDetailBean equiDetail;
    private EDHandler handler;

    @Override
    protected int layoutInit() {
        return R.layout.activity_equipment_detail;
    }

    @Override
    protected void binEvent() {
        handler = new EDHandler();
        rl_black.setVisibility(View.VISIBLE);
        rl_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        doGetEquimentDetailData(intent.getStringExtra("getCoredataId"));
    }

    private void doGetEquimentDetailData(String eq_sn) {
        StringBuffer url = new StringBuffer(ConstantsUtils.ADDRESS_URL).append(ConstantsUtils.EQUIPMENT_DETAIL);
        url.append(eq_sn + "/").append("detail");
        OkHttpClientManager.getInstance().getAsync(url.toString(), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

                showToast("加载网路数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("--doGetDetailData-->", result);
                JSONObject json = new JSONObject(result);
                int errorCode = json.optInt("errorCode");
                if (errorCode == 0) {
                    JSONObject data = json.optJSONObject("data");
                    Gson gson = new Gson();
                    equiDetail = gson.fromJson(data.toString(), EquipmentDetailBean.class);
                    handler.sendEmptyMessage(ConstantsUtils.EQUIPMENT_DETAIL_GET_DATA);
                } else if (errorCode == 10001) {
                    showToast("参数错误！");
                } else if (errorCode == 10002) {
                    showToast("没有权限！");
                } else {
                    showToast("获取数据失败！");
                }

            }
        });
    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context, String getCoredataId) {
        Intent intent = new Intent(context, EquipmentDetailActivity.class);
        intent.putExtra("getCoredataId", getCoredataId);
        context.startActivity(intent);
    }

    class EDHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.EQUIPMENT_DETAIL_GET_DATA:
                    updataView();
                    break;
                default:
                    break;
            }
        }
    }

    private void updataView() {

        tvPeriodNum.setText(equiDetail.getSystemData().getPeriodNum());
        tvTotalTime.setText(equiDetail.getSystemData().getTotalTime());
        tvDryTarget.setText(equiDetail.getSystemData().getDryTarget());
        tvWetTarget.setText(equiDetail.getSystemData().getWetTarget());
        String systemStatus = equiDetail.getSystemData().getSystemStatus();
        String shangxiapenStataus = systemStatus.substring(1,2);
        if (shangxiapenStataus.equals("1")){
            tvShangxiapeng.setText("上棚");
            tvUpwetTemperature.setText(equiDetail.getSystemData().getUpwetTemperature());
            tvUpdryTemperature.setText(equiDetail.getSystemData().getUpdryTemperature());
        }else {
            tvShangxiapeng.setText("下棚");
            tvUpwetTemperature.setText(equiDetail.getSystemData().getDownwetTemperature());
            tvUpdryTemperature.setText(equiDetail.getSystemData().getDowndryTemperature());
        }
        int bakingStatus = Integer.valueOf(systemStatus.substring(2,3));
        switch (bakingStatus){
            case 1:
                tvBaking.setText("脚叶");
                break;
            case 2:
                tvBaking.setText("下二棚");
                break;
            case 3:
                tvBaking.setText("腰叶1");
                break;
            case 4:
                tvBaking.setText("腰叶2");
                break;
            case 5:
                tvBaking.setText("上二棚");
                break;
            case 6:
                tvBaking.setText("顶叶");
                break;
            default:
                break;
        }

       int watercontentStatus =  Integer.valueOf(systemStatus.substring(3,4));
        switch (watercontentStatus){
            case 1:
                tvWaterContent.setText("干旱天气");
                break;
            case 2:
                tvWaterContent.setText("正常天气");
                break;
            case 3:
                tvWaterContent.setText("多雨天");
                break;
            default:
                break;
        }
        tvVoltage.setText(equiDetail.getSystemData().getVoltage());
        tvNum.setText("第"+ NumTrans.input((equiDetail.getSystemData().getTimes()+1)+"")+"轮");
    }

}
