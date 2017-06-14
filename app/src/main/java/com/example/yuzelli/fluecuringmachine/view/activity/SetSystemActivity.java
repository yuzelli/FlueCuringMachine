package com.example.yuzelli.fluecuringmachine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseActivity;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.CommonAdapter;
import com.example.yuzelli.fluecuringmachine.utils.ListViewForScrollViewUtil;
import com.example.yuzelli.fluecuringmachine.utils.ViewHolder;

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

public class SetSystemActivity extends BaseActivity {


    @OnClick(R.id.rl_black)
    public void rlBlack() {
        finish();
    }

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
    private int weizhiIndex = -1;
    private int sheifengIndex = -1;
    private String deviceID ;
    private String systemStatus ;
    private SetSystemHandler handler;

    @Override
    protected int layoutInit() {
        return R.layout.activity_set_system;
    }

    @Override
    protected void binEvent() {
        tvAction.setVisibility(View.VISIBLE);
        tvAction.setText("设置完成");
        handler = new SetSystemHandler();
        tvTitle.setText("设置烘烤属性的配置");
        findViewById(R.id.rl_black).setVisibility(View.VISIBLE);
        systemStatus = getIntent().getStringExtra("systemStatus");
        SetSystem(systemStatus);
        deviceID = getIntent().getStringExtra("deviceID");
        final List<String> weizhiList = new ArrayList<>(Arrays.asList(new String[]{"脚叶", "下二棚", "腰叶1", "腰叶2", "上二棚", "顶叶"}));
        lvWeizhi.setAdapter(new CommonAdapter<String>(this, weizhiList, R.layout.cell_info_item) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv_cotent, item);
            }
        });
        lvWeizhi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvWeizhi.setText(weizhiList.get(position));
                weizhiIndex = position+1;
            }
        });
        final List<String> shuifengList = new ArrayList<>(Arrays.asList(new String[]{ "干旱天气", "正常气候", "多雨天"}));
        lvShuifeng.setAdapter(new CommonAdapter<String>(this, shuifengList, R.layout.cell_info_item) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv_cotent, item);

            }
        });
        lvShuifeng.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvShuifeng.setText(shuifengList.get(position));
                sheifengIndex = position+1;
            }
        });

        tvAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvWeizhi.equals("未设置")||weizhiIndex==-1){
                    showToast("请设置烘烤位置！");
                    return;
                }if(tvShuifeng.equals("未设置")||sheifengIndex==-1){
                    showToast("请设置烘烟水分！");
                    return;
                }
                doSetSystemAction();

            }
        });
    }

    /**
     * 设置网络请求
     */
    private void doSetSystemAction() {
        OkHttpClientManager.getInstance().postAsync(ConstantsUtils.ADDRESS_URL + ConstantsUtils.SET_SYSTEM_POST+deviceID+"/change", getMap(), new OkHttpClientManager.DataCallBack() {
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
                        Message msg = new Message();
                        msg.what = ConstantsUtils.SET_SYSTEM;
                        handler.sendMessage(msg);
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

    private Map<String,String> getMap() {
        Map<String,String> map = new HashMap<>();
        map.put("token",getToken());
        StringBuffer buffer = new StringBuffer(systemStatus.substring(0,2));
        buffer.append(weizhiIndex+"");
        buffer.append(sheifengIndex+"");
        map.put("systemStatus",buffer.toString());
        return map;
    }

    @Override
    protected void fillData() {

    }

    /**
     * 设置烘烤位置
     *
     * @param systemStatus
     */
    private void SetSystem(String systemStatus) {
        if (systemStatus.length() == 2) {
            tvWeizhi.setText("未设置");
            tvShuifeng.setText("未设置");
            return;
        }
        int bakingStatus = Integer.valueOf(systemStatus.substring(2, 3));
        switch (bakingStatus) {
            case 1:
                tvWeizhi.setText("脚叶");
                break;
            case 2:
                tvWeizhi.setText("下二棚");
                break;
            case 3:
                tvWeizhi.setText("腰叶1");
                break;
            case 4:
                tvWeizhi.setText("腰叶2");
                break;
            case 5:
                tvWeizhi.setText("上二棚");
                break;
            case 6:
                tvWeizhi.setText("顶叶");
                break;
            default:
                break;
        }
        if (systemStatus.length() == 3) {
            tvShuifeng.setText("未设置");
            return;
        }
        int watercontentStatus = Integer.valueOf(systemStatus.substring(3, 4));
        switch (watercontentStatus) {
            case 1:
                tvShuifeng.setText("干旱天气");
                break;
            case 2:
                tvShuifeng.setText("正常天气");
                break;
            case 3:
                tvShuifeng.setText("多雨天");
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context, String systemStatus,String deviceID) {
        Intent intent = new Intent(context, SetSystemActivity.class);
        intent.putExtra("systemStatus", systemStatus);
        intent.putExtra("deviceID", deviceID);
        context.startActivity(intent);
    }

    class SetSystemHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ConstantsUtils.SET_SYSTEM:
                    finish();
                    break;
            }
        }
    }
}
