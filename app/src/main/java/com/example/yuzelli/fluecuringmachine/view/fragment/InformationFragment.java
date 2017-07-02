package com.example.yuzelli.fluecuringmachine.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;
import com.example.yuzelli.fluecuringmachine.base.BaseFragment;
import com.example.yuzelli.fluecuringmachine.utils.CommonAdapter;
import com.example.yuzelli.fluecuringmachine.utils.ListViewForScrollViewUtil;
import com.example.yuzelli.fluecuringmachine.utils.ViewHolder;
import com.example.yuzelli.fluecuringmachine.view.activity.EquipmentDetailActivity;
import com.example.yuzelli.fluecuringmachine.view.activity.InfomationDetailActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    List<String> list = new ArrayList<>(Arrays.asList(new String[]{"气流上升式的安装","气流下降式的安装","大密集烤房安装","普改密的安装"}));
    List<String> list2 = new ArrayList<>(Arrays.asList(new String[]{"烟叶的分类编杆","气流下降式装烟应注意的事项"}));
    List<String> list3 = new ArrayList<>(Arrays.asList(new String[]{"炉堂维护","发电机的使用和维护","循环风机和自控的维护"}));


    @Override
    protected int layoutInit() {
        return R.layout.fragment_information;
    }

    @Override
    protected void bindEvent(View v) {
        tvTitle.setText("信息");

        lvInstall.setAdapter(new CommonAdapter<String>(getActivity(),list,R.layout.cell_info_item) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv_cotent,item);
            }
        });
        lvGetReady.setAdapter(new CommonAdapter<String>(getActivity(),list2,R.layout.cell_info_item) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv_cotent,item);
            }
        });
        lvMaintain.setAdapter(new CommonAdapter<String>(getActivity(),list3,R.layout.cell_info_item) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv_cotent,item);
            }
        });
        lvInstall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  InfomationDetailActivity.actionStart(getActivity(),list.get(position),position);
            }
        });

        lvGetReady.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // InfomationDetailActivity.actionStart(getActivity(),list2.get(position),position+4);
            }
        });

        lvMaintain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   InfomationDetailActivity.actionStart(getActivity(),list3.get(position),position+4+2);
            }
        });
    }

    @Override
    protected void fillData() {

    }


}
