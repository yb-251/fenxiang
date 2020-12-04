package com.umeng.soexample.ui.fragment;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseFragment;
import com.umeng.soexample.interfaces.tongpao.IPersonalMeans;
import com.umeng.soexample.interfaces.tongpao.IRecommendDetails;
import com.umeng.soexample.module.data.PersonalMeansData;
import com.umeng.soexample.module.data.RecommendData;
import com.umeng.soexample.module.data.TrandsData;
import com.umeng.soexample.presenter.PersonalMeansPresenter;
import com.umeng.soexample.presenter.RecommendDetailsPresenter;
import com.umeng.soexample.ui.adapter.PersonalMeansAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalMeansFragment extends BaseFragment<PersonalMeansPresenter> implements IPersonalMeans.View {


    private RecyclerView means_rlv;
    private List<PersonalMeansData.DataBean> list;
    private PersonalMeansAdapter adapter;

    public PersonalMeansFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_personal_means;
    }

    @Override
    protected void initView() {
        means_rlv = getActivity().findViewById(R.id.means_rlv);
        means_rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new PersonalMeansAdapter(list, getActivity());
        means_rlv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getPersonalMeansData();
    }

    @Override
    protected PersonalMeansPresenter createPresenter() {
        return new PersonalMeansPresenter(this);
    }


    @Override
    public void getPersonalMeansReturn(PersonalMeansData result) {
        list.clear();
        list.add(result.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getTrendsReturn(TrandsData result) {

    }

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }
}
