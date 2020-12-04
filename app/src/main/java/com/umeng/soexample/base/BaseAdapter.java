package com.umeng.soexample.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {

    List<D> mData;
    public Context context;
    IItemListClick click;

    public BaseAdapter(List<D> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null){
                    click.itemClick(vh.getLayoutPosition());
                }
            }
        });
        return vh;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mData.get(position), (VH) holder);
    }

    protected abstract void bindData(D d, VH vh);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addListClick(IItemListClick click) {
        this.click = click;
    }
    public interface IItemListClick{

        void itemClick(int pos);
    }

    public class VH extends RecyclerView.ViewHolder {

        SparseArray views = new SparseArray();

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        public View getViewById(int id) {
            View view = (View) views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }
}
