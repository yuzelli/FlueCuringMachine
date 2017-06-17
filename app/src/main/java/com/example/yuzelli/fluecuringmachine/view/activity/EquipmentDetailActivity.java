package com.example.yuzelli.fluecuringmachine.view.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.EquipmentDetailBean;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.BaiduLoading;
import com.example.yuzelli.fluecuringmachine.utils.NumTrans;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class EquipmentDetailActivity extends BaseActivity {


    @BindView(R.id.rl_black)
    RelativeLayout rl_black;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_water_content)
    TextView tvWaterContent;

    @BindView(R.id.tv_baking)
    TextView tvBaking;

    @BindView(R.id.tv_wind)
    TextView tvWind;

    @BindView(R.id.tv_voltage)
    TextView tvVoltage;
    @BindView(R.id.rl_voltage)
    RelativeLayout rlVoltage;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.rl_num)
    RelativeLayout rlNum;

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
    private Context context;
    private String deviceID;

    @OnClick({R.id.rl_set_tempAndTime, R.id.rl_set_baking, R.id.rl_set_water_content, R.id.rl_set_system})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.rl_set_tempAndTime:
                showPopupWindow("1");
                break;
            case R.id.rl_set_baking:
                showPopupWindow("2");
                break;
            case R.id.rl_set_water_content:
                showPopupWindow("2");
                break;
            case R.id.rl_set_system:
                showPopupWindow("3");
                break;
            default:
                break;
        }
    }

    @Override
    protected int layoutInit() {
        return R.layout.activity_equipment_detail;
    }

    @Override
    protected void binEvent() {
        handler = new EDHandler();
        context = this;
        rl_black.setVisibility(View.VISIBLE);
        rl_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        deviceID = intent.getStringExtra("getCoredataId");
    }

    private void doGetEquimentDetailData(String eq_sn) {
        BaiduLoading.onBeiginDialog(context);
        StringBuffer url = new StringBuffer(ConstantsUtils.ADDRESS_URL).append(ConstantsUtils.EQUIPMENT_DETAIL);
        url.append(eq_sn + "/").append("detail").append("/" + getToken());
        OkHttpClientManager.getInstance().getAsync(url.toString(), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
           BaiduLoading.onStopDialog();
                showToast("加载网路数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("--doGetDetailData-->", result);
                BaiduLoading.onStopDialog();
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
    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        doGetEquimentDetailData(deviceID);
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

        tvPeriodNum.setText(equiDetail.getSystemData().getPeriod());
        tvTotalTime.setText(equiDetail.getSystemData().getTotalTime());
        tvDryTarget.setText("目标:"+equiDetail.getSystemData().getDryTarget());
        tvWetTarget.setText("目标:"+equiDetail.getSystemData().getWetTarget());
        String systemStatus = equiDetail.getSystemData().getSystemStatus();
        String shangxiapenStataus = systemStatus.substring(1, 2);
//        if (shangxiapenStataus.equals("1")) {
//            tvShangxiapeng.setText("上棚");
            tvUpwetTemperature.setText("上棚:"+equiDetail.getSystemData().getUpwetTemperature()+"\n\n"+"下棚:"+equiDetail.getSystemData().getDownwetTemperature());
            tvUpdryTemperature.setText("上棚:"+equiDetail.getSystemData().getUpdryTemperature()+"\n\n"+"下棚:"+equiDetail.getSystemData().getDowndryTemperature());
//        } else {
//            tvShangxiapeng.setText("下棚");
//            tvUpwetTemperature.setText(equiDetail.getSystemData().getDownwetTemperature());
//            tvUpdryTemperature.setText(equiDetail.getSystemData().getDowndryTemperature());
//        }
        SetSystem(systemStatus);

        tvVoltage.setText(equiDetail.getSystemData().getVoltage());
        tvNum.setText("第" + NumTrans.input((equiDetail.getSystemData().getTimes()) + "") + "轮");

        doShowWarning();
    }

    /**
     * 设置烘烤位置
     *
     * @param systemStatus
     */
    private void SetSystem(String systemStatus) {
        if (systemStatus.length() == 2) {
            tvBaking.setText("未设置");
            tvWaterContent.setText("未设置");
            return;
        }
        int bakingStatus = Integer.valueOf(systemStatus.substring(2, 3));
        switch (bakingStatus) {
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
        if (systemStatus.length() == 3) {
            tvWaterContent.setText("未设置");
            return;
        }
        int watercontentStatus = Integer.valueOf(systemStatus.substring(3, 4));
        switch (watercontentStatus) {
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
    }

    /**
     * 判断警告信息
     */
    private void doShowWarning() {

      String title = equiDetail.getDevice().getDeviceName();
        StringBuffer buffer = new StringBuffer();
        for (String str : equiDetail.getAlarms()) {
            int index = Integer.valueOf(str);
            switch (index) {
                case 1:
                    buffer.append("偏高\n");
                    break;
                case 2:
                    buffer.append("严重偏高-检查传感器或者设备\n");
                    break;
                case 3:
                    buffer.append("运行超时-请重新确认数据\n");
                    break;
                case 4:
                    buffer.append("电压超过260V-检查供电电源\n");
                    break;
                case 5:
                    buffer.append("电压低于170V-检查供电电源\n");
                    break;
                case 6:
                    buffer.append("设备未连接-可能关机或者停机\n");
                    break;
                case 7:
                    buffer.append("风机过载-请检查风机和供电电源\n");
                    break;
                case 8:
                    buffer.append("风机无电流-请检查风机和供电电源\n");
                    break;
                case 9:
                    buffer.append("目标棚传感器故障-检查传感器或者设备\n");
                    break;
                case 10:
                    buffer.append("参考棚传感器故障-检查传感器或者设备\n");
                    break;
                default:
                    break;
            }
        }

        if (equiDetail.getAlarms().size()>0){
            showWarning(title,buffer.toString());
        }

    }

    /**
     * 设置温度和时长
     */
    private void showPopupWindow(final String where) {
        final Dialog dialog = new Dialog(context, R.style.PhotoDialog2);
        final View view = LayoutInflater.from(context).inflate(R.layout.personal_change_select_diallog, null);
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
                UserInfoBean userInfo = (UserInfoBean) SharePreferencesUtil.readObject(context, ConstantsUtils.USER_LOGIN_INFO);
                String password = et_input.getText().toString().trim();
                if (password.equals(userInfo.getPassWords())) {
                    dialog.dismiss();
                    if (where.equals("1")) {
                        ShowTempActivity.actionStart(context, equiDetail.getCoreData(), equiDetail.getDevice().getDeviceId() + "");
                    }
                    if (where.equals("2")) {
                        SetSystemActivity.actionStart(context, equiDetail.getSystemData().getSystemStatus(), equiDetail.getDevice().getDeviceId() + "");
                    }
                    if (where.equals("3")) {
                        SetSytemParametersActivity.actionStart(context, equiDetail);
                    }

                } else {
                    showToast("密码错误");
                }
            }
        });
//        android Activity改成dialog样式后 怎设置点击空白处关闭窗体，如图点击窗体意外的地方关闭窗体
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void showWarning(String title,String contentText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 构建
            builder.setTitle(title+"设备警告！");
        builder.setMessage(contentText);
        // 添加确定按钮 listener事件是继承与DialogInerface的
        builder.setPositiveButton("确定", null);
        builder.show();
    }
}
