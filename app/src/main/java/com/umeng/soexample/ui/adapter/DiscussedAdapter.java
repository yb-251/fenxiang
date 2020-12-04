package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.HotIssueData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class DiscussedAdapter extends BaseAdapter {

    Context context;
    List<HotIssueData.DataBean> mData;

    public DiscussedAdapter(List<HotIssueData.DataBean> mData, Context context) {
        super(mData, context);
        this.context = context;
        this.mData = mData;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_dicussed_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HotIssueData.DataBean dataBean = (HotIssueData.DataBean) data;
        ImageView imgIcon = (ImageView) vh.getViewById(R.id.img_icon);
        TextView txtTag = (TextView) vh.getViewById(R.id.tv_name);
        TxtUtils.setTextView(txtTag,dataBean.getName());
        //加载数据
        Glide.with(context).load(dataBean.getImageUrl()).into(imgIcon);
    }
}
