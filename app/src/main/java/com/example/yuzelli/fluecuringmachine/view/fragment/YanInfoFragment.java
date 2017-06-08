package com.example.yuzelli.fluecuringmachine.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseFragment;
import com.example.yuzelli.fluecuringmachine.bean.EquipmentListBean;
import com.example.yuzelli.fluecuringmachine.bean.UserInfoBean;
import com.example.yuzelli.fluecuringmachine.constants.ConstantsUtils;
import com.example.yuzelli.fluecuringmachine.https.OkHttpClientManager;
import com.example.yuzelli.fluecuringmachine.utils.CommonAdapter;
import com.example.yuzelli.fluecuringmachine.utils.GsonUtils;
import com.example.yuzelli.fluecuringmachine.utils.SharePreferencesUtil;
import com.example.yuzelli.fluecuringmachine.utils.ViewHolder;
import com.example.yuzelli.fluecuringmachine.view.activity.MainActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by 51644 on 2017/6/5.
 */

public class YanInfoFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_equipmentList)
    PullToRefreshListView lvEquipmentList;

    private int pageNo = 1;
    private YanInfoFragmentHandler handler;

    private List<EquipmentListBean.ListBean> equipmentLists;


    @Override
    protected int layoutInit() {
        return R.layout.fragment_yan;
    }

    @Override
    protected void bindEvent(View v) {
        tvTitle.setText("设备列表");
        handler = new YanInfoFragmentHandler();
        equipmentLists = new ArrayList<>();
        doGetEquipmentList();


    }

    //   获取设备列表
    private void doGetEquipmentList() {

        StringBuffer url = new StringBuffer(ConstantsUtils.ADDRESS_URL).append(ConstantsUtils.EQUIPMENT_LIST);
        url.append(pageNo + "/").append(pageSize).append("page");

        OkHttpClientManager.getInstance().getAsync(url.toString(), new OkHttpClientManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                lvEquipmentList.onRefreshComplete();
                showToast("加载网路数据失败！");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("--doGetEquipmentList-->", result);
                lvEquipmentList.onRefreshComplete();

                JSONObject json = new JSONObject(result);
                int errorCode = json.optInt("errorCode");
                if (errorCode == 0) {
                    JSONObject data = json.optJSONObject("data");
                    List<EquipmentListBean.ListBean> lists = GsonUtils.jsonToArrayList(data.optString("list"), EquipmentListBean.ListBean.class);
                    if (lists.size() == 0) {
                        showToast("没有更多的数据了");
                        pageNo--;
                        return;
                    }
                    equipmentLists.addAll(lists);
                    handler.sendEmptyMessage(ConstantsUtils.EQUIPMENT_LIST_GET_DATA);
                } else {
                    showToast("数据获取失败！");
                }

            }
        });
    }

    class YanInfoFragmentHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantsUtils.EQUIPMENT_LIST_GET_DATA:
                    upDataList();
                    break;
                default:
                    break;
            }
        }
    }

    private void upDataList() {
        lvEquipmentList.setMode(PullToRefreshBase.Mode.BOTH);
        lvEquipmentList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //设置下拉时显示的日期和时间
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                // 更新显示的label
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                equipmentLists.clear();
                pageNo = 1;
                doGetEquipmentList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                pageNo++;
                doGetEquipmentList();
            }
        });
        lvEquipmentList.setAdapter(new CommonAdapter<EquipmentListBean.ListBean>(getActivity(), equipmentLists, R.layout.cell_equipmentlist_item) {
            @Override
            public void convert(ViewHolder helper, EquipmentListBean.ListBean item, int position) {
                LinearLayout ll_cell = helper.getView(R.id.ll_cell);
                if (position%2!=0)
                {
                    ll_cell.setBackgroundColor(Color.parseColor("#5587ceeb"));
                }

                helper.setText(R.id.tv_deviceName, item.getDeviceName());
                helper.setText(R.id.tv_deviceSn, item.getDeviceSn());
                helper.setText(R.id.tv_deviceType, item.getDeviceType());
                switch (item.getState()) {
                    case -1:
                        helper.setText(R.id.tv_state, "离线");
                        break;
                    case 0:
                        helper.setText(R.id.tv_state, "在线");
                        break;
                    case 1:
                        helper.setText(R.id.tv_state, "故障");
                        break;
                }

                helper.setText(R.id.tv_run, "正常");
            }
        });


    }

    @Override
    protected void fillData() {

    }


}
