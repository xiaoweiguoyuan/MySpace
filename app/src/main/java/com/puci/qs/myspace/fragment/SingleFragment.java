package com.puci.qs.myspace.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.puci.qs.myspace.adapter.SingleAdapter;
import com.puci.qs.myspace.base.BaseFragment;
import com.puci.qs.myspace.entity.SingleBean;
import com.puci.qs.myspace.presenter.SinglePresenter;
import com.puci.qs.myspace.view.SingleV;
import com.puci.qs.qishuier.R;

import java.util.List;

public class SingleFragment extends BaseFragment<SinglePresenter> implements SingleV {
    private RecyclerView mRvSingle;
    private SingleAdapter singleAdapter;
    private List<String> mProgramDatas;
    private static SingleFragment Instance;
    private String keyword;
    private boolean isRefresh = false;

    public static SingleFragment newInstance(String keyword){
        if (Instance==null) {
            Instance = new SingleFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("keyword",keyword);
        Instance.setArguments(bundle);
        return  Instance;
    }

    @Override
    protected int inflaterLayout() {
        return R.layout.fragment_program;
    }

    @Override
    protected void initViews(View view) {
        presenter = new SinglePresenter(getContext(),this);
        mRvSingle = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViewData() {
        if (getArguments()!=null){
            keyword = getArguments().getString("keyword");
            presenter.getSingleDataByNet(keyword,isRefresh);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResult(SingleBean bean, boolean isRefresh) {
        if (bean.getEpisodes()!=null && bean.getEpisodes().size()>0){
            singleAdapter = new SingleAdapter(getContext(),bean.getEpisodes());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRvSingle.setLayoutManager(layoutManager);
            mRvSingle.setAdapter(singleAdapter);
            singleAdapter.setItemClickListener(new SingleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), "点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
