package com.loogen.wanandroid.ui.state;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.loogen.wanandroid.base.BaseModel;
import com.loogen.wanandroid.base.BaseViewModel;
import com.loogen.wanandroid.model.DataResult;
import com.loogen.wanandroid.model.HomeModel;
import com.loogen.wanandroid.request.entity.ArticleListData;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeModel> {
    public static final String TAG = "HomeViewModel";

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt curPage = new ObservableInt(0);


    public MutableLiveData<List<ArticleListData.Article>> articleList = new MutableLiveData<>();


    public void loadArticleListData(int page) {
        model.getArticleList(page, new DataResult.IResult<ArticleListData>() {
            @Override
            public void onSuccess(DataResult.Success<ArticleListData> data) {
                articleList.setValue(data.getData().getDatas());
                curPage.set(data.getData().getCurPage());
            }

            @Override
            public void onError(DataResult.Error error) {

            }
        });
    }

    @Override
    public BaseModel getModel() {
        return new HomeModel();
    }
}
