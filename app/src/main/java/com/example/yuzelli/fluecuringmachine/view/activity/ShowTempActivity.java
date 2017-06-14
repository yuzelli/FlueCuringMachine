package com.example.yuzelli.fluecuringmachine.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.EquipmentDetailBean;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

public class ShowTempActivity extends BaseActivity implements View.OnClickListener {

    @OnClick(R.id.rl_black)
    public void rlBlack() {
        finish();
    }

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_action)
    TextView tvAction;
    @BindView(R.id.tv_drySet1)
    TextView tvDrySet1;
    @BindView(R.id.tv_drySet2)
    TextView tvDrySet2;
    @BindView(R.id.tv_drySet3)
    TextView tvDrySet3;
    @BindView(R.id.tv_drySet4)
    TextView tvDrySet4;
    @BindView(R.id.tv_drySet5)
    TextView tvDrySet5;
    @BindView(R.id.tv_drySet6)
    TextView tvDrySet6;
    @BindView(R.id.tv_drySet7)
    TextView tvDrySet7;
    @BindView(R.id.tv_drySet8)
    TextView tvDrySet8;
    @BindView(R.id.tv_drySet9)
    TextView tvDrySet9;
    @BindView(R.id.tv_drySet10)
    TextView tvDrySet10;
    @BindView(R.id.tv_wetSet1)
    TextView tvWetSet1;
    @BindView(R.id.tv_wetSet2)
    TextView tvWetSet2;
    @BindView(R.id.tv_wetSet3)
    TextView tvWetSet3;
    @BindView(R.id.tv_wetSet4)
    TextView tvWetSet4;
    @BindView(R.id.tv_wetSet5)
    TextView tvWetSet5;
    @BindView(R.id.tv_wetSet6)
    TextView tvWetSet6;
    @BindView(R.id.tv_wetSet7)
    TextView tvWetSet7;
    @BindView(R.id.tv_wetSet8)
    TextView tvWetSet8;
    @BindView(R.id.tv_wetSet9)
    TextView tvWetSet9;
    @BindView(R.id.tv_wetSet10)
    TextView tvWetSet10;
    @BindView(R.id.tv_timeSet1)
    TextView tvTimeSet1;
    @BindView(R.id.tv_timeSet2)
    TextView tvTimeSet2;
    @BindView(R.id.tv_timeSet3)
    TextView tvTimeSet3;
    @BindView(R.id.tv_timeSet4)
    TextView tvTimeSet4;
    @BindView(R.id.tv_timeSet5)
    TextView tvTimeSet5;
    @BindView(R.id.tv_timeSet6)
    TextView tvTimeSet6;
    @BindView(R.id.tv_timeSet7)
    TextView tvTimeSet7;
    @BindView(R.id.tv_timeSet8)
    TextView tvTimeSet8;
    @BindView(R.id.tv_timeSet9)
    TextView tvTimeSet9;
    @BindView(R.id.tv_timeSet10)
    TextView tvTimeSet10;
    @BindView(R.id.tv_timeUp1)
    TextView tvTimeUp1;
    @BindView(R.id.tv_timeUp2)
    TextView tvTimeUp2;
    @BindView(R.id.tv_timeUp3)
    TextView tvTimeUp3;
    @BindView(R.id.tv_timeUp4)
    TextView tvTimeUp4;
    @BindView(R.id.tv_timeUp5)
    TextView tvTimeUp5;
    @BindView(R.id.tv_timeUp6)
    TextView tvTimeUp6;
    @BindView(R.id.tv_timeUp7)
    TextView tvTimeUp7;
    @BindView(R.id.tv_timeUp8)
    TextView tvTimeUp8;
    @BindView(R.id.tv_timeUp9)
    TextView tvTimeUp9;


    private EquipmentDetailBean.CoreDataBean coredata;
    private String deviceId;
    private ArrayList<Boolean> settingFlags;

    @Override
    protected int layoutInit() {
        return R.layout.activity_show_temp;
    }

    @Override
    protected void binEvent() {
        coredata = (EquipmentDetailBean.CoreDataBean) getIntent().getSerializableExtra("coredata");
        deviceId =  getIntent().getStringExtra("deviceId");
        findViewById(R.id.rl_black).setVisibility(View.VISIBLE);
        tvTitle.setText("当前设备详情");
        tvAction.setVisibility(View.VISIBLE);
        tvAction.setText("去设置");
        tvDrySet1.setText(coredata.getDrySet1());
        tvDrySet2.setText(coredata.getDrySet2());
        tvDrySet3.setText(coredata.getDrySet3());
        tvDrySet4.setText(coredata.getDrySet4());
        tvDrySet5.setText(coredata.getDrySet5());
        tvDrySet6.setText(coredata.getDrySet6());
        tvDrySet7.setText(coredata.getDrySet7());
        tvDrySet8.setText(coredata.getDrySet8());
        tvDrySet9.setText(coredata.getDrySet9());
        tvDrySet10.setText(coredata.getDrySet10());

        tvWetSet1.setText(coredata.getWetSet1());
        tvWetSet2.setText(coredata.getWetSet2());
        tvWetSet3.setText(coredata.getWetSet3());
        tvWetSet4.setText(coredata.getWetSet4());
        tvWetSet5.setText(coredata.getWetSet5());
        tvWetSet6.setText(coredata.getWetSet6());
        tvWetSet7.setText(coredata.getWetSet7());
        tvWetSet8.setText(coredata.getWetSet8());
        tvWetSet9.setText(coredata.getWetSet9());
        tvWetSet10.setText(coredata.getWetSet10());

        tvTimeSet1.setText(coredata.getTimeSet1());
        tvTimeSet2.setText(coredata.getTimeSet2());
        tvTimeSet3.setText(coredata.getTimeSet3());
        tvTimeSet4.setText(coredata.getTimeSet4());
        tvTimeSet5.setText(coredata.getTimeSet5());
        tvTimeSet6.setText(coredata.getTimeSet6());
        tvTimeSet7.setText(coredata.getTimeSet7());
        tvTimeSet8.setText(coredata.getTimeSet8());
        tvTimeSet9.setText(coredata.getTimeSet9());
        tvTimeSet10.setText(coredata.getTimeSet10());

        tvTimeUp1.setText(coredata.getTimeUp1());
        tvTimeUp2.setText(coredata.getTimeUp2());
        tvTimeUp3.setText(coredata.getTimeUp3());
        tvTimeUp4.setText(coredata.getTimeUp4());
        tvTimeUp5.setText(coredata.getTimeUp5());
        tvTimeUp6.setText(coredata.getTimeUp6());
        tvTimeUp7.setText(coredata.getTimeUp7());
        tvTimeUp8.setText(coredata.getTimeUp8());
        tvTimeUp9.setText(coredata.getTimeUp9());

        tvAction.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    protected void fillData() {

    }

    public static void actionStart(Context context, EquipmentDetailBean.CoreDataBean coredata,String deviceId) {
        Intent intent = new Intent(context, ShowTempActivity.class);
        intent.putExtra("coredata", coredata);
        intent.putExtra("deviceId", deviceId);
        context.startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_action:
                if (tvAction.getText().toString().trim().equals("去设置")) {
                    doBeginSetAction();
                } else if (tvAction.getText().toString().trim().equals("设置完成")) {
                    for (Boolean b : settingFlags){
                        if (b.booleanValue()==false){
                            showToast("请全部设置完成后在上传！");
                            return;
                        }
                    }
                    doPushActionSetting();
                }
                break;
            case R.id.tv_wetSet1:
                showSettingDialog(tvWetSet1,1);
                break;
            case R.id.tv_wetSet2:
                showSettingDialog(tvWetSet2,2);
                break;
            case R.id.tv_wetSet3:
                showSettingDialog(tvWetSet3,3);
                break;
            case R.id.tv_wetSet4:
                showSettingDialog(tvWetSet4,4);
                break;
            case R.id.tv_wetSet5:
                showSettingDialog(tvWetSet5,5);
                break;
            case R.id.tv_wetSet6:
                showSettingDialog(tvWetSet6,6);
                break;
            case R.id.tv_wetSet7:
                showSettingDialog(tvWetSet7,7);
                break;
            case R.id.tv_wetSet8:
                showSettingDialog(tvWetSet8,8);
                break;
            case R.id.tv_wetSet9:
                showSettingDialog(tvWetSet9,9);
                break;
            case R.id.tv_wetSet10:
                showSettingDialog(tvWetSet10,10);
                break;
            case R.id.tv_drySet1:
                showSettingDialog(tvDrySet1,11);
                break;
            case R.id.tv_drySet2:
                showSettingDialog(tvDrySet2,12);
                break;
            case R.id.tv_drySet3:
                showSettingDialog(tvDrySet3,13);
                break;
            case R.id.tv_drySet4:
                showSettingDialog(tvDrySet4,14);
                break;
            case R.id.tv_drySet5:
                showSettingDialog(tvDrySet5,15);
                break;
            case R.id.tv_drySet6:
                showSettingDialog(tvDrySet6,16);
                break;
            case R.id.tv_drySet7:
                showSettingDialog(tvDrySet7,17);
                break;
            case R.id.tv_drySet8:
                showSettingDialog(tvDrySet8,18);
                break;
            case R.id.tv_drySet9:
                showSettingDialog(tvDrySet9,19);
                break;
            case R.id.tv_drySet10:
                showSettingDialog(tvDrySet10,20);
                break;
            case R.id.tv_timeSet1:
                showSettingDialog(tvTimeSet1,21);
                break;
            case R.id.tv_timeSet2:
                showSettingDialog(tvTimeSet2,22);
                break;
            case R.id.tv_timeSet3:
                showSettingDialog(tvTimeSet3,23);
                break;
            case R.id.tv_timeSet4:
                showSettingDialog(tvTimeSet4,24);
                break;
            case R.id.tv_timeSet5:
                showSettingDialog(tvTimeSet5,25);
                break;
            case R.id.tv_timeSet6:
                showSettingDialog(tvTimeSet6,26);
                break;
            case R.id.tv_timeSet7:
                showSettingDialog(tvTimeSet7,27);
                break;
            case R.id.tv_timeSet8:
                showSettingDialog(tvTimeSet8,28);
                break;
            case R.id.tv_timeSet9:
                showSettingDialog(tvTimeSet9,29);
                break;
            case R.id.tv_timeSet10:
                showSettingDialog(tvTimeSet10,30);
                break;
            case R.id.tv_timeUp1:
                showSettingDialog(tvTimeUp1,31);
                break;
            case R.id.tv_timeUp2:
                showSettingDialog(tvTimeUp2,32);
                break;
            case R.id.tv_timeUp3:
                showSettingDialog(tvTimeUp3,33);
                break;
            case R.id.tv_timeUp4:
                showSettingDialog(tvTimeUp4,34);
                break;
            case R.id.tv_timeUp5:
                showSettingDialog(tvTimeUp5,35);
                break;
            case R.id.tv_timeUp6:
                showSettingDialog(tvTimeUp6,36);
                break;
            case R.id.tv_timeUp7:
                showSettingDialog(tvTimeUp7,37);
                break;
            case R.id.tv_timeUp8:
                showSettingDialog(tvTimeUp8,38);
                break;
            case R.id.tv_timeUp9:
                showSettingDialog(tvTimeUp9,39);
                break;
            default:
                break;
        }
    }

    /**
     * 上传操作
     */
    private void doPushActionSetting() {
        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.SET_TEMP_POST+deviceId+"/ececute",setMap(), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                showToast("请求数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {

                JSONObject object = new JSONObject(result);
                int code = object.optInt("errorCode");
                switch (code) {
                    case 0:
                        finish();
                        showToast("修改成功！");
                        break;
                    case 10001:
                        showToast("参数错误！");
                        break;
                    case 10002:
                        showToast("没有权限！");
                        break;
                    default:
                        break;
                }
            }
        });


    }

    /**
     * 设置参数
     * @return
     */
    private Map<String,String> setMap() {
        Map<String ,String> map = new HashMap<>();
        map.put("token",getToken());
        JSONObject json = new JSONObject();
        try {
            json.put("coredataId",0);
            json.put("coreType",1);
            json.put("creatTime",null);
            json.put("wetSet1",tvWetSet1.getText().toString().trim());
            json.put("wetSet2",tvWetSet2.getText().toString().trim());
            json.put("wetSet3",tvWetSet3.getText().toString().trim());
            json.put("wetSet4",tvWetSet4.getText().toString().trim());
            json.put("wetSet5",tvWetSet5.getText().toString().trim());
            json.put("wetSet6",tvWetSet6.getText().toString().trim());
            json.put("wetSet7",tvWetSet7.getText().toString().trim());
            json.put("wetSet8",tvWetSet8.getText().toString().trim());
            json.put("wetSet9",tvWetSet9.getText().toString().trim());
            json.put("wetSet10",tvWetSet10.getText().toString().trim());
            json.put("drySet1",tvDrySet1.getText().toString().trim());
            json.put("drySet2",tvDrySet2.getText().toString().trim());
            json.put("drySet3",tvDrySet3.getText().toString().trim());
            json.put("drySet4",tvDrySet4.getText().toString().trim());
            json.put("drySet5",tvDrySet5.getText().toString().trim());
            json.put("drySet6",tvDrySet6.getText().toString().trim());
            json.put("drySet7",tvDrySet7.getText().toString().trim());
            json.put("drySet8",tvDrySet8.getText().toString().trim());
            json.put("drySet9",tvDrySet9.getText().toString().trim());
            json.put("drySet10",tvDrySet10.getText().toString().trim());
            json.put("timeSet1",tvTimeSet1.getText().toString().trim());
            json.put("timeSet2",tvTimeSet2.getText().toString().trim());
            json.put("timeSet3",tvTimeSet3.getText().toString().trim());
            json.put("timeSet4",tvTimeSet4.getText().toString().trim());
            json.put("timeSet5",tvTimeSet5.getText().toString().trim());
            json.put("timeSet6",tvTimeSet6.getText().toString().trim());
            json.put("timeSet7",tvTimeSet7.getText().toString().trim());
            json.put("timeSet8",tvTimeSet8.getText().toString().trim());
            json.put("timeSet9",tvTimeSet9.getText().toString().trim());
            json.put("timeSet10",tvTimeSet10.getText().toString().trim());
            json.put("timeUp1",tvTimeUp1.getText().toString().trim());
            json.put("timeUp2",tvTimeUp2.getText().toString().trim());
            json.put("timeUp3",tvTimeUp3.getText().toString().trim());
            json.put("timeUp4",tvTimeUp4.getText().toString().trim());
            json.put("timeUp5",tvTimeUp5.getText().toString().trim());
            json.put("timeUp6",tvTimeUp6.getText().toString().trim());
            json.put("timeUp7",tvTimeUp7.getText().toString().trim());
            json.put("timeUp8",tvTimeUp8.getText().toString().trim());
            json.put("timeUp9",tvTimeUp9.getText().toString().trim());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.put("coreDate",json.toString());
        return map;
    }

    /**
     *
     * @param tv
     * @param postion
     */
    private void showSettingDialog(final TextView tv,final int postion) {

        final Dialog dialog = new Dialog(this, R.style.PhotoDialog2);
        View view = LayoutInflater.from(ShowTempActivity.this).inflate(R.layout.setting_temp_or_time_dialog, null);
        final EditText et_input = (EditText) view.findViewById(R.id.et_input);
        dialog.setContentView(view);
        et_input.setText(tv.getText().toString().trim());

        final TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
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
                String input = et_input.getText().toString().trim();
                if (input.equals("")){
                    showToast("请输入！");
                    return;
                }
                tv.setText(input);
                tv.setTextColor(Color.BLACK);
                int index = postion;
                settingFlags.set(index-1,Boolean.TRUE);
                dialog.dismiss();
            }
        });
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogWindow.setAttributes(p);

