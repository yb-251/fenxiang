package com.umeng.soexample.ui.adapter;

import android.content.Context;

import com.umeng.soexample.app.Constants;
import com.umeng.soexample.base.BaseAdapter;

import java.util.List;

public class SquareAllAdapter extends BaseAdapter {
    public SquareAllAdapter(List mData, Context context) {
        super(mData, context);
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void bindData(Object o, VH vh) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==1){
            return Constants.VIEW_TYPE_ONE;
        }else if (position==2){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==3){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==4){
            return Constants.VIEW_TYPE_TWO;
        }else if (position==5){
            return Constants.VIEW_TYPE_TWO;
        }else if (position==6){
            return Constants.VIEW_TYPE_TWO;
        }else if (position==7){
            return Constants.VIEW_TYPE_ONE;
        }else if (position==8){
            return Constants.VIEW_TYPE_TWO;
        }else if (position==9){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==10){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==11){
            return Constants.VIEW_TYPE_THREE;
        }else if (position==12){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==13){
            return Constants.VIEW_TYPE_THREE;
        }else if (position==14){
            return Constants.VIEW_TYPE_ZIEO;
        }else if (position==15){
            return Constants.VIEW_TYPE_THREE;
        }else if (position==16){
            return Constants.VIEW_TYPE_TWO;
        }else if (position==17){
            return Constants.VIEW_TYPE_TWO;
        }else if (position==18){
            return Constants.VIEW_TYPE_THREE;
        }else if (position==19){
            return Constants.VIEW_TYPE_ZIEO;
        }
        return 20;
    }
   /*0.mp3一个 1.九宫格 0.没有0.没有2.一个2.一个2.一个1.九宫格2.一个0.安迪温斯顿0.安迪温斯顿3.六个
0.安迪温斯顿3.六个0.没有3.两个2一个2一个3两个0安迪温斯顿


    */
}
