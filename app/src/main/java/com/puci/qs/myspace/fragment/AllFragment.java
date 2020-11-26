package com.puci.qs.myspace.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.puci.qs.myspace.adapter.AllProgramAdapter;
import com.puci.qs.myspace.adapter.AllSingleAdapter;
import com.puci.qs.myspace.base.BaseFragment;
import com.puci.qs.myspace.entity.AllBean;
import com.puci.qs.myspace.presenter.AllPresenter;
import com.puci.qs.myspace.view.AllV;
import com.puci.qs.qishuier.R;

public class AllFragment extends BaseFragment<AllPresenter> implements AllV {
    private RecyclerView mRvProgram;
    private RecyclerView mRvSigle;
    private AllProgramAdapter allProgramAdapter;
    private AllSingleAdapter allSingleAdapter;
    private String keyword;
    private LinearLayout mLLProgram, mLLSingle;
    private boolean isRefresh = false;
    private static AllFragment Instance;

    public static AllFragment newInstance(String keyword) {
        if (Instance == null) {
            Instance = new AllFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        Instance.setArguments(bundle);
        return Instance;
    }

    @Override
    protected int inflaterLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initViews(View view) {
        presenter = new AllPresenter(getActivity(), this);
        mRvProgram = view.findViewById(R.id.recyclerView);
        mRvSigle = view.findViewById(R.id.recyclerView2);
        mLLProgram = view.findViewById(R.id.ll_program);
        mLLSingle = view.findViewById(R.id.ll_single);
    }

    @Override
    protected void initViewData() {
        if (getArguments() != null) {
            keyword = getArguments().getString("keyword");
            presenter.getAllDataByNet(keyword, isRefresh);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResult(AllBean allBean, boolean isRefresh) {
        if (allBean.getPodcasts() != null && allBean.getPodcasts().size() > 0) {
            mLLProgram.setVisibility(View.VISIBLE);
            allProgramAdapter = new AllProgramAdapter(getContext(), allBean.getPodcasts());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRvProgram.setLayoutManager(layoutManager);
            mRvProgram.setAdapter(allProgramAdapter);
            allProgramAdapter.setItemClickListener(new AllProgramAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), "订阅", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mLLProgram.setVisibility(View.GONE);
        }
        if (allBean.getEpisodes() != null && allBean.getEpisodes().size() > 0) {
            mLLSingle.setVisibility(View.VISIBLE);
            allSingleAdapter = new AllSingleAdapter(getContext(), allBean.getEpisodes());
            LinearLayoutManager layoutManagerSingle = new LinearLayoutManager(getContext());
            mRvSigle.setLayoutManager(layoutManagerSingle);
            mRvSigle.setAdapter(allSingleAdapter);
            allSingleAdapter.setItemClickListener(new AllSingleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mLLSingle.setVisibility(View.GONE);
        }
    }


}
