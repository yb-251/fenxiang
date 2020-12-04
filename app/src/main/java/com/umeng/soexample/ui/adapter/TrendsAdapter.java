package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.umeng.soexample.R;
import com.umeng.soexample.app.Constants;
import com.umeng.soexample.module.data.TrandsData;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;

public class TrendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public List<TrandsData.DataBean.DynamicsBean> list;

    public TrendsAdapter(Context context, List<TrandsData.DataBean.DynamicsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constants.VIEW_TYPE_ZIEO) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_one, parent, false);
            return new ViewHolderOne(inflate);
        } else if (viewType == Constants.VIEW_TYPE_TWO) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_three, parent, false);
            return new ViewHolderTwo(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_two, parent, false);
            return new ViewHolderTwo(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == Constants.VIEW_TYPE_ZIEO) {
            TrandsData.DataBean.DynamicsBean dynamicsBean = list.get(position);
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.content.setText(dynamicsBean.getContent());
            viewHolderOne.createTime.setText(dynamicsBean.getCreateTime());
            viewHolderOne.nickname.setText(dynamicsBean.getNickName());
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(context).load(dynamicsBean.getHeadUrl()).apply(options).into(viewHolderOne.img_head);

            ArrayList<String> imgs = new ArrayList<>();
            for (TrandsData.DataBean.DynamicsBean.ImagesBean item : dynamicsBean.getImages()) {
                imgs.add(item.getFilePath());
            }
            viewHolderOne.nineGrid.setImagesData(imgs);
            viewHolderOne.nineGrid.setAdapter(new NineGridImageViewAdapter() {
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

            viewHolderOne.nineGrid.setImagesData(imgs);
        } else if (itemViewType == Constants.VIEW_TYPE_TWO){
            TrandsData.DataBean.DynamicsBean dynamicsBean = list.get(position);
            ViewHolderThree viewHolderThree = (ViewHolderThree) holder;
            viewHolderThree.content.setText(dynamicsBean.getContent());
            viewHolderThree.createTime.setText(dynamicsBean.getCreateTime());
            viewHolderThree.nickname.setText(dynamicsBean.getNickName());
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(context).load(dynamicsBean.getHeadUrl()).apply(options).into(viewHolderThree.img_head);
        }else {
            TrandsData.DataBean.DynamicsBean dynamicsBean = list.get(position);
            TrandsData.DataBean.DynamicsBean.ImagesBean imagesBean = list.get(position).getImages().get(position);
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.content.setText(dynamicsBean.getContent());
            viewHolderTwo.createTime.setText(dynamicsBean.getCreateTime());
            viewHolderTwo.nickname.setText(dynamicsBean.getNickName());
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(context).load(dynamicsBean.getHeadUrl()).apply(options).into(viewHolderTwo.img_head);
            Glide.with(context).load(imagesBean.getFilePath()).apply(options).into(viewHolderTwo.images);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 4) {
            return Constants.VIEW_TYPE_ZIEO;
        } else if (position == 7) {
            return Constants.VIEW_TYPE_TWO;
        } else {
            return Constants.VIEW_TYPE_ONE;
        }
    }


    class ViewHolderOne extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_head;
        public TextView nickname;
        public TextView createTime;
        public TextView content;
        public NineGridImageView nineGrid;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.img_head = (ImageView) rootView.findViewById(R.id.img_head);
            this.nickname = (TextView) rootView.findViewById(R.id.nickname);
            this.createTime = (TextView) rootView.findViewById(R.id.createTime);
            this.content = (TextView) rootView.findViewById(R.id.content);
            this.nineGrid = (NineGridImageView) rootView.findViewById(R.id.nineGrid);
        }

    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_head;
        public TextView nickname;
        public TextView createTime;
        public TextView content;
        public ImageView images;

        public ViewHolderTwo(View rootView) {
            super(rootView);
            this.img_head = (ImageView) rootView.findViewById(R.id.img_head);
            this.nickname = (TextView) rootView.findViewById(R.id.nickname);
            this.createTime = (TextView) rootView.findViewById(R.id.createTime);
            this.content = (TextView) rootView.findViewById(R.id.content);
            this.images = (ImageView) rootView.findViewById(R.id.images);
        }

    }

    class ViewHolderThree extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView img_head;
        public TextView nickname;
        public TextView createTime;
        public TextView content;

        public ViewHolderThree(View rootView) {
            super(rootView);
            this.img_head = (ImageView) rootView.findViewById(R.id.img_head);
            this.nickname = (TextView) rootView.findViewById(R.id.nickname);
            this.createTime = (TextView) rootView.findViewById(R.id.createTime);
            this.content = (TextView) rootView.findViewById(R.id.content);
        }
    }
}
