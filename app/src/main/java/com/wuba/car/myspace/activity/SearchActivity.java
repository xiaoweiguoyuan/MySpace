package com.wuba.car.myspace.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.wuba.car.myspace.adapter.ProgramAdapter;
import com.wuba.car.myspace.adapter.SearchHistoryAdapter;
import com.wuba.car.myspace.base.BaseActivity;
import com.wuba.car.myspace.base.BaseFragmentPagerAdapter;
import com.wuba.car.myspace.entity.AllBean;
import com.wuba.car.myspace.fragment.SubscribeFragment;
import com.wuba.car.myspace.presenter.SearchAcPresenter;
import com.wuba.car.myspace.presenter.SearchPresenter;
import com.wuba.car.myspace.utils.Constants;
import com.wuba.car.myspace.utils.SpUtils;
import com.wuba.car.myspace.view.SearchAcV;
import com.wuba.car.qishuier.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchAcPresenter> implements SearchAcV {
    private EditText mEtSerch;
    private ImageView mIvClear;
    private ImageView mIvSearch;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private LinearLayout mllFragments;
    private LinearLayout mllHistory;
    private RecyclerView mRvHistory;
    private TextView mTvClearHistory;
    private boolean isRefresh = false;
    private List<String> historyData;
    private SearchHistoryAdapter searchHistoryAdapter;
    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        presenter = new SearchAcPresenter(this,this);
        mEtSerch = findViewById(R.id.search_et_input);
        mIvClear = findViewById(R.id.search_iv_delete);
        mIvSearch = findViewById(R.id.search_iv_search);
        mTablayout  = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewPager);
        mllFragments = findViewById(R.id.ll_fragments);
        mllHistory = findViewById(R.id.ll_search_history);
        mRvHistory = findViewById(R.id.rv_history);
        mTvClearHistory = findViewById(R.id.tv_clear_history);
        setListener();
    }

    private void setListener() {
        mTvClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtils.Companion.saveString(Constants.SEARCH_HISTORY,"");
                historyData.clear();
                searchHistoryAdapter.notifyDataSetChanged();
            }
        });
        mEtSerch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                initSearchHistory();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!mEtSerch.getText().toString().trim().isEmpty()){
                    mIvClear.setVisibility(View.VISIBLE);
                    mIvSearch.setVisibility(View.GONE);
                }else {
                    mIvClear.setVisibility(View.GONE);
                    mIvSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mIvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtSerch.setText("");
            }
        });

        mEtSerch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    presenter.getAllDataByNet(mEtSerch.getText().toString(),isRefresh);
                    mllHistory.setVisibility(View.GONE);
                    String strings = SpUtils.Companion.getString(Constants.SEARCH_HISTORY);
                    if (!strings.isEmpty()){
                        if (strings.contains("#")) {
                            String[] split = strings.split("#");
                            if (split.length<10) {
                                SpUtils.Companion.saveString(Constants.SEARCH_HISTORY,mEtSerch.getText().toString()+"#"+strings);
                            }else {
                                String substring = strings.substring(0, strings.lastIndexOf("#"));
                                SpUtils.Companion.saveString(Constants.SEARCH_HISTORY,mEtSerch.getText().toString()+"#"+substring);
                            }
                        }else {
                            SpUtils.Companion.saveString(Constants.SEARCH_HISTORY,mEtSerch.getText().toString()+"#"+strings);
                        }
                    }else {
                        SpUtils.Companion.saveString(Constants.SEARCH_HISTORY,mEtSerch.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onResult(AllBean allBean, boolean isRefresh) {
        if (allBean.getEpisodes()!=null || allBean.getPodcasts()!=null){
            mllFragments.setVisibility(View.VISIBLE);
            mllHistory.setVisibility(View.GONE);
            BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(),mEtSerch.getText().toString());
            mViewPager.setAdapter(pagerAdapter);
            mTablayout.setupWithViewPager(mViewPager);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        }else {
            mllFragments.setVisibility(View.GONE);
            mllHistory.setVisibility(View.VISIBLE);
        }
    }

    private void initSearchHistory(){
        mllHistory.setVisibility(View.VISIBLE);
        historyData = new ArrayList<>();
        String strings = SpUtils.Companion.getString(Constants.SEARCH_HISTORY);
        if (strings.isEmpty()){
            return;
        }
        if (strings.contains("#")){
            String[] split = strings.split("#");
            historyData.addAll(new ArrayList<>(Arrays.asList(split)));
        }else {
            historyData.add(strings);
        }
        searchHistoryAdapter = new SearchHistoryAdapter(this,historyData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvHistory.setLayoutManager(layoutManager);
        mRvHistory.setAdapter(searchHistoryAdapter);
        searchHistoryAdapter.setItemClickListener(new SearchHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mEtSerch.setText(historyData.get(position));
                presenter.getAllDataByNet(mEtSerch.getText().toString(),isRefresh);
            }
        });
    }
}
