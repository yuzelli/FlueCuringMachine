package com.example.yuzelli.fluecuringmachine.adapter;

/**
 * Created by 51644 on 2017/6/29.
 */
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yuzelli.fluecuringmachine.R;

public class SpinerAdapter extends BaseAdapter {

    private List<String> mList;
    private Context mContext;

    public SpinerAdapter(Context pContext, List<String> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 下面是重要代码
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.sprinner_item, null);
        if(convertView!=null) {

            TextView _TextView1=(TextView)convertView.findViewById(R.id.tv_name);

            _TextView1.setText(mList.get(position));

        }
        return convertView;
    }

}