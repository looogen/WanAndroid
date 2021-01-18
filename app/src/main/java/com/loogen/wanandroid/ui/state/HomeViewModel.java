package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.base.BaseViewModel;
import com.loogen.wanandroid.model.HomeModel;
import com.loogen.wanandroid.model.Request;
import com.loogen.wanandroid.request.entity.ArticleListData;

import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeModel> {
    public static final String TAG = "HomeViewModel";

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt curPage = new ObservableInt(0);

    public LiveData<Request<ArticleListData>> loadMoreArticleListData() {
        return model.getArticleList(curPage.get());
    }

    public LiveData<Request<ArticleListData>> refreshArticleListData() {
        return model.getArticleList(0);
    }


    @Override
    public BaseModel getModel() {
        return new HomeModel();
    }
}
