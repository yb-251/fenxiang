package com.umeng.soexample.ui.fragment;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.SquareData;
import com.umeng.soexample.presenter.SquarePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends BaseFragment<SquarePresenter> implements ISquare.View {


    private RecyclerView rlv_all;

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView() {
        rlv_all = getActivity().findViewById(R.id.rlv_all);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected SquarePresenter createPresenter() {
        return new SquarePresenter(this);
    }

    @Override
    public void getSquareReturn(SquareData result) {

    }

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }
}
