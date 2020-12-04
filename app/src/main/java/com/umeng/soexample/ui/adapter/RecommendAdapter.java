package com.umeng.soexample.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.umeng.soexample.MainActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.ui.recommendDetails.RecommendPersonalActivity;
import com.umeng.soexample.utils.DateUtils;
import com.umeng.soexample.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cn.carbs.android.expandabletextview.library.ExpandableTextView;

public class RecommendAdapter extends BaseAdapter {

    public RecommendAdapter(List<RecommendData.DataBean.PostDetailBean> mData, Context context) {
        super(mData, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_layout;
    }

    @SuppressLint({"CheckResult", "NewApi"})
    @Override
    protected void bindData(Object data, VH vh) {
        RecommendData.DataBean.PostDetailBean bean = (RecommendData.DataBean.PostDetailBean) data;
        ConstraintLayout cl_tz = (ConstraintLayout) vh.getViewById(R.id.cl_tz);
        ImageView imgHead = (ImageView) vh.getViewById(R.id.img_head);
        TextView txtName = (TextView) vh.getViewById(R.id.nickname);
        TextView txtTime = (TextView) vh.getViewById(R.id.createTime);
        TextView content = (ExpandableTextView) vh.getViewById(R.id.content);
        NineGridImageView ninegrid = (NineGridImageView) vh.getViewById(R.id.nineGrid);

        RequestOptions options = new RequestOptions().circleCrop();

        Glide.with(context).load(bean.getHeadUrl()).apply(options).into(imgHead);
        TxtUtils.setTextView(txtName,bean.getNickName());
        Long dateToTime = DateUtils.getDateToTime(bean.getCreateTime(), null);
        String date = DateUtils.getStandardDate(dateToTime);
        TxtUtils.setTextView(txtTime,date);
        ((ExpandableTextView) content).updateForRecyclerView(bean.getContent(),content.getMinWidth(), ExpandableTextView.STATE_SHRINK);
        testSpanAbleString(content,bean.getContent());

        imgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RecommendPersonalActivity.class));
            }
        });

        ArrayList<String> imgs = new ArrayList<>();
        for(RecommendData.DataBean.PostDetailBean.ImagesBean item:bean.getImages()){
            imgs.add(item.getFilePath());
        }
        ninegrid.setImagesData(imgs);
        ninegrid.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                Glide.with(context).load(o).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(imageView);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List list) {
                //点击查看大图
                super.onItemImageClick(context, index, list);
                ImagePreview
                        .getInstance()
                        // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                        .setContext(context)
                        // 设置从第几张开始看（索引从0开始）
                        .setIndex(index)
                        // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                        // 1：第一步生成的imageInfo List
                        //.setImageInfoList(imageInfoList)
                        // 2：直接传url List
                        .setImageList(imgs)
                        // 3：只有一张图片的情况，可以直接传入这张图片的url
                        //.setImage(String image)
                        // 开启预览
                        .start();
            }
        });
        ninegrid.setImagesData(imgs);
        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
            }
        });
        cl_tz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.onClick(vh.getLayoutPosition());
                }
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


    private void testSpanAbleString(TextView content, String beanContent) {
        SpannableString spannable = new SpannableString(beanContent);
        spannable.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }
        };
        spannable.setSpan(clickableSpan,0,9,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        content.setMovementMethod(LinkMovementMethod.getInstance());


        int da = beanContent.indexOf("@");
        int xiao = beanContent.lastIndexOf(" ");

        spannable.setSpan(new ForegroundColorSpan(Color.BLUE),da,xiao,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@androidx.annotation.NonNull View widget) {
                Toast.makeText(context, "点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannable.setSpan(clickableSpan1,da,xiao,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        content.setMovementMethod(LinkMovementMethod.getInstance());
        content.setText(spannable);
    }
}
