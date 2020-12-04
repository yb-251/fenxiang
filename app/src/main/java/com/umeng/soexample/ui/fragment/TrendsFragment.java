package com.umeng.soexample.ui.fragment;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.IPersonalMeans;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.TrandsData;
import com.umeng.soexample.presenter.PersonalMeansPresenter;
import com.umeng.soexample.ui.adapter.TrendsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendsFragment extends BaseFragment<PersonalMeansPresenter> implements IPersonalMeans.View {


    private RecyclerView trends_rlv;
    private List<TrandsData.DataBean.DynamicsBean> list;
    private TrendsAdapter adapter;

    public TrendsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_trends;
    }

    @Override
    protected void initView() {
        trends_rlv = getActivity().findViewById(R.id.trends_rlv);
        trends_rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new TrendsAdapter(getActivity(), list);
        trends_rlv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getTrendsData();
    }

    @Override
    protected PersonalMeansPresenter createPresenter() {
        return new PersonalMeansPresenter(this);
    }

    @Override
    public void getPersonalMeansReturn(PersonalMeansData result) {

    }

    @Override
    public void getTrendsReturn(TrandsData result) {
        list.clear();
        list.addAll(result.getData().getDynamics());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }
}
