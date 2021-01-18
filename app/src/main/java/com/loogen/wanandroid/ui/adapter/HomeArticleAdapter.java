package com.loogen.wanandroid.ui.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.loogen.wanandroid.R;
import com.loogen.wanandroid.request.entity.ArticleListData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeArticleAdapter extends BaseQuickAdapter<ArticleListData.Article, BaseViewHolder> {
    public static final String TAG = "ArticleListAdapter";

    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm",Locale.getDefault());
    public Date date = new Date();

    public HomeArticleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder vh, ArticleListData.Article article) {
        if ("".equals(article.getAuthor())){
            vh.setText(R.id.tv_author,article.getShareUser());
        }else {
            vh.setText(R.id.tv_author,article.getAuthor());
        }
        vh.setText(R.id.tv_title,article.getTitle());
        date.setTime(article.getPublishTime());
        vh.setText(R.id.tv_date, dateFormat.format(date));
        vh.setText(R.id.tv_category,article.getChapterName());
    }
}
