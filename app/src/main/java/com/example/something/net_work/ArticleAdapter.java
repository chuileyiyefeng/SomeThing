package com.example.something.net_work;

import android.content.Context;

import com.example.something.R;
import com.example.something.net_work.base.BaseAdapter;
import com.example.something.net_work.base.BaseViewHolder;
import com.example.something.net_work.bean.Article;

public class ArticleAdapter extends BaseAdapter<Article> {
    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayout() {
        return R.layout.adapter_article;
    }

    @Override
    protected void bindHolder(BaseViewHolder holder, int i) {
        holder.setText(R.id.tv_name, getItem(i).getName());
    }
}