//        android Activity改成dialog样式后 怎设置点击空白处关闭窗体，如图点击窗体意外的地方关闭窗体
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void doBeginSetAction() {
        tvAction.setText("设置完成");
        settingFlags = new ArrayList<>();
        for (int i = 0 ; i < 39 ; i ++){
            settingFlags.add(Boolean.FALSE);
        }
        tvWetSet1.setTextColor(Color.GRAY);
        tvWetSet2.setTextColor(Color.GRAY);
        tvWetSet3.setTextColor(Color.GRAY);
        tvWetSet4.setTextColor(Color.GRAY);
        tvWetSet5.setTextColor(Color.GRAY);
        tvWetSet6.setTextColor(Color.GRAY);
        tvWetSet7.setTextColor(Color.GRAY);
        tvWetSet8.setTextColor(Color.GRAY);
        tvWetSet9.setTextColor(Color.GRAY);
        tvWetSet10.setTextColor(Color.GRAY);
        tvDrySet1.setTextColor(Color.GRAY);
        tvDrySet2.setTextColor(Color.GRAY);
        tvDrySet3.setTextColor(Color.GRAY);
        tvDrySet4.setTextColor(Color.GRAY);
        tvDrySet5.setTextColor(Color.GRAY);
        tvDrySet6.setTextColor(Color.GRAY);
        tvDrySet7.setTextColor(Color.GRAY);
        tvDrySet8.setTextColor(Color.GRAY);
        tvDrySet9.setTextColor(Color.GRAY);
        tvDrySet10.setTextColor(Color.GRAY);

        tvTimeSet1.setTextColor(Color.GRAY);
        tvTimeSet2.setTextColor(Color.GRAY);
        tvTimeSet3.setTextColor(Color.GRAY);
        tvTimeSet4.setTextColor(Color.GRAY);
        tvTimeSet5.setTextColor(Color.GRAY);
        tvTimeSet6.setTextColor(Color.GRAY);
        tvTimeSet7.setTextColor(Color.GRAY);
        tvTimeSet8.setTextColor(Color.GRAY);
        tvTimeSet9.setTextColor(Color.GRAY);
        tvTimeSet10.setTextColor(Color.GRAY);
        tvTimeUp1.setTextColor(Color.GRAY);
        tvTimeUp2.setTextColor(Color.GRAY);
        tvTimeUp3.setTextColor(Color.GRAY);
        tvTimeUp4.setTextColor(Color.GRAY);
        tvTimeUp5.setTextColor(Color.GRAY);
        tvTimeUp6.setTextColor(Color.GRAY);
        tvTimeUp7.setTextColor(Color.GRAY);
        tvTimeUp8.setTextColor(Color.GRAY);
        tvTimeUp9.setTextColor(Color.GRAY);
        tvWetSet1.setOnClickListener(this);
        tvWetSet2.setOnClickListener(this);
        tvWetSet3.setOnClickListener(this);
        tvWetSet4.setOnClickListener(this);
        tvWetSet5.setOnClickListener(this);
        tvWetSet6.setOnClickListener(this);
        tvWetSet7.setOnClickListener(this);
        tvWetSet8.setOnClickListener(this);
        tvWetSet9.setOnClickListener(this);
        tvWetSet10.setOnClickListener(this);
        tvDrySet1.setOnClickListener(this);
        tvDrySet2.setOnClickListener(this);
        tvDrySet3.setOnClickListener(this);
        tvDrySet4.setOnClickListener(this);
        tvDrySet5.setOnClickListener(this);
        tvDrySet6.setOnClickListener(this);
        tvDrySet7.setOnClickListener(this);
        tvDrySet8.setOnClickListener(this);
        tvDrySet9.setOnClickListener(this);
        tvDrySet10.setOnClickListener(this);

        tvTimeSet1.setOnClickListener(this);
        tvTimeSet2.setOnClickListener(this);
        tvTimeSet3.setOnClickListener(this);
        tvTimeSet4.setOnClickListener(this);
        tvTimeSet5.setOnClickListener(this);
        tvTimeSet6.setOnClickListener(this);
        tvTimeSet7.setOnClickListener(this);
        tvTimeSet8.setOnClickListener(this);
        tvTimeSet9.setOnClickListener(this);
        tvTimeSet10.setOnClickListener(this);
        tvTimeUp1.setOnClickListener(this);
        tvTimeUp2.setOnClickListener(this);
        tvTimeUp3.setOnClickListener(this);
        tvTimeUp4.setOnClickListener(this);
        tvTimeUp5.setOnClickListener(this);
        tvTimeUp6.setOnClickListener(this);
        tvTimeUp7.setOnClickListener(this);
        tvTimeUp8.setOnClickListener(this);
        tvTimeUp9.setOnClickListener(this);
    }
}
//              tvWetSet1
//              tvWetSet2
//              tvWetSet3
//              tvWetSet4
//              tvWetSet5
//              tvWetSet6
//              tvWetSet7
//              tvWetSet8
//              tvWetSet9
//              tvWetSet10
//              tvDrySet1
//              tvDrySet2
//              tvDrySet3
//              tvDrySet4
//              tvDrySet5
//              tvDrySet6
//              tvDrySet7
//           tvDrySet8
//          tvDrySet9
//             tvDrySet10
//
//              tvTimeSet1
//              tvTimeSet2
//              tvTimeSet3
//              tvTimeSet4.
//              tvTimeSet5.
//              tvTimeSet6.
//              tvTimeSet7.
//              tvTimeSet8.
//              tvTimeSet9.
//              tvTimeSet10.
//              tvTimeUp1.
//              tvTimeUp2.
//              tvTimeUp3.
//              tvTimeUp4.
//              tvTimeUp5.
//              tvTimeUp6.
//              tvTimeUp7.
//              tvTimeUp8.
//              tvTimeUp9.