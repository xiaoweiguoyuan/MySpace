package com.puci.qs.myspace.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.puci.qs.myspace.adapter.ProgramAdapter;
import com.puci.qs.myspace.base.BaseFragment;
import com.puci.qs.myspace.entity.ProgramBean;
import com.puci.qs.myspace.presenter.ProgramPresenter;
import com.puci.qs.myspace.view.ProgramV;
import com.puci.qs.qishuier.R;

import java.util.List;

public class ProgramFragment extends BaseFragment<ProgramPresenter> implements ProgramV {
    private RecyclerView mRvProgram;
    private ProgramAdapter allProgramAdapter;
    private List<String> mProgramDatas;
    private static ProgramFragment Instance;
    private String keyword;
    private boolean isRefresh = false;

    public static ProgramFragment newInstance(String keyword){
        if (Instance==null) {
            Instance = new ProgramFragment();
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
        presenter = new ProgramPresenter(getContext(),this);
        mRvProgram = view.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViewData() {
        if (getArguments()!=null){
            keyword = getArguments().getString("keyword");
            presenter.getProgramDataByNet(keyword,isRefresh);
        }
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onResult(ProgramBean allBean, boolean isRefresh) {
        if (allBean.getPodcasts()!=null){
            allProgramAdapter = new ProgramAdapter(getContext(),allBean.getPodcasts());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRvProgram.setLayoutManager(layoutManager);
            mRvProgram.setAdapter(allProgramAdapter);
            allProgramAdapter.setItemClickListener(new ProgramAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), "订阅了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
