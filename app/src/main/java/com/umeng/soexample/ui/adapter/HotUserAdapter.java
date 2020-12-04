package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.HotUserData;
import com.umeng.soexample.module.data.RecommendData;

import java.util.ArrayList;
import java.util.List;

public class HotUserAdapter extends BaseAdapter {

    public HotUserAdapter(List<HotUserData.DataBean> mData, Context context) {
        super(mData, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_hotuser;
    }

    @Override
    protected void bindData(Object o, VH vh) {
        HotUserData.DataBean dataBean = (HotUserData.DataBean) o;

        ImageView hot_head_img = (ImageView) vh.getViewById(R.id.hot_head_img);
        TextView hot_name = (TextView) vh.getViewById(R.id.hot_name);
        TextView dress = (TextView) vh.getViewById(R.id.dress);
        ImageView img1 = (ImageView) vh.getViewById(R.id.img1);
        ImageView img2 = (ImageView) vh.getViewById(R.id.img2);

        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher);



        Glide.with(context).load(dataBean.getHeadUrl()).apply(options).into(hot_head_img);
        hot_name.setText(dataBean.getNickName());
        dress.setText(dataBean.getCity());

        Glide.with(context).load(dataBean.getFileBeanList().get(0).getFilePath()).into(img1);
        Glide.with(context).load(dataBean.getFileBeanList().get(1).getFilePath()).into(img2);
    }
}
