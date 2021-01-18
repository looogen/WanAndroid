package com.loogen.wanandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.base.ViewModelScope;
import com.loogen.wanandroid.databinding.FragmentHomeBinding;
import com.loogen.wanandroid.ui.adapter.HomeArticleAdapter;
import com.loogen.wanandroid.ui.state.HomeViewModel;
import com.loogen.wanandroid.ui.state.MainViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

/**
 *
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    public static final String TAG = "HomeFragment";

    private HomeArticleAdapter mArticleAdapter;

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);

        mViewModel.title.set(getString(R.string.home_title));

        mArticleAdapter = new HomeArticleAdapter(R.layout.item_home_article_list);
        mViewDataBinding.rvArticle.setAdapter(mArticleAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(mActivity,R.drawable.shape_divider));
        mViewDataBinding.rvArticle.addItemDecoration(divider);



        mViewModel.articleList.observe(getViewLifecycleOwner(), articles -> {
//            mArticleAdapter.addData(articles);
            mArticleAdapter.addData(0,articles);
            mViewDataBinding.smartRefresh.finishRefresh();
            mViewDataBinding.smartRefresh.finishLoadMore();
        });


        mViewDataBinding.smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mViewModel.get;
                mViewModel.loadArticleListData(0);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                mViewModel.getArticleListData(0);
                mViewModel.loadArticleListData(0);
            }
        });


        Log.i(TAG, "onViewCreated: " + "requestData>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (mActivity == null) {
            Log.i(TAG, "onViewCreated: " + "activity null");
        } else {
            Log.i(TAG, "onViewCreated: " + "activity not null");
        }
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}