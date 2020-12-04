package com.umeng.soexample.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

import cc.shinichi.library.ImagePreview;

public class RecommendPersonalAdapter extends BaseAdapter {

    public RecommendPersonalAdapter(List<RecommendData.DataBean.PostDetailBean> mData, Context context) {
        super(mData, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_layout_personal;
    }

    @SuppressLint({"CheckResult", "NewApi"})
    @Override
    protected void bindData(Object data, VH vh) {
        RecommendData.DataBean.PostDetailBean bean = (RecommendData.DataBean.PostDetailBean) data;
        ConstraintLayout cl_tz = (ConstraintLayout) vh.getViewById(R.id.cl_tz);

        TextView personal_name = (TextView) vh.getViewById(R.id.personal_name);
        TextView personal_constant = (TextView) vh.getViewById(R.id.personal_constant);
        ImageView personal_head_img = (ImageView) vh.getViewById(R.id.personal_head_img);

        RequestOptions options = new RequestOptions().circleCrop();

        Glide.with(context).load(bean.getHeadUrl()).apply(options).into(personal_head_img);
        TxtUtils.setTextView(personal_name,bean.getNickName());
        TxtUtils.setTextView(personal_constant,bean.getLabels());

        personal_head_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePreview
                        .getInstance()
                        // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                        .setContext(context)
                        // 设置从第几张开始看（索引从0开始）
                        // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                        // 1：第一步生成的imageInfo List
                        //.setImageInfoList(imageInfoList)
                        // 2：直接传url List
                        .setImage(bean.getHeadUrl())
                        // 3：只有一张图片的情况，可以直接传入这张图片的url
                        //.setImage(String image)
                        // 开启预览
                        .start();
            }
        });

    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void onClick(int pos);
    }

}
