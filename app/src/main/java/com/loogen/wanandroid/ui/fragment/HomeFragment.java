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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.loogen.wanandroid.R;
import com.loogen.wanandroid.base.BaseFragment;
import com.loogen.wanandroid.base.DataBindingConfig;
import com.loogen.wanandroid.databinding.FragmentHomeBinding;
import com.loogen.wanandroid.model.Request;
import com.loogen.wanandroid.request.entity.ArticleListData;
import com.loogen.wanandroid.ui.adapter.HomeArticleAdapter;
import com.loogen.wanandroid.ui.state.HomeViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.Objects;

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
        DividerItemDecoration divider = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mActivity, R.drawable.shape_divider)));
        mViewDataBinding.rvArticle.addItemDecoration(divider);

        mViewDataBinding.smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewModel.refreshArticleListData().observe(getViewLifecycleOwner(), refreshArticle);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.loadMoreArticleListData().observe(getViewLifecycleOwner(), loadArticle);
            }
        });

        mViewDataBinding.smartRefresh.autoRefresh();


        Log.i(TAG, "onViewCreated: " + "requestData>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (mActivity == null) {
            Log.i(TAG, "onViewCreated: " + "activity null");
        } else {
            Log.i(TAG, "onViewCreated: " + "activity not null");
        }
    }


    Observer<Request<ArticleListData>> refreshArticle = new Observer<Request<ArticleListData>>() {

        @Override
        public void onChanged(Request<ArticleListData> request) {
            if (request.getCode() == Request.Status.SUCCESS) {
                mViewDataBinding.smartRefresh.finishRefresh(true);
                mArticleAdapter.setList(request.getData().getDatas());
                mViewModel.curPage.set(0);
            } else {
                mViewDataBinding.smartRefresh.finishRefresh(false);
            }
        }
    };


    Observer<Request<ArticleListData>> loadArticle = new Observer<Request<ArticleListData>>() {
        @Override
        public void onChanged(Request<ArticleListData> request) {
            mViewDataBinding.smartRefresh.finishLoadMore();
            if (request.getCode() == Request.Status.SUCCESS) {
                mViewDataBinding.smartRefresh.finishLoadMore(true);
                mArticleAdapter.addData(request.getData().getDatas());
                mViewModel.curPage.set(request.getData().getCurPage());
            } else {
                mViewDataBinding.smartRefresh.finishLoadMore(false);
            }
        }
    };


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