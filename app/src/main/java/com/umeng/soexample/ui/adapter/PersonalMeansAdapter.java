package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class PersonalMeansAdapter extends BaseAdapter {
    public PersonalMeansAdapter(List<PersonalMeansData.DataBean> mData, Context context) {
        super(mData, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.means_item;
    }

    @Override
    protected void bindData(Object o, VH vh) {
        PersonalMeansData.DataBean dataBean = (PersonalMeansData.DataBean) o;
        TextView tv_sex = (TextView) vh.getViewById(R.id.tv_sex);
        TextView birthday = (TextView) vh.getViewById(R.id.birthday);
        TextView tv_dress = (TextView) vh.getViewById(R.id.tv_dress);
        TxtUtils.setTextView(birthday,dataBean.getBirthday());
        TxtUtils.setTextView(tv_dress,dataBean.getCity());
    }
}
