package com.wuba.car.myspace.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.$Gson$Preconditions;
import com.wuba.car.myspace.adapter.AllProgramAdapter;
import com.wuba.car.myspace.adapter.AllSingleAdapter;
import com.wuba.car.myspace.adapter.ProgramAdapter;
import com.wuba.car.myspace.base.BaseFragment;
import com.wuba.car.myspace.entity.ProgramBean;
import com.wuba.car.myspace.presenter.AllPresenter;
import com.wuba.car.myspace.presenter.ProgramPresenter;
import com.wuba.car.myspace.view.ProgramV;
import com.wuba.car.qishuier.R;

import java.util.ArrayList;
import java.util.List;

public class ProgramFragment extends BaseFragment<ProgramPresenter> implements ProgramV {
    private RecyclerView mRvProgram;
    private ProgramAdapter allProgramAdapter;
    private List<String> mProgramDatas;
    private static ProgramFragment Instance;
    private String keyword;
    private boolean isRefresh = false;

    public static ProgramFragment newInstance(String keyword) {
        if (Instance == null) {
            Instance = new ProgramFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        Instance.setArguments(bundle);
        return Instance;
    }

    @Override
    protected int inflaterLayout() {
        return R.layout.fragment_program;
    }

    @Override
    protected void initViews(View view) {
        presenter = new ProgramPresenter(getContext(), this);
        mRvProgram = view.findViewById(R.id.recyclerView);
        mProgramDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mProgramDatas.add(i+"hello");
        }

    }

    @Override
    protected void initViewData() {
        if (getArguments() != null) {
            keyword = getArguments().getString("keyword");
            presenter.getProgramDataByNet(keyword, isRefresh);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResult(ProgramBean allBean, boolean isRefresh) {
        if (allBean.getPodcasts() != null) {
            allProgramAdapter = new ProgramAdapter(getContext(), allBean.getPodcasts());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRvProgram.setLayoutManager(layoutManager);
            mRvProgram.setAdapter(allProgramAdapter);
            allProgramAdapter.setItemClickListener(new ProgramAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), "订阅了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
